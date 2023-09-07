package shop.mtcoding.blogv2.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyRepository;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillRepository;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishduty.WishDutyRepository;
import shop.mtcoding.blogv2.wishskill.WishSkill;
import shop.mtcoding.blogv2.wishskill.WishSkillRepository;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private WishSkillRepository wishSkillRepository;

    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private WishDutyRepository wishDutyRepository;

    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    // // 채용공고등록 view
    // public List<Notice> findAllJoinSkill() {
    // return null;
    // }

    // 채용공고등록
    @Transactional
    public void 채용등록(NoticeRequest.SaveDTO saveDTO) {
        List<WishSkill> wishSkillList = new ArrayList<>();
        List<WishDuty> wishDutyList = new ArrayList<>();

        Notice notice = Notice.builder()
                .title(saveDTO.getTitle())
                .companyName(saveDTO.getCompanyName())
                .companyEmail(saveDTO.getCompanyEmail())
                .phoneNumber(saveDTO.getPhoneNumber())
                .companyInfo(saveDTO.getCompanyInfo())
                .location(saveDTO.getLocation())
                .intake(saveDTO.getIntake())
                .period(saveDTO.getPeriod())
                .pay(saveDTO.getPay())
                .qualification(saveDTO.getQualification())
                .build();

        // Notice 엔터티를 저장합니다.
        noticeRepository.save(notice);

        // 채용공고와 연관된 기술 스택을 가져와서 WishSkill 엔터티를 생성하고 저장합니다.
        List<String> skillList = saveDTO.getWishSkills();
        for (String skillName : skillList) {
            Skill skill = skillRepository.findBySkillName(skillName);
            if (skill != null) {
                WishSkill wishSkill = WishSkill.builder()
                        .notice(notice)
                        .skill(skill)
                        .build();
                wishSkillList.add(wishSkill); // wishSkill을 리스트에 추가
            }
        }

        // WishSkill 엔터티를 저장합니다.
        wishSkillRepository.saveAll(wishSkillList);

        // 채용공고와 연관된 직무를 가져와서 WishDuty 엔터티를 생성하고 저장합니다.
        List<String> dutyList = saveDTO.getWishDutys();
        for (String dutyName : dutyList) {
            Duty duty = dutyRepository.findByDutyName(dutyName);
            if (duty != null) {
                WishDuty wishDuty = WishDuty.builder()
                        .notice(notice)
                        .duty(duty)
                        .build();
                wishDutyList.add(wishDuty); // wishDuty를 리스트에 추가
            }
        }

        // WishDuty 엔터티를 저장합니다.
        wishDutyRepository.saveAll(wishDutyList);
    }

    // 채용공고삭제
    @Transactional
    public void 채용삭제(Integer id) {
        noticeRepository.deleteById(id);
    }

    @Transactional
    public Notice 수정화면(Integer id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new MyException(id + "는 찾을 수 없습니다"));

        // Notice와 연관된 직무 정보를 가져옵니다.
        List<WishDuty> wishDutys = wishDutyRepository.findByNoticeId(id);

        // Notice와 연관된 기술 정보를 가져옵니다.
        List<WishSkill> wishSkills = wishSkillRepository.findByNoticeId(id);

        // 가져온 직무와 기술 정보를 Notice에 설정합니다.
        notice.setWishDutys(wishDutys);
        notice.setWishSkills(wishSkills);

        return notice;
    }

    // @Transactional
    // public void 채용수정(Integer id, NoticeRequest.UpdateDTO updateDTO) {
    // // 공고 엔티티를 조회합니다.
    // Notice notice = noticeRepository.findById(id).orElse(null);

    // if (notice != null) {
    // // 사용자가 입력한 내용을 사용하여 공고를 업데이트합니다.
    // notice.setTitle(updateDTO.getTitle());
    // notice.setCompanyInfo(updateDTO.getCompanyInfo());
    // // 다른 필드도 업데이트...

    // // 기존의 직무 및 기술 정보를 삭제합니다.
    // wishDutyRepository.deleteByNoticeId(id);
    // wishSkillRepository.deleteByNoticeId(id);

    // // 업데이트된 직무 정보를 저장합니다.
    // List<WishDuty> updatedWishDutys = createOrUpdateWishDutys(notice,
    // updateDTO.getWishDutys());
    // wishDutyRepository.saveAll(updatedWishDutys);

    // // 업데이트된 기술 정보를 저장합니다.
    // List<WishSkill> updatedWishSkills = createOrUpdateWishSkills(notice,
    // updateDTO.getWishSkills());
    // wishSkillRepository.saveAll(updatedWishSkills);

    // // 업데이트된 공고를 저장합니다.
    // noticeRepository.save(notice);
    // }
    // }

    // private List<WishSkill> createOrUpdateWishSkills(Notice notice, String[]
    // strings) {
    // return null;
    // }

    // private List<WishDuty> createOrUpdateWishDutys(Notice notice, String[]
    // strings) {
    // return null;
    // }

    // @Transactional
    // public List<WishDuty> getWishDutys(Integer id) {
    // return wishDutyRepository.findByNoticeId(id);
    // }

    // @Transactional
    // public List<WishSkill> getWishSkills(Integer id) {
    // return wishSkillRepository.findByNoticeId(id);
    // }

    // // 채용공고상세보기

    public List<Notice> getAllNotices() {
        List<Notice> noties = noticeRepository.findAll();
        return noties;
    }

    @Transactional
    public void 채용수정(Integer id, NoticeRequest.UpdateDTO updateDTO) {
        Optional<Notice> optionalNotice = noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            notice.setTitle(updateDTO.getTitle());
            notice.setCompanyInfo(updateDTO.getCompanyInfo());
            notice.setCompanyPicUrl(updateDTO.getCompanyPicUrl());
            notice.setLocation(updateDTO.getLocation());
            notice.setIntake(updateDTO.getIntake());
            notice.setPay(updateDTO.getPay());
            notice.setPeriod(updateDTO.getPeriod());
            notice.setQualification(updateDTO.getQualification());

            // 위시 스킬 및 위시 듀티 업데이트

            // 기존 위시스킬 데이터 삭제
            for (WishSkill existingSkill : notice.getWishSkills()) {
                existingSkill.setNotice(null);
            }
            notice.getWishSkills().clear();

            // 새로운 위시스킬 데이터 추가
            if (updateDTO.getWishSkills() != null) {
                for (String skillName : updateDTO.getWishSkills()) {
                    Skill skill = skillRepository.findBySkillName(skillName);
                    if (skill != null) {
                        WishSkill wishSkill = new WishSkill();
                        wishSkill.setSkill(skill);
                        wishSkill.setNotice(notice);
                        wishSkillRepository.save(wishSkill);
                    }
                }
            }

            // 위시 듀티 업데이트
            // 기존 위시 듀티 데이터 삭제
            for (WishDuty existingDuty : notice.getWishDutys()) {
                existingDuty.setNotice(null);
            }
            notice.getWishDutys().clear();

            // 새로운 위시 듀티 데이터 추가
            // List<WishDuty> newWishDutys = new ArrayList<>();
            if (updateDTO.getWishDutys() != null) {
                for (String dutyName : updateDTO.getWishDutys()) {
                    Duty duty = dutyRepository.findByDutyName(dutyName);
                    if (duty != null) {
                        WishDuty wishDuty = new WishDuty();
                        wishDuty.setDuty(duty);
                        wishDuty.setNotice(notice);
                        wishDutyRepository.save(wishDuty);

                    }
                }

            }

            noticeRepository.save(notice);
        }
    }

    public List<WishDuty> getWishDutys(Integer id) {
        return wishDutyRepository.findByNoticeId(id);
    }

    public List<WishSkill> getWishSkills(Integer id) {
        return wishSkillRepository.findByNoticeId(id);
    }

}
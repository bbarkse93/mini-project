package shop.mtcoding.blogv2.notice;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.vo.MyPath;
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

    // 채용공고등록
    @Transactional
    public Notice 채용등록(NoticeRequest.SaveDTO saveDTO) {
        // 파일 이름 생성
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + saveDTO.getCompanyPic().getOriginalFilename();
        System.out.println("fileName : " + fileName);

        // 이미지 파일 저장 경로 설정
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            // 이미지 파일 저장
            Files.write(filePath, saveDTO.getCompanyPic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        // 기존의 리스트를 초기화
        List<WishSkill> wishSkillList = new ArrayList<>();
        List<WishDuty> wishDutyList = new ArrayList<>();

        // Notice 엔터티 생성
        Notice notice = Notice.builder()
                .title(saveDTO.getTitle())
                .companyName(saveDTO.getCompanyName())
                .companyEmail(saveDTO.getCompanyEmail())
                .phoneNumber(saveDTO.getPhoneNumber())
                .companyPicUrl(fileName)
                .companyInfo(saveDTO.getCompanyInfo())
                .location(saveDTO.getLocation())
                .intake(saveDTO.getIntake())
                .period(saveDTO.getPeriod())
                .pay(saveDTO.getPay())
                .qualification(saveDTO.getQualification())
                .build();

        // Notice 엔티티 저장
        noticeRepository.save(notice);

        // 기술 스택 관련 정보 처리
        List<String> skillList = saveDTO.getWishSkills();
        for (String skillName : skillList) {
            Skill skill = skillRepository.findBySkillName(skillName);
            if (skill != null) {
                WishSkill wishSkill = WishSkill.builder()
                        .notice(notice)
                        .skill(skill)
                        .build();
                wishSkillList.add(wishSkill);
            }
        }
        // WishSkill 엔터티 저장
        wishSkillRepository.saveAll(wishSkillList);

        // 직무 관련 정보 처리
        List<String> dutyList = saveDTO.getWishDutys();
        for (String dutyName : dutyList) {
            Duty duty = dutyRepository.findByDutyName(dutyName);
            if (duty != null) {
                WishDuty wishDuty = WishDuty.builder()
                        .notice(notice)
                        .duty(duty)
                        .build();
                wishDutyList.add(wishDuty);
            }
        }
        // WishDuty 엔터티 저장
        wishDutyRepository.saveAll(wishDutyList);

        // 이미지 URL 업데이트
        notice.setCompanyPicUrl(fileName);

        return notice;
    }

    // 채용공고삭제
    @Transactional
    public void 채용삭제(Integer id) {
        noticeRepository.deleteById(id);
    }

    // 채용공고수정 view
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

    // 채용공고수정
    @Transactional
    public void 채용수정(Integer id, NoticeRequest.UpdateDTO updateDTO) {
        Optional<Notice> optionalNotice = noticeRepository.findById(id);

        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            notice.setTitle(updateDTO.getTitle());
            notice.setCompanyInfo(updateDTO.getCompanyInfo());
            notice.setLocation(updateDTO.getLocation());
            notice.setIntake(updateDTO.getIntake());
            notice.setPay(updateDTO.getPay());
            notice.setPeriod(updateDTO.getPeriod());
            notice.setQualification(updateDTO.getQualification());

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

            // 기존 위시듀티 데이터 삭제
            for (WishDuty existingDuty : notice.getWishDutys()) {
                existingDuty.setNotice(null);
            }
            notice.getWishDutys().clear();

            // 새로운 위시 듀티 데이터 추가
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

    // 채용공고상세보기
    public List<Notice> getAllNotices() {
        List<Notice> noties = noticeRepository.findAll();
        return noties;
    }

}
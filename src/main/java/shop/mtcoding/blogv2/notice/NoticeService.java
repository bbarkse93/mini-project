package shop.mtcoding.blogv2.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyRepository;
import shop.mtcoding.blogv2.notice.NoticeRequest.UpdateDTO;
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

    // 채용공고수정 view
    @Transactional
    public Notice 수정화면(@PathVariable Integer id) {
        Optional<Notice> noticeOP = noticeRepository.findById(id);
        // if (noticeOP.isPresent()) {
        return noticeOP.get();
        // } else {
        // throw new MyApiException(id + "는 찾을 수 없습니다");
        // }
    }

    // 채용공고수정
    @Transactional
    public void 채용수정(Integer id, UpdateDTO updateDTO) {
        Optional<Notice> noticeOP = noticeRepository.findById(id);
        if (noticeOP.isPresent()) {
            Notice notice = noticeOP.get();
            notice.setTitle(updateDTO.getTitle());
            notice.setCompanyName(updateDTO.getCompanyName());
            notice.setCompanyEmail(updateDTO.getCompanyEmail());
            notice.setPhoneNumber(updateDTO.getPhoneNumber());
            notice.setCompanyPicUrl(updateDTO.getCompanyPicUrl());
            notice.setCompanyInfo(updateDTO.getCompanyInfo());
            notice.setLocation(updateDTO.getLocation());
            notice.setWishDutys(updateDTO.getWishDutys());
            notice.setWishSkills(updateDTO.getWishSkills());
            notice.setIntake(updateDTO.getIntake());
            notice.setPay(updateDTO.getPay());
            notice.setPeriod(updateDTO.getPeriod());
            notice.setQualification(updateDTO.getQualification());
            notice.setCreatedAt(updateDTO.getCreatedAt());
        } else {
            throw new MyException(id + "는 찾을 수 없습니다");
        }

        // 채용공고상세보기

    }

    public List<Notice> getAllNotices() {
        List<Notice> noties = noticeRepository.findAll();
        return noties;
    }



}
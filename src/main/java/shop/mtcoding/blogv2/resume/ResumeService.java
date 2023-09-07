package shop.mtcoding.blogv2.resume;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
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
import shop.mtcoding.blogv2.resume.ResumeRequest.ResumeDTO;
import shop.mtcoding.blogv2.resume.ResumeRequest.UpdateDTO;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillRepository;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishduty.WishDutyRepository;
import shop.mtcoding.blogv2.wishskill.WishSkill;
import shop.mtcoding.blogv2.wishskill.WishSkillRepository;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private WishSkillRepository wishSkillRepository;

    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private WishDutyRepository wishDutyRepository;

    public Resume findById(Integer id) {
        return resumeRepository.findById(id).get();
    }

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    // 이력서삭제
    @Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(id);
    }

    // 이력서등록
    @Transactional
    public Resume 이력서등록(ResumeRequest.SaveDTO saveDTO) {
        // 파일 이름 생성
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + saveDTO.getPersonalPic().getOriginalFilename();

        // 이미지 파일 저장 경로 설정
        Path filePath = Paths.get(MyPath.IMG_PATH + fileName);
        try {
            // 이미지 파일 저장
            Files.write(filePath, saveDTO.getPersonalPic().getBytes());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        // 기존의 리스트를 초기화
        List<WishSkill> wishSkillList = new ArrayList<>();
        List<WishDuty> wishDutyList = new ArrayList<>();

        Resume resume = Resume.builder()
                .title(saveDTO.getTitle())
                .personalName(saveDTO.getPersonalName())
                .personalEmail(saveDTO.getPersonalEmail())
                .phoneNumber(saveDTO.getPhoneNumber())
                .personalPicUrl(fileName)
                .coverLetter(saveDTO.getCoverLetter())
                .createdAt(saveDTO.getCreatedAt())
                .build();
        resumeRepository.save(resume);

        // 기술 스택 관련 정보 처리
        List<String> skillList = saveDTO.getWishSkills();
        for (String skillName : skillList) {
            Skill skill = skillRepository.findBySkillName(skillName);
            if (skill != null) {
                WishSkill wishSkill = WishSkill.builder()
                        .resume(resume)
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
                        .resume(resume)
                        .duty(duty)
                        .build();
                wishDutyList.add(wishDuty);
            }
        }
        // WishDuty 엔터티 저장
        wishDutyRepository.saveAll(wishDutyList);

        // 이미지 URL 업데이트
        resume.setPersonalPicUrl(fileName);

        return resume;
    }

    public List<WishDuty> getWishDutys(Integer id) {
        return wishDutyRepository.findByResumeId(id);
    }

    public List<WishSkill> getWishSkills(Integer id) {
        return wishSkillRepository.findByResumeId(id);
    }

    // 이력서수정 view
    @Transactional
    public Resume 이력서수정화면(Integer id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new MyException(id + "는 찾을 수 없습니다"));

        // Notice와 연관된 직무 정보를 가져옵니다.
        List<WishDuty> wishDutys = wishDutyRepository.findByResumeId(id);

        // Notice와 연관된 기술 정보를 가져옵니다.
        List<WishSkill> wishSkills = wishSkillRepository.findByResumeId(id);

        // 가져온 직무와 기술 정보를 Notice에 설정합니다.
        resume.setWishDutys(wishDutys);
        resume.setWishSkills(wishSkills);

        return resume;
    }

    // 이력서수정
    @Transactional
    public void 이력서수정(Integer id, UpdateDTO updateDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(id);

        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            resume.setTitle(updateDTO.getTitle());
            resume.setCoverLetter(updateDTO.getCoverLetter());
            resume.setCreatedAt(updateDTO.getCreatedAt());

            // 기존 위시스킬 데이터 삭제
            for (WishSkill existingSkill : resume.getWishSkills()) {
                existingSkill.setResume(null);
            }
            resume.getWishSkills().clear();

            // 새로운 위시스킬 데이터 추가
            if (updateDTO.getWishSkills() != null) {
                for (String skillName : updateDTO.getWishSkills()) {
                    Skill skill = skillRepository.findBySkillName(skillName);
                    if (skill != null) {
                        WishSkill wishSkill = new WishSkill();
                        wishSkill.setSkill(skill);
                        wishSkill.setResume(resume);
                        wishSkillRepository.save(wishSkill);
                    }
                }
            }

            // 기존 위시듀티 데이터 삭제
            for (WishDuty existingDuty : resume.getWishDutys()) {
                existingDuty.setResume(null);
            }
            resume.getWishDutys().clear();

            // 새로운 위시 듀티 데이터 추가
            if (updateDTO.getWishDutys() != null) {
                for (String dutyName : updateDTO.getWishDutys()) {
                    Duty duty = dutyRepository.findByDutyName(dutyName);
                    if (duty != null) {
                        WishDuty wishDuty = new WishDuty();
                        wishDuty.setDuty(duty);
                        wishDuty.setResume(resume);
                        wishDutyRepository.save(wishDuty);
                    }
                }
            }
            resumeRepository.save(resume);
        }
    }
}

//
// public void updateUserApplyStatus(ResumeDTO resumeDTO) {
// {
// // ResumeDTO를 Resume 엔티티로 변환
// Resume resume = new Resume();
// resume.setTitle(resumeDTO.getTitle());
// resume.setPersonalName(resumeDTO.getPersonalName());
// resume.setPersonalEmail(resumeDTO.getPersonalEmail());
// resume.setPhoneNumber(resumeDTO.getPhoneNumber());
// resume.setCoverLetter(resumeDTO.getCoverLetter());

// // 이력서 정보 업데이트
// // userApplyStatusRepository를 사용하여 데이터베이스 업데이트
// }
// }

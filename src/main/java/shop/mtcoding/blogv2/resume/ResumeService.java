package shop.mtcoding.blogv2.resume;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.vo.MyPath;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyRepository;
import shop.mtcoding.blogv2.resume.ResumeRequest.ResumeDTO;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillRepository;
import shop.mtcoding.blogv2.user.User;
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

    @Autowired
    private HttpSession session;

    // 이력서삭제
    @Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(id);
    }

    // 이력서 수정
    public Resume findById(Integer id) {
        return resumeRepository.findById(id).get();
    }

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    // 이력서등록
    @Transactional
    public Resume 이력서등록(ResumeRequest.SaveDTO saveDTO, Integer sessionUserId) {
        User sessionUser = (User) session.getAttribute("sessionUserId");
        // 파일 이름 생성
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + saveDTO.getPersonalPic().getOriginalFilename();
        System.out.println("fileName : " + fileName);

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
                .edu(saveDTO.getEdu())
                .user(User.builder().id(sessionUserId).build())
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

    public Resume getResumeById(Integer resumeId) {
        return null;
    }

    public void updateUserApplyStatus(ResumeDTO resumeDTO) {
        {
            // ResumeDTO를 Resume 엔티티로 변환
            Resume resume = new Resume();
            resume.setTitle(resumeDTO.getTitle());
            resume.setPersonalName(resumeDTO.getPersonalName());
            resume.setPersonalEmail(resumeDTO.getPersonalEmail());
            resume.setPhoneNumber(resumeDTO.getPhoneNumber());
            resume.setCoverLetter(resumeDTO.getCoverLetter());

            // 이력서 정보 업데이트
            // userApplyStatusRepository를 사용하여 데이터베이스 업데이트
        }
    }

}

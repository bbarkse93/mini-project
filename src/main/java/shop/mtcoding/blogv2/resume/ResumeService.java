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
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillRepository;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.duty.wishduty.WishDuty;
import shop.mtcoding.blogv2.duty.wishduty.WishDutyRepository;
import shop.mtcoding.blogv2.skill.wishskill.WishSkill;
import shop.mtcoding.blogv2.skill.wishskill.WishSkillRepository;

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

    public Resume findById(Integer id) {
        Resume resume = resumeRepository.findById(id).get();
        return resume;
    }

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    // 이력서삭제
    @Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(id);
    }

    @Transactional
    public Resume 이력서등록(ResumeRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
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

        Resume resume;
        if (saveDTO.getId() != null) {
            // 이미 등록된 이력서 업데이트
            resume = resumeRepository.findById(saveDTO.getId()).orElse(null);
            if (resume == null) {
                // 해당 ID에 해당하는 이력서가 없으면 예외 처리 또는 오류 처리를 수행할 수 있습니다.
                throw new MyException("해당 이력서를 찾을 수 없습니다.");
            }
        } else {
            // 새로운 이력서 생성
            resume = new Resume();
        }

        resume.setTitle(saveDTO.getTitle());
        resume.setPersonalName(saveDTO.getPersonalName());
        resume.setPersonalEmail(saveDTO.getPersonalEmail());
        resume.setPhoneNumber(saveDTO.getPhoneNumber());
        resume.setPersonalPicUrl(fileName);
        resume.setCoverLetter(saveDTO.getCoverLetter());
        resume.setCreatedAt(saveDTO.getCreatedAt());
        resume.setEdu(saveDTO.getEdu());
        resume.setUser(User.builder().id(sessionUser.getId()).build());

        // 이력서를 저장 (새로 생성한 경우 또는 업데이트한 경우 모두 저장)
        resumeRepository.save(resume);

        // 기존의 리스트를 초기화하지 않고, 해당 이력서의 WishSkill 및 WishDuty를 업데이트
        List<String> skillList = saveDTO.getWishSkills();
        List<String> dutyList = saveDTO.getWishDutys();

        // 새로운 정보 추가
        List<WishSkill> newWishSkills = new ArrayList<>();
        List<WishDuty> newWishDutys = new ArrayList<>();

        for (String skillName : skillList) {
            Skill skill = skillRepository.findBySkillName(skillName);
            if (skill != null) {
                WishSkill wishSkill = WishSkill.builder()
                        .resume(resume) // 이 부분이 누락되지 않도록 확인
                        .skill(skill)
                        .build();
                newWishSkills.add(wishSkill);
            }
        }

        for (String dutyName : dutyList) {
            Duty duty = dutyRepository.findByDutyName(dutyName);
            if (duty != null) {
                WishDuty wishDuty = WishDuty.builder()
                        .resume(resume) // 이 부분이 누락되지 않도록 확인
                        .duty(duty)
                        .build();
                newWishDutys.add(wishDuty);
            }
        }

        // 새로운 정보 저장
        wishSkillRepository.saveAll(newWishSkills);
        wishDutyRepository.saveAll(newWishDutys);

        // 이미지 URL 업데이트
        resume.setPersonalPicUrl(fileName);
        return resume;
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
    public void update(Integer id, ResumeRequest.UpdateDTO updateDTO) {
        // 기존 이력서 조회
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new MyException("해당 이력서 정보를 찾을 수 없습니다."));

        // 이력서 내용 업데이트
        resume.setTitle(updateDTO.getTitle());
        resume.setCoverLetter(updateDTO.getCoverLetter());
        resume.setCreatedAt(updateDTO.getCreatedAt());

        // 기존의 정보를 모두 삭제하고 새로운 정보로 대체
        wishSkillRepository.deleteAllByResume(resume);
        wishDutyRepository.deleteAllByResume(resume);

        // 새로운 위시 스킬 정보 추가
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

        // 새로운 위시 듀티 정보 추가
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

        // 수정된 채용 정보 저장
        resumeRepository.save(resume);
    }

    public List<WishDuty> getWishDutys(Integer id) {
        return wishDutyRepository.findByResumeId(id);
    }

    public List<WishSkill> getWishSkills(Integer id) {
        return wishSkillRepository.findByResumeId(id);
    }

    public Long 총이력서(Integer userId) {
        return resumeRepository.findByUserIdCount(userId);
    }

    public List<Resume> 이력서조회(Integer userId) {
        return resumeRepository.findByUserId(userId);
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

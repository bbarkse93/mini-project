package shop.mtcoding.blogv2.resume;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyService;
import shop.mtcoding.blogv2.edu.Edu;
import shop.mtcoding.blogv2.edu.EduService;
import shop.mtcoding.blogv2.location.Location;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillService;
import shop.mtcoding.blogv2.resume.ResumeRequest.ResumeDTO;
import shop.mtcoding.blogv2.user.UserService;


@Controller
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private DutyService dutyService;

    @Autowired
    private EduService eduService;

    @Autowired
    private UserService userService;

    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {

        resumeService.deleteById(id); // 이렇게 하면 요청된 id에 해당하는 이력서를 삭제합니다.

        return "redirect:/myResumeList"; // userResumeList 페이지로 리디렉션
    }

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO updateDTO) {

        resumeService.update(updateDTO, id);

        return "redirect:/myResumeList";
    }

    @GetMapping("/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        // 1. 인증 검사

        // 2. 권한 체크

        // 3. 핵심 로직
        Resume resume = resumeService.findById(id);
        request.setAttribute("resume", resume);

        return "resume/resumeUpdateForm";
    }

    @GetMapping("/myResumeList")
    public String 나의이력서관리(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();

        request.setAttribute("notices", notices);
        List<Resume> resumeList = resumeService.findAll();
        request.setAttribute("resumeList", resumeList);
        return "resume/myResumeList";
    }

    // 이력서 등록 view
    @GetMapping("/resumeWrite")
    public String resumeWrite(HttpServletRequest request) {
        List<Skill> skills = skillService.findAll();
        List<Duty> dutys = dutyService.findAll();
        List<Edu> edus = eduService.findAll();

        request.setAttribute("skills", skills);
        request.setAttribute("dutys", dutys);
        request.setAttribute("edus", edus);
        return "/resume/resumeWrite";
    }

    // 이력서등록하기
    @PostMapping("/resumeSave")
    public String resumeSave(ResumeRequest.SaveDTO saveDTO) {
        resumeService.이력서등록(saveDTO); 
        return "redirect:/myResumeList";
    }

    @GetMapping("/resume/{id}/Detail")
    public String 이력서상세보기(@PathVariable Integer id,HttpServletRequest request){
         Resume resume = resumeService.findById(id);
        request.setAttribute("resume", resume);
          List<Resume> resumeList = resumeService.findAll();
        request.setAttribute("resumeList", resumeList);
        return "resume/resumeDetail";
    }

    
   


}

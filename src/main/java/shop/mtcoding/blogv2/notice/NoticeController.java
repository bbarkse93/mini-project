package shop.mtcoding.blogv2.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyService;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillService;
import shop.mtcoding.blogv2.wishduty.WishDutyService;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private DutyService dutyService;

    @Autowired
    private WishDutyService wishDutyService;

    // 기업회원정보(디폴트화면)
    @GetMapping("/companyNoticeList")
    public String companyNoticeList(HttpServletRequest request) {
        List<Notice> noticeList = noticeService.findAll();
        request.setAttribute("noticeList", noticeList);
        return "/company/companyNoticeList";
    }

    // 공고상세보기 view
    @GetMapping("/noticeDetail")
    public String detail() {
        return "/notice/noticeDetail";
    }

    // 채용공고등록 view
    @GetMapping("/noticeWrite")
    public String noticeWrite(HttpServletRequest request) {
        List<Skill> skills = skillService.findAll();
        List<Duty> dutys = dutyService.findAll();
        request.setAttribute("skills", skills);
        request.setAttribute("dutys", dutys);
        return "/notice/noticeWrite";
    }

    // 채용등록하기
    @PostMapping("/noticeSave")
    public String noticeSave(NoticeRequest.SaveDTO saveDTO) {
        System.out.println("테스트 : " + saveDTO.getWishSkills());

        noticeService.채용등록(saveDTO);
        System.out.println("테스트 : " + saveDTO.getWishSkills());

        return "redirect:/companyNoticeList";
    }

    // 채용삭제하기
    @PostMapping("/companyNoticeList/{id}/delete")
    public String delete(@PathVariable Integer id) {
        noticeService.채용삭제(id);
        return "redirect:/companyNoticeList";
    }

    // 추천공고
    @GetMapping("/notices")
    public String getAllNotices(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
        return "notices"; // 머스태치 템플릿 파일의 경로
    }

    // 채용수정하기 view
    @GetMapping("noticeUpdate/{id}")
    public String noticeUpdateForm(@PathVariable Integer id, HttpServletRequest request) {
        Notice notice = noticeService.수정화면(id);
        request.setAttribute("notice", notice);
        return "/notice/noticeUpdate";
    }

    // 채용수정하기
    @PostMapping("noticeUpdate/{id}/update")
    public String noticeUpdate(@PathVariable Integer id, NoticeRequest.UpdateDTO updateDTO) {
        noticeService.채용수정(id, updateDTO);
        return "redirect:/companyNoticeList";
    }

    @GetMapping("/jobPosting")
    public String noticeList(HttpServletRequest request){
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
        return "/main/jobPosting";
    }

}

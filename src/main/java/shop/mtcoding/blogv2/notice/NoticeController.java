package shop.mtcoding.blogv2.notice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyService;
import shop.mtcoding.blogv2.location.Location;
import shop.mtcoding.blogv2.location.LocationService;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillService;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishskill.WishSkill;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private DutyService dutyService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private HttpSession session;

    // 기업회원정보(디폴트화면)
    @GetMapping("/companyNoticeList")
    public String companyNoticeList(HttpServletRequest request) {
        List<Notice> noticeList = noticeService.findAll();
        Collections.sort(noticeList, Comparator.comparing(Notice::getId).reversed());
        request.setAttribute("noticeList", noticeList);
        return "/company/companyNoticeList";
    }

    // 채용삭제하기
    @PostMapping("/companyNoticeList/{id}/delete")
    public String delete(@PathVariable Integer id) {
        noticeService.채용삭제(id);
        return "redirect:/companyNoticeList";
    }

    // 공고상세보기 view
    @GetMapping("/notice/noticeDetail/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Notice noticeDetail = noticeService.공고상세보기(id);
        List<WishSkill> wishSkills = noticeService.getWishSkills(id);
        List<WishDuty> wishDutys = noticeService.getWishDutys(id);
        request.setAttribute("noticeDetail", noticeDetail);
        request.setAttribute("wishSkills", wishSkills);
        request.setAttribute("wishDutys", wishDutys);
        // noticeDetail.get()
        return "notice/noticeDetail";
    }

    // 채용공고등록 view
    @GetMapping("/noticeWrite")
    public String noticeWrite(HttpServletRequest request) {
        List<Skill> skills = skillService.findAll();
        List<Duty> dutys = dutyService.findAll();
        List<Location> locations = locationService.findAll();

        request.setAttribute("skills", skills);
        request.setAttribute("dutys", dutys);
        request.setAttribute("locations", locations);
        return "/notice/noticeWrite";
    }

    // 채용등록하기
    @PostMapping("/noticeSave")
    public String noticeSave(NoticeRequest.SaveDTO saveDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않았습니다");
        }

        noticeService.채용등록(saveDTO, sessionUser.getId());

        return "redirect:/companyNoticeList";
    }

    // 채용수정하기 view
    @GetMapping("/noticeUpdate/{id}")
    public String noticeUpdateForm(@PathVariable Integer id, HttpServletRequest request) {
        // 수정할 공고 정보 및 관련된 직무 및 기술 정보를 불러와서 모델에 추가합니다.
        Notice notice = noticeService.수정화면(id);
        List<Skill> skills = skillService.findAll();
        List<Duty> dutys = dutyService.findAll();
        List<Location> locations = locationService.findAll();
        request.setAttribute("skills", skills);
        request.setAttribute("dutys", dutys);
        request.setAttribute("locations", locations);

        List<WishDuty> wishDutys = noticeService.getWishDutys(id); // 직무 정보 가져오기
        List<WishSkill> wishSkills = noticeService.getWishSkills(id); // 기술 정보 가져오기
        request.setAttribute("wishDutys", wishDutys);
        request.setAttribute("wishSkills", wishSkills);
        request.setAttribute("notice", notice);
        return "/notice/noticeUpdate";
    }

    // 채용수정하기
    @PostMapping("/noticeUpdate/{id}/update")
    public String noticeUpdate(@PathVariable Integer id,
            NoticeRequest.UpdateDTO updateDTO, HttpServletRequest request) {
        Notice notice = noticeService.수정화면(id);
        request.setAttribute("notice", notice);
        // 사용자가 입력한 내용을 사용하여 공고를 업데이트합니다.

        noticeService.채용수정(id, updateDTO);
        return "redirect:/companyNoticeList";
    }

    // 추천공고
    @GetMapping("/notices")
    public String getAllNotices(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
        return "notices"; // 머스태치 템플릿 파일의 경로
    }
}

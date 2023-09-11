package shop.mtcoding.blogv2.notice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.duty.Duty;
import shop.mtcoding.blogv2.duty.DutyService;
import shop.mtcoding.blogv2.event.Event;
import shop.mtcoding.blogv2.event.EventService;
import shop.mtcoding.blogv2.location.Location;
import shop.mtcoding.blogv2.location.LocationService;
import shop.mtcoding.blogv2.skill.Skill;
import shop.mtcoding.blogv2.skill.SkillService;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserService;
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
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private HttpSession session;

    // 메인페이지
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        List<Event> events = eventService.findAll();
        List<Notice> noticeList = noticeService.findAll();
        request.setAttribute("events", events);
        request.setAttribute("noticeList", noticeList);
        return "index";
    }

    // 기업회원정보(디폴트화면)
    @GetMapping("/companyNoticeList/{id}")
    public String companyNoticeList(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        List<Notice> noticeList = noticeService.findByUserId(sessionUser.getId());
        User user = userService.회원정보보기(sessionUser.getId());
        Collections.sort(noticeList, Comparator.comparing(Notice::getId).reversed());
        request.setAttribute("noticeList", noticeList);
        request.setAttribute("user", user);
        return "company/companyNoticeList";
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
        Notice notice = noticeService.findById(id);
        request.setAttribute("notice", notice);
        List<Notice> noticeList = noticeService.findAll();
        request.setAttribute("noticeList", noticeList);
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

        noticeService.채용등록(saveDTO);

        return "redirect:/companyNoticeList/" + sessionUser.getId();
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
        return "redirect:/companyNoticeList/" + id;
    }

    // 추천공고
    @GetMapping("/notices")
    public String getAllNotices(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
        return "notices"; // 머스태치 템플릿 파일의 경로
    }

    // // 채용공고 페이지 빈 껍데기를 준다
    // @GetMapping("/api/jobPosting")
    // public @ResponseBody List<Notice> jobPosting() {
    // List<Notice> notices = noticeService.findAll();
    // return notices;
    // }

    // // 채용공고 페이지
    // @GetMapping("/api/jobPosting")
    // public ApiUtil<String> jobPosting(@RequestParam(defaultValue = "0") Integer
    // page, HttpServletRequest request) {
    // Page<Notice> noticePage = noticeService.공고목록보기(page);
    // request.setAttribute("noticePage", noticePage);
    // request.setAttribute("prevPage", noticePage.getNumber() - 1);
    // request.setAttribute("nextPage", noticePage.getNumber() + 1);
    // return new ApiUtil<String>(true, "성공");
    // }

    @GetMapping("/jobPosting")
    public String jobPosting(@RequestParam(defaultValue = "0") Integer page, Model model) {
        Page<Notice> noticePage = noticeService.공고목록보기(page);
        model.addAttribute("noticePage", noticePage);
        model.addAttribute("prevPage", noticePage.getNumber() - 1);
        model.addAttribute("nextPage", noticePage.getNumber() + 1);
        return "/main/jobPosting";
    }

}

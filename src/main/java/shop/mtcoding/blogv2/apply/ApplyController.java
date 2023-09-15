package shop.mtcoding.blogv2.apply;

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
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserService;

@Controller
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private HttpSession session;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;

    @GetMapping("/individual")
    public List<Apply> getIndividualApplies(@PathVariable Integer userId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        return applyService.getAppliesByStatus(sessionUser.getId());
    }

    // @GetMapping("/userApplyStatus")
    // public String getUserApplyStatus(HttpServletRequest request) {
    // List<Notice> notices = noticeService.getAllNotices();
    // request.setAttribute("notices", notices);
    // List<Apply> individualApplies = applyService.getAppliesByStatus(false);
    // request.setAttribute("individual", individualApplies); // HttpServletRequest에
    // 데이터 추가
    // return "user/userApplyStatus"; // 머스태치 템플릿 파일의 경로
    // }
    @GetMapping("/companyApplyList/{id}")
    public String companyApplyList(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        List<Apply> individualApplies = applyService.getAppliesByStatus(sessionUser.getId());
        User user = userService.회원정보보기(sessionUser.getId());
        request.setAttribute("user", user);
        request.setAttribute("individual", individualApplies);
        Collections.sort(individualApplies, Comparator.comparing(Apply::getId).reversed());

        return "company/companyApplyList";
    }

    @GetMapping("/userApplyStatus/{id}")
    public String getApplyStatus(@PathVariable Integer id, HttpServletRequest request) {
        // 세션에서 userId를 가져옵니다.
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        // userId를 사용하여 로직을 수행
        List<Apply> individual = applyService.지원현황조회(sessionUser.getId());
        List<Notice> notices = noticeService.getAllNotices();
        User user = userService.회원정보보기(sessionUser.getId());
        request.setAttribute("user", user);
        request.setAttribute("notices", notices);
        // HttpServletRequest에 데이터 추가
        request.setAttribute("individual", individual);

        return "user/userApplyStatus";
    }

    @PostMapping("/apply/{id}/update")
    public String update(@PathVariable Integer id, ApplyRequest.UpdateDTO updateDTO) {

        applyService.기업지원관리(id, updateDTO);
        return "redirect:/companyApplyList";
    }

    @GetMapping("/apply/{id}")
    public String 지원하기(@PathVariable Integer id, ApplyRequest.ApplyDTO applyDTO) {

        applyService.지원하기(id);
        return "redirect:/userApplyStatus/" + id;
    }

}
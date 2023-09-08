package shop.mtcoding.blogv2.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.apply.ApplyService;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.resume.ResumeService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    HttpSession session;

    @Autowired
    private ApplyService applyService;

     @Autowired
    private ResumeService resumeService;
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/userJoinForm")
    public String joinForm() {
        return "main/userJoinForm";
    }

    @PostMapping("/userJoin")
    public String 개인회원가입(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);

        return "redirect:/loginForm";
    }

    @GetMapping("/compJoinForm")
    public String companyjoinForm() {

        return "main/compJoinForm";
    }

    @PostMapping("/compJoin")
    public String 기업회원가입(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);

        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(UserRequest.LoginDTO loginDTO) {

        return "/main/loginForm";
    }

    @PostMapping("/login")
    public String 로그인(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);

        session.setAttribute("sessionUser", sessionUser);

        return "/index";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/userInformation")
    private String 회원정보보기(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();
        Long apply = applyService.지원개수();
        Long resume = resumeService.총이력서();
        request.setAttribute("notices", notices);
        request.setAttribute("apply", apply);
        request.setAttribute("resume", resume);

        
        return "user/userinformation";
    }

    @GetMapping("/userUpdate")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회원정보보기(sessionUser.getId());

        session.setAttribute("user", user);

        return "user/userUpdate";
    }

    @PostMapping("/userupdate1")
    public String 개인정보수정(UserRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회원수정(updateDTO, sessionUser.getId());

        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/companyUpdate")
    public String companyUpdate() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회원정보보기(sessionUser.getId());

        session.setAttribute("user", user);

        return "company/companyUpdate";
    }

    @PostMapping("/companyUpdate1")
    public String 기업정보수정(UserRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User user = userService.회원수정(updateDTO, sessionUser.getId());

        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/jobPosting")
    public String jobPosting() {

        return "main/jobPosting";
    }

    @GetMapping("/event")
    public String Event() {
        return "main/event";
    }

    @GetMapping("/api/check")
    public @ResponseBody ApiUtil<String> check(String username) {

        return userService.checkusername(username);
    }

    @GetMapping("/userInformation2")
    public String 개인정보구분() {

        String myInformation = userService.개인정보구분();

        return "redirect:" + myInformation;
    }

    



}

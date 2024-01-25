package shop.mtcoding.blogv2.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.apply.ApplyService;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.resume.ResumeService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NoticeService noticeService;
    private final ApplyService applyService;
    private final ResumeService resumeService;
    private final HttpSession session;

    // 개인유저 회원가입 페이지
    @GetMapping("/userJoinForm")
    public String joinForm() {
        return "main/userJoinForm";
    }

    // 개인유저 회원가입 기능
    @PostMapping("/userJoin")
    public String 개인회원가입(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);

        return "redirect:/loginForm";
    }

    // 기업유저 회원가입 페이지
    @GetMapping("/compJoinForm")
    public String companyjoinForm() {

        return "main/compJoinForm";
    }

    // 기업유저 회원가입 기능
    @PostMapping("/compJoin")
    public String 기업회원가입(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO);

        return "redirect:/loginForm";
    }

    // 로그인 페이지
    @GetMapping("/loginForm")
    public String loginForm(UserRequest.LoginDTO loginDTO) {

        return "main/loginForm";
    }

    // 로그인 기능
    @PostMapping("/login")
    public @ResponseBody String 로그인(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);

        // 유효성 검사
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()){
            throw new MyException("username을 입력하세요");
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()){
            throw new MyException("password를 입력하세요");
        }
        if (sessionUser == null) {
            return Script.href("/loginForm", "username이 틀렸습니다");
        }
        boolean isvalid = BCrypt.checkpw(loginDTO.getPassword(), sessionUser.getPassword());
        if (!isvalid) {
            return Script.href("/loginForm", "password가 틀렸습니다");
        }

        session.setAttribute("sessionUser", sessionUser);

        return Script.href("/", "로그인 성공");
    }

    // 로그아웃 기능
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("userInformation/{id}")
    private String 회원정보보기(@PathVariable Integer id, HttpServletRequest request) {
        System.out.println("나 여깄어!");
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        List<Notice> notices = noticeService.getAllNotices();
        Long apply = applyService.지원개수(sessionUser.getId());
        Long resume = resumeService.총이력서(sessionUser.getId());
        User user = userService.회원정보보기(sessionUser.getId());
        request.setAttribute("notices", notices);
        request.setAttribute("apply", apply);
        request.setAttribute("resume", resume);
        request.setAttribute("user", user);

        return "user/userInformation";
    }

    @GetMapping("/userUpdateForm/{id}")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        User user = userService.회원정보보기(sessionUser.getId());

        request.setAttribute("user", user);

        return "user/userUpdateForm";
    }

    @PostMapping("/userupdate/{id}")
    public String 개인정보수정(HttpServletRequest request, UserRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        User user = userService.회원수정(updateDTO, sessionUser.getId());

        request.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/companyUpdateForm/{id}")
    public String companyUpdate(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        User user = userService.회원정보보기(sessionUser.getId());

        request.setAttribute("user", user);

        return "company/companyUpdateForm";
    }

    @PostMapping("/companyUpdate/{id}")
    public String 기업정보수정(UserRequest.UpdateDTO updateDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        User user = userService.회원수정(updateDTO, sessionUser.getId());

        session.setAttribute("sessionUser", user);

        return "redirect:/";
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

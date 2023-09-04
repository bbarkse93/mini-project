package shop.mtcoding.blogv2.user;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;

    @Autowired
    HttpSession session;

  

    @GetMapping("/userJoinForm")
    public String joinForm() {
        return "main/userJoinForm";
    }

    @PostMapping("/userJoin")
    public String 개인회원가입(UserRequest.JoinDTO joinDTO){
        userService.회원가입(joinDTO);
        return "redirect:/loginForm";
    }

    @GetMapping("/compJoinForm")
    public String companyjoinForm(){    
        return "main/compJoinForm";
    }

    @PostMapping("/compJoin")
    public String 기업회원가입(UserRequest.JoinDTO joinDTO){
        userService.회원가입(joinDTO);
        return "redirect:/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(UserRequest.LoginDTO loginDTO){
        
        return "/main/loginForm";
    }

    @PostMapping("/login")
    public  String 로그인(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "user/userApplyStatus";
    }

    @GetMapping("/userInformation")
    private String 회원정보보기(HttpServletRequest request){
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
     
        return"user/userinformation";
    }

 
 
}



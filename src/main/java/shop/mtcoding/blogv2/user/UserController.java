package shop.mtcoding.blogv2.user;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;

  

    @GetMapping("/puserjoinForm")
    public String joinForm() {
        return "main/puserJoinForm";
    }

    @PostMapping("/join")
    public String 개인회원가입(UserRequest.JoinDTO joinDTO){
        userService.회원가입(joinDTO);
        return "redirect:/loginForm";
    }


    @GetMapping("/companyJoinForm")
    public String companyjoinForm(){    
        return "main/companyJoin";
    }


    @PostMapping("/companyjoin")
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
 
}

package shop.mtcoding.blogv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public String loginForm(){
        return "/main/loginForm";
    }

    @GetMapping("/test")
    public String test(){
        return"user/userApplyStatus";
    }

}



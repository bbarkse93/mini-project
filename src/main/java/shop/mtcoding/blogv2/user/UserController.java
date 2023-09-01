package shop.mtcoding.blogv2.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


   @GetMapping("userApplyStatus")    //지원현황
     public String manageResume() {

    
        
    return "user/userApplyStatus";
}


    
      
    

}

package shop.mtcoding.blogv2.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResumeController {

    @Autowired
    ResumeService resumeService;

     @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
      
        resumeService.deleteById(1); // 이렇게 하면 요청된 id에 해당하는 이력서를 삭제합니다.
    
        return "redirect:/test"; // userResumeList 페이지로 리디렉션
    }
         

}

package shop.mtcoding.blogv2.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2.user.UserRepository;
import shop.mtcoding.blogv2.user.UserService;




@Controller
public class ResumeController {

    @Autowired
   private ResumeRepository resumeRepository;

    @Autowired
   private ResumeService resumeService;

    @Autowired 
    private UserRepository userRepository;
    
    @Autowired

   private UserService userService;
    
    @GetMapping("userResumeList") // 이력서관리
    public String manageResume() {
    
        
             return "user/userResumeList";
        }

      

      @Transactional
       @PostMapping("/resumeWrite/{id}/update")
       public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO updateDTO) {
        // 1. 인증 검사

        // 2. 권한 체크

        // 3. 핵심 로직
        // update board_tb set title = :title, content = :content where id = :id
        resumeService.update(updateDTO,id);

        return "resume/resumeWrite";
       }

    
    @GetMapping("/resume/{id}/updateForm")
    public String updateForm( ) {
        // // 1. 인증 검사

        // // 2. 권한 체크

        // // 3. 핵심 로직
        // Resume resume = resumeService.findById(1);
        // request.setAttribute("resume", resume);

        return "resume/resumeUpdateForm";
    }
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
      
        resumeService.deleteById(id); // 이렇게 하면 요청된 id에 해당하는 이력서를 삭제합니다.
    
        return "redirect:/userResumeList"; // userResumeList 페이지로 리디렉션
    }
           
    
          
    }
        
 
    
        



    



package shop.mtcoding.blogv2.resume;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.board.BoardRequest;


@Controller
public class ResumeController {

    @Autowired
    ResumeService resumeService;

     @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
      
        resumeService.deleteById(1); // 이렇게 하면 요청된 id에 해당하는 이력서를 삭제합니다.
    
        return "redirect:/test"; // userResumeList 페이지로 리디렉션
    }
    
    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO updateDTO) {
       
        resumeService.update(updateDTO, id);

        return "user/userResumeList";
    }
    
    @GetMapping("/resume/{id}/updateForm")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request) {
        // 1. 인증 검사

        // 2. 권한 체크

        // 3. 핵심 로직
        Resume resume = resumeService.findById(1);
        request.setAttribute("resume", resume);

        return "resume/resumeUpdateForm";
    }

}

package shop.mtcoding.blogv2.apply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private NoticeService noticeService;
    


    // 개인 지원 목록 조회
    @GetMapping("/individual")
    public List<Apply> getIndividualApplies() {
        return applyService.getAppliesByStatus(true);
    }
    
    @GetMapping("/userApplyStatus")
    public String getUserApplyStatus(HttpServletRequest request) {
        List<Notice> notices = noticeService.getAllNotices();
         request.setAttribute("notices", notices);
        List<Apply> individualApplies = applyService.getAppliesByStatus(true);
        request.setAttribute("individual", individualApplies); // HttpServletRequest에 데이터 추가
        return "user/userApplyStatus"; // 머스태치 템플릿 파일의 경로
    }
  
    
}

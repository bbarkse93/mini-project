package shop.mtcoding.blogv2.apply;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private HttpSession session;

    @GetMapping("/individual")
    public List<Apply> getIndividualApplies(@PathVariable Integer userId) {
        return applyService.getAppliesByStatus(userId);
    }

    // @GetMapping("/userApplyStatus")
    // public String getUserApplyStatus(HttpServletRequest request) {
    // List<Notice> notices = noticeService.getAllNotices();
    // request.setAttribute("notices", notices);
    // List<Apply> individualApplies = applyService.getAppliesByStatus(false);
    // request.setAttribute("individual", individualApplies); // HttpServletRequest에
    // 데이터 추가
    // return "user/userApplyStatus"; // 머스태치 템플릿 파일의 경로
    // }
    @GetMapping("/companyApplyList/{userId}")
    public String companyApplyList(@PathVariable Integer userId, HttpServletRequest request) {
        List<Apply> individualApplies = applyService.getAppliesByStatus(userId);
        request.setAttribute("individual", individualApplies);
        return "company/companyApplyList";
    }

    @GetMapping("/userApplyStatus/{userId}")
    public String getApplyStatus(HttpServletRequest request) {
        // 세션에서 userId를 가져옵니다.
        Integer userId = (Integer) session.getAttribute("userId");
        // userId를 사용하여 로직을 수행
        List<Apply> individualApplies = applyService.지원현황조회(1);
        // HttpServletRequest에 데이터 추가
        request.setAttribute("individual", individualApplies);
        return "user/userApplyStatus";
    }

    @PostMapping("/apply/{id}/update")
    public String update(@PathVariable Integer id, ApplyRequest.UpdateDTO updateDTO) {
        applyService.기업지원관리(id, updateDTO);
        return "redirect:/companyApplyList";
    }

    @GetMapping("/apply")
    public String apply() {
        applyService.지원하기();
        return "redirect:/userApplyStatus/1";
}
}
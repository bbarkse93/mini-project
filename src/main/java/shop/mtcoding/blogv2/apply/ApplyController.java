package shop.mtcoding.blogv2.apply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import shop.mtcoding.blogv2.notice.NoticeService;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private HttpSession session;

    @Autowired
    private NoticeService noticeService;

    // 개인 지원 목록 조회
    @GetMapping("/individual")
    public List<Apply> getIndividualApplies() {
        return applyService.getAppliesByStatus(false);
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

    @GetMapping("/companyApplyList")
    public String 기업지원서관리(HttpServletRequest request) {
        List<Apply> individualApplies = applyService.getAppliesByStatus(false);
        request.setAttribute("individual", individualApplies);

        return "company/companyApplyList"; // 머스태치 템플릿 파일의 경로
    }

    @GetMapping("/userApplyStatus/{userId}")
    public String getApplyStatus(HttpServletRequest request) {
        // 세션에서 userId를 가져옵니다.
        Integer userId = (Integer) session.getAttribute("userId");

        // userId를 사용하여 로직을 수행
        List<Apply> individualApplies = applyService.지원현황조회(userId);

        // HttpServletRequest에 데이터 추가
        request.setAttribute("individual", individualApplies);
        return "user/userApplyStatus";

    }

}

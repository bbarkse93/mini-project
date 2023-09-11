package shop.mtcoding.blogv2.scrap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserService;

@Controller
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/userScrapCompany/{id}")
    public String userScrapCompany(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        List<Scrap> scraps = scrapService.getScrapsByUserId(sessionUser.getId());
        List<Notice> notices = noticeService.getAllNotices();
        User user = userService.회원정보보기(sessionUser.getId());
        // 조회된 공지사항과 스크랩된 목록을 HTTP 요청 객체에 추가
        request.setAttribute("notices", notices);
        request.setAttribute("user", user);
        request.setAttribute("scraps", scraps);

        return "user/userScrapCompany"; // 뷰 이름 반환
    }
    @GetMapping("/notice/{id}/noticScrap")
    public String 유저관심기업(@PathVariable Integer id, ScrapRequest.UserScrapDTO userScrapDTO) {
        // 세션에서 사용자 정보 가져오기
        User sessionUser = (User) session.getAttribute("sessionUser");
        
        // 세션에 사용자 정보가 없다면 예외 처리
        if (sessionUser == null) {
            throw new MyException("인증되지 않은 유저입니다.");
        }
        
        // 스크랩 서비스를 호출하여 관심 기업을 추가
        scrapService.관심기업(id, userScrapDTO);
    
        // 리다이렉트할 URL로 변경 (예시로 "/userScrapCompany"를 사용)
        return "redirect:/userScrapCompany/"+sessionUser.getId();
    }
}
package shop.mtcoding.blogv2.scrap;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;

@Controller
public class ScrapController {

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/userScrapCompany")
    public String userScrapCompany(Integer userId, HttpServletRequest request) {
        List<Scrap> scraps = scrapService.getScrapsByUserId(1);
        List<Notice> notices = noticeService.getAllNotices();

        // 조회된 공지사항과 스크랩된 목록을 HTTP 요청 객체에 추가
        request.setAttribute("notices", notices);
        request.setAttribute("scraps", scraps);

        return "user/userScrapCompany"; // 뷰 이름 반환
    }

    //유저 관심기업
   @GetMapping("/notice/{id}/noticScrap")
    public String 유저관심기업(@PathVariable Integer id, ScrapRequest.UserScrapDTO userScrapDTO) {
        scrapService.관심기업(id,userScrapDTO);
        return "redirect:/userScrapCompany";
    }


}

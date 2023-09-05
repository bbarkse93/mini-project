package shop.mtcoding.blogv2.bookmark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeService;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.resume.ResumeService;
import shop.mtcoding.blogv2.user.User;



@Controller
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ResumeService resumeService;

 
    @GetMapping("/bookmark")
    public String getAllBookmarksByUser(Integer userId, HttpServletRequest request) {
       
        List<Notice> notices = noticeService.getAllNotices();
        request.setAttribute("notices", notices);
        // userId를 사용하여 해당 유저의 북마크를 조회
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarksByUser(1);
    
        // 조회된 북마크 목록을 HTTP 요청 객체에 추가
        request.setAttribute("bookmarks", bookmarks);
        return "company/companyApplyList"; // 뷰 이름 반환


    }
}

package shop.mtcoding.blogv2.bookmark;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

   @GetMapping("/user/bookmarkCompany/{userId}")
public String getAllBookmarksByUser(@PathVariable Integer userId, HttpServletRequest request) {
    // userId를 사용하여 해당 유저의 북마크를 조회
    List<Bookmark> bookmarks = bookmarkService.getAllBookmarksByUser(userId);

    // 조회된 북마크 목록을 HTTP 요청 객체에 추가
    request.setAttribute("bookmarks", bookmarks);

    return "bookmarks"; // 뷰 이름 반환
}



    
}

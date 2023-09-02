package shop.mtcoding.blogv2.bookmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/user/bookmarkCompany/{userId}")
    public List<Bookmark> getAllBookmarksByUser(@PathVariable Integer userId) {
        // userId를 사용하여 해당 유저의 북마크를 조회하는 로직을 구현하면 됩니다.
        List<Bookmark> bookmarks = bookmarkService.getAllBookmarksByUser(userId);
        return bookmarks;
    }



    
}

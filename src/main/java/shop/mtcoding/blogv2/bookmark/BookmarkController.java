package shop.mtcoding.blogv2.bookmark;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.user.User;

@Controller
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private HttpSession session;

    @GetMapping("api/user/bookmark/{id}/save")
    public @ResponseBody ApiUtil<String> userBookmarkSave(@PathVariable Integer id
            ) {
        User user = (User) session.getAttribute("sessionUser");
        if (user == null) {
            return new ApiUtil<String>(false, "로그인 안됨");
        }
        Integer sucuess = bookmarkService.유저북마크추가(id, user.getId());
        System.out.println("테스트" + sucuess);
        if (sucuess == 1) {
            return new ApiUtil<String>(true, "북마크 성공");
        } else {
            return new ApiUtil<String>(false, "북마크 실패");
        }
    }

    @GetMapping("/api/user/bookmark/{id}/delete")
    public @ResponseBody ApiUtil<String> userBookmarkDelete(@PathVariable Integer id) {
        System.out.println("id: " + id);
        User user = (User) session.getAttribute("sessionUser");
        if (user == null) {
            return new ApiUtil<String>(false, "로그인 안됨");
        }
        Integer sucuess = bookmarkService.유저북마크제거(id, user.getId());
        System.out.println("테스트" + sucuess);
        if (sucuess == 1) {
            return new ApiUtil<String>(true, "북마크 제거 성공");
        } else {
            return new ApiUtil<String>(false, "북마크 제거 실패");
        }
    }

}

package shop.mtcoding.blogv2.bookmark;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {


    @Autowired
    BookmarkRepository bookmarkRepository;

    public List<Bookmark> getAllBookmarksByUser(Integer userId) {
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(1); // 유저 ID를 기반으로 북마크를 조회합니다.
        return bookmarks;
    }


    

    
}

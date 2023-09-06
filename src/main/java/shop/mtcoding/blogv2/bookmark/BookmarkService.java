package shop.mtcoding.blogv2.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeRepository;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.resume.ResumeRepository;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserRepository;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private NoticeRepository noticeRepository;


    public Integer 유저북마크추가(Integer id, Integer id2) {
        try {
            // 북마크 추가 로직을 구현합니다.
            // 예를 들어, 데이터베이스에 북마크 정보를 저장하는 코드일 수 있습니다.
            // 이 예제에서는 성공적으로 북마크를 추가한 경우 1을 반환합니다.
            // 실패한 경우 0을 반환하거나 예외를 throw할 수 있습니다.

            // 북마크 추가 로직을 여기에 구현하세요.

            // 성공적으로 북마크를 추가한 경우 1 반환
            return 1;
        } catch (Exception e) {
            // 북마크 추가 중에 예외가 발생한 경우 0 반환 또는 예외 처리
            e.printStackTrace();
            return 0;
        }
    }

    public Integer 유저북마크제거(Integer id, Integer id2) {
        try {
            // 북마크 추가 로직을 구현합니다.
            // 예를 들어, 데이터베이스에 북마크 정보를 저장하는 코드일 수 있습니다.
            // 이 예제에서는 성공적으로 북마크를 추가한 경우 1을 반환합니다.
            // 실패한 경우 0을 반환하거나 예외를 throw할 수 있습니다.

            // 북마크 추가 로직을 여기에 구현하세요.

            // 성공적으로 북마크를 추가한 경우 1 반환
            return 1;
        } catch (Exception e) {
            // 북마크 추가 중에 예외가 발생한 경우 0 반환 또는 예외 처리
            e.printStackTrace();
            return 0;
        }
    }
}

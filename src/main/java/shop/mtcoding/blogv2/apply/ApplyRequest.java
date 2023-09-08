package shop.mtcoding.blogv2.apply;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.resume.Resume;
import shop.mtcoding.blogv2.user.User;

public class ApplyRequest {

    @Getter
    @Setter
    public static class UpdateDTO {
        private Boolean status;
        private Notice notice;
        private User user;
        private Resume resume;

    }

    @Getter
    @Setter
    public static class ApplyDTO {
        private Boolean status;
        private Integer noticeId;
        private Integer userId;
        private Integer resumeId;

    }

}

package shop.mtcoding.blogv2.scrap;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.user.User;

public class ScrapRequest {
    @Getter
    @Setter
    public static class UserScrapDTO {
        private User user;
        private Notice notice;

    }

}

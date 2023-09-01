package shop.mtcoding.blogv2.user;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class JoinDTO {
        private String username;
        private String password;
        private String name;
        private String telNumber;
        private String registNumber;
        private String email;
        private String picUrl;
        private boolean distingush;
        private Timestamp createdAt;
    }

    @Getter
    @Setter
    public static class LoginDTO {
        private String usernmae;
        private String password;
    }

}

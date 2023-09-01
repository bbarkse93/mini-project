package shop.mtcoding.blogv2.user;



import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter@Setter
    public static class JoinDTO {
        private String username;
        private String password;
        private String personalName;
        private String companyName;
        private String personalBirth;
        private String phoneNumber;
        private String proprietaryNumber;
        private String email;
        private Integer personalid;
        private Integer companyid;
        private Timestamp createdAt;
    }
    
    @Getter@Setter
    public static class LoginDTO {
        private String usernmae;
        private String password;
    }



      

}

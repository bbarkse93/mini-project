package shop.mtcoding.blogv2.notice;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class NoticeRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        private String title;
        private String companyName;
        private String companyEmail;
        private String phoneNumber;
        private String companyInfo;
        private MultipartFile companyPic;
        private String location;
        private String intake;
        private String pay;
        private String period;
        private String qualification;
        private Timestamp createdAt;
        private List<String> wishSkills;
        private List<String> wishDutys;

    }

    @Getter
    @Setter
    public static class UpdateDTO {
        private String title;
        private String companyInfo;
        private String location;
        private String intake;
        private String pay;
        private String period;
        private String qualification;
        private Timestamp createdAt;
        private String[] wishSkills; // 문자열 배열로 변경
        private String[] wishDutys; // 문자열 배열로 변경
    }
}

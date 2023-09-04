package shop.mtcoding.blogv2.notice;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blogv2.wishduty.WishDuty;
import shop.mtcoding.blogv2.wishskill.WishSkill;

public class NoticeRequest {

    @Getter
    @Setter
    public static class SaveDTO {
        private String title;
        private String companyName;
        private String companyEmail;
        private String phoneNumber;
        private String companyInfo;
        private String companyPicUrl;
        private String location;
        private String intake;
        private String pay;
        private String period;
        private String qualification;
        private Timestamp createdAt;
        private List<WishSkill> wishSkills;
        private List<WishDuty> wishDutys;
    }

    @Getter
    @Setter
    public static class UpdateDTO {
        private String title;
        private String companyName;
        private String companyEmail;
        private String phoneNumber;
        private String companyInfo;
        private String companyPicUrl;
        private String location;
        private String intake;
        private String pay;
        private String period;
        private String qualification;
        private Timestamp createdAt;
        private List<WishSkill> wishSkills;
        private List<WishDuty> wishDutys;
    }
}

package shop.mtcoding.blogv2.resume;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResumeRequest {

  @Getter
  @Setter
  public static class UpdateDTO {
    private String title;
    private String coverLetter;
    private Timestamp createdAt;
    private String[] wishSkills; // 문자열 배열로 변경
    private String[] wishDutys; // 문자열 배열로 변경
  }

  @Getter
  @Setter
  public static class SaveDTO {
    private Integer id;
    private String title;
    private String personalName;
    private String personalEmail;
    private String phoneNumber;
    private MultipartFile personalPic;
    private String coverLetter;
    private Timestamp createdAt;
    private List<String> wishSkills;
    private List<String> wishDutys;
  }

  @Getter
  @Setter
  public static class ResumeDTO {
    private String title;
    private String personalName;
    private String personalEmail;
    private String phoneNumber;
    private String coverLetter;

  }

}

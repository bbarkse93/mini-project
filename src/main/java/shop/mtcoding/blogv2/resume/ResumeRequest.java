package shop.mtcoding.blogv2.resume;

import java.sql.Timestamp;

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
    private String personalEmail;
    private String phoneNumber;
    private String coverLetter;
    private String personalPicUrl;
    private Timestamp createdAt;
  }

  @Getter
  @Setter
  public static class SaveDTO {
    private String title;
    private String personalName;
    private String personalEmail;
    private String personalPicUrl;
    private String phoneNumber;
    private String coverLetter;
    private Timestamp createdAt;
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

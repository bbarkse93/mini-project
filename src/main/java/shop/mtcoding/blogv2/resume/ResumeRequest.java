package shop.mtcoding.blogv2.resume;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

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

}

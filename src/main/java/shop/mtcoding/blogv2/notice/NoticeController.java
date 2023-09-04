package shop.mtcoding.blogv2.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // 기업회원정보(디폴트화면)
    @GetMapping("/companyNoticeList")
    public String companyNoticeList() {
        return "/company/companyNoticeList";
    }

    // 공고상세보기 view
    @GetMapping("/noticeDetail")
    public String detail() {
        return "/notice/noticeDetail";
    }

    // 채용공고등록 view
    @GetMapping("/noticeWrite")
    public String noticeWrite() {
        return "/notice/noticeWrite";
    }

    // 채용등록하기
    @PostMapping("/noticeSave")
    public String noticeSave(NoticeRequest.SaveDTO saveDTO) {
        noticeService.채용등록(saveDTO);
        return "redirect:/companyNoticeList";
    }

    // 채용삭제하기
    @PostMapping("/companyNoticeList/1/delete")
    public String delete(Integer id) {
        noticeService.채용삭제(id);
        return "redirect:/companyNoticeList";
    }
@GetMapping("/notices")
    public String getAllNotices(HttpServletRequest request) {
        
        List<Notice> notices = noticeService.getAllNotices();

        request.setAttribute("notices", notices);

        return "notices"; // 머스태치 템플릿 파일의 경로
    }

}

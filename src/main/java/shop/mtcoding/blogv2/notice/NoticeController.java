package shop.mtcoding.blogv2.notice;

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

}

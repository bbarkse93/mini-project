package shop.mtcoding.blogv2.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blogv2.board.BoardRequest;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    // 기업회원정보(디폴트화면)
    @GetMapping("/companyNoticeList")
    public String companyNoticeListForm() {
        return "/company/companyNoticeList";
    }

    // 공고상세보기 view
    @GetMapping("/noticeDetail")
    public String noticeDetailForm() {
        return "/notice/noticeDetail";
    }

    // 채용공고등록 view
    @GetMapping("/noticeWrite")
    public String noticeWriteForm() {
        return "/notice/noticeWrite";
    }

    // 채용등록하기
    @PostMapping("/noticeSave")
    public String noticeSave(NoticeRequest.SaveDTO saveDTO) {
        noticeService.채용등록(saveDTO);
        return "redirect:/companyNoticeList";
    }

    // 채용삭제하기
    @PostMapping("/companyNoticeList/{id}/delete")
    public String noticeDelete(@PathVariable Integer id) {
        noticeService.채용삭제(id);
        return "redirect:/companyNoticeList";
    }

    // 채용수정하기 view
    // @GetMapping("/noticeUpdate/{id}")
    // public String noticeUpdateForm(@PathVariable Integer id, Model model) {
    // Notice notice = noticeService.상세보기(id);
    // model.addAttribute("notice", notice);
    // return "/notice/noticeUpdateForm";
    // }

    @GetMapping("noticeUpdate/{id}")
    public String noticeUpdateForm(@PathVariable Integer id) {
        noticeService.수정화면(id);
        return "notice/noticeUpdate";
    }

    @PostMapping("noticeUpdate/{id}/update")
    public String noticeUpdate(@PathVariable Integer id, NoticeRequest.UpdateDTO updateDTO) {
        noticeService.채용수정(id, updateDTO);
        return "redirect:/companyNoticeList";
    }

    // @PostMapping("/companyNoticeList/{id}/update")
    // public String noticeUpdate(@PathVariable Integer id, NoticeRequest.UpdateDTO
    // updateDTO) {
    // noticeService.채용수정(id, updateDTO);
    // return "redirect:/companyNoticeList";
    // }

    // @PostMapping("/board/{id}/update")
    // public String update(@PathVariable Integer id, BoardRequest.UpdateDTO
    // updateDTO) {
    // // where 데이터, body, session값
    // boardService.게시글수정하기(id, updateDTO);
    // return "redirect:/board/" + id;
    // }

    // @GetMapping("/board/{id}/updateForm")
    // public String updateForm(@PathVariable Integer id, Model model) {
    // Board board = boardService.상세보기(id);
    // model.addAttribute("board", board); // request에 담는 것과 동일
    // return "board/updateForm";
    // }

}

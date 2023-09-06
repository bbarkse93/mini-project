package shop.mtcoding.blogv2.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/csForm")
    public String customerService(HttpServletRequest request) {
        List<Board> boards = boardService.문의조회();
        request.setAttribute("boards", boards);
        return "/board/csForm";
    }

    @GetMapping("/csSaveForm")
    public String customerServiceWrite() {
        return "/board/csSaveForm";
    }

    @PostMapping("/board/save")
    public String customerServiceSave(BoardRequest.SaveDTO saveDTO) {
        boardService.문의작성(saveDTO, 1);
        return "redirect:/csForm";
    }

}

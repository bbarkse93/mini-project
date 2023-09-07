package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/board/csUpdateForm/{id}")
    public String csUpdateForm(@PathVariable Integer id, HttpServletRequest request) {
        Optional<Board> board = boardService.수정화면(id);
        request.setAttribute("board", board);
        return "board/csUpdateForm";
    }

    @PostMapping("/board/{id}/update")
    public String csUpdate(@PathVariable Integer id, BoardRequest.UpdateDTO updateDTO, HttpServletRequest request) {
        boardService.문의수정(updateDTO, id);
        return "redirect:/csForm";
    }
}

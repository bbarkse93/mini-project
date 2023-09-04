package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/customerService")
    public String customerService() {
        return "/board/customerService";
    }

    @GetMapping("/customerServiceWrite")
    public String customerServiceWrite() {
        return "/board/customerServiceWrite";
    }

    @PostMapping("/board/save")
    public String customerServiceSave(BoardRequest.SaveDTO saveDTO) {
        boardService.문의작성(saveDTO, 1);
        return "redirect:/customerService";
    }

    
}

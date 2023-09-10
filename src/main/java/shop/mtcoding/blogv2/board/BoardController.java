package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.user.User;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private HttpSession session;

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

    // @PostMapping("/api/board/{id}/delete")
    // public @ResponseBody String delete(@PathVariable Integer id) {
        //     User sessionUser = (User) session.getAttribute("sessionUser");
        //     if (sessionUser == null) {
            //         throw new MyApiException("인증되지 않았습니다.");
            //     }
            
            //     boardService.deleteById(id); 
            
    //     return Script.href("/csForm","댓글삭제 완료");
    // }
    
    //  고객센터 글 삭제하기
    @PostMapping("/api/board/{id}/delete")
    public ApiUtil<String> delete(@RequestBody @PathVariable Integer id) {
    User sessionUser = (User) session.getAttribute("sessionUser");
    if (sessionUser == null) {
        throw new MyApiException("인증되지 않았습니다.");
    }

    boardService.deleteById(id);

    
    return new ApiUtil<String>(true, "문의 삭제 완료");
    
}
}

package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.board.BoardRequest.UpdateDTO;
import shop.mtcoding.blogv2.user.User;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 문의작성(BoardRequest.SaveDTO saveDTO, int sessionUserId) {
        Board board = Board.builder()
                .title(saveDTO.getTitle())
                .content(saveDTO.getContent())
                .email(saveDTO.getEmail())
                .phoneNumber(saveDTO.getPhoneNumber())
                .user(User.builder().id(sessionUserId).build())
                .build();
        boardRepository.save(board);

    }

    public List<Board> 문의조회() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    @Transactional
    public Optional<Board> 문의수정(UpdateDTO updateDTO, Integer id) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            board.setTitle(updateDTO.getTitle());
            board.setEmail(updateDTO.getEmail());
            board.setPhoneNumber(updateDTO.getPhoneNumber());
            board.setContent(updateDTO.getContent());
        } else {
            throw new MyException(id + "는 찾을 수 없습니다");
        }
        return boardOP;
    }

    public Optional<Board> 수정화면(Integer id) {
        return boardRepository.findById(id);

    }

    @Transactional
    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }

}

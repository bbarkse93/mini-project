package shop.mtcoding.blogv2.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println("test" + board);

    }

    public List<Board> 문의조회() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
}

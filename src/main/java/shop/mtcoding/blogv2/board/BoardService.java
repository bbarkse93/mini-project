package shop.mtcoding.blogv2.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.user.User;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

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
}

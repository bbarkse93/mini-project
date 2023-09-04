package shop.mtcoding.blogv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.user.UserRequest.LoginDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        User user = User.builder()
                .username(joinDTO.getUsername())
                .password(joinDTO.getPassword())
                .name(joinDTO.getName())
                .telNumber(joinDTO.getTelNumber())
                .registNumber(joinDTO.getRegistNumber())
                .email(joinDTO.getEmail())
                .picUrl(joinDTO.getPicUrl())
                .distinguish(joinDTO.isDistingish())
                .build();
        userRepository.save(user);

    }

    public User 로그인(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        
        if (user == null) {
            throw new MyException("아이디가 틀렸습니다");
        }

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new MyException("비밀번호가 틀렸습니다");
        }
        
        return user;
    }

}

package shop.mtcoding.blogv2.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .distinguish(joinDTO.isDistinguish())
                .build();
        userRepository.save(user);

    }

}

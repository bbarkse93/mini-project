package shop.mtcoding.blogv2.user;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.user.UserRequest.LoginDTO;
import shop.mtcoding.blogv2.user.UserRequest.UpdateDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpSession session;

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

    @Transactional
    public User 회원수정(UpdateDTO updateDTO, Integer id) {

        User user = userRepository.findById(id).get();

        user.setPassword(updateDTO.getPassword());
        user.setName(updateDTO.getName());
        user.setTelNumber(updateDTO.getTelNumber());

        return user;
    }

    public User 회원정보보기(Integer id) {
        return userRepository.findById(id).get();
    }

    public ApiUtil<String> checkusername(String username) {
       User user = userRepository.findByUsername(username);
      
       if (user != null) {
        throw new MyApiException("유저네임을 사용할 수 없습니다");
        
       }
       return new ApiUtil<String>(true, "유저네임을 사용할 수 있습니다");
    }

    public String 개인정보구분() {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser != null && sessionUser.isDistinguish()) {
            return "/userInformation"; // 개인회원 정보 페이지로 리다이렉트
        } else {
            return "/companyNoticeList"; // 기업회원 정보 페이지로 리다이렉트
        }
    }

}

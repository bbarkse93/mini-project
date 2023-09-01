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
                .personalName(joinDTO.getPersonalName())
                .companyName(joinDTO.getCompanyName())
                .email(joinDTO.getEmail())
                .personalBirth(joinDTO.getPersonalBirth())
                .proprietaryNumber(joinDTO.getProprietaryNumber())
                .personalBirth(joinDTO.getPersonalBirth())
                .phoneNumber(joinDTO.getPhoneNumber())
                .build();
        userRepository.save(user);

    }

 


}

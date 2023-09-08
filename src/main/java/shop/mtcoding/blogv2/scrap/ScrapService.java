package shop.mtcoding.blogv2.scrap;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.notice.Notice;
import shop.mtcoding.blogv2.notice.NoticeRepository;
import shop.mtcoding.blogv2.scrap.ScrapRequest.UserScrapDTO;
import shop.mtcoding.blogv2.user.User;
import shop.mtcoding.blogv2.user.UserRepository;

@Service
public class ScrapService {
    @Autowired
    private ScrapRepository scrapRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Scrap> getScrapsByUserId(Integer userId) {
        return scrapRepository.findAll(1);
    }

    //유저관심기업 서비스
    public void 관심기업(Integer id, UserScrapDTO userScrapDTO) {  
        Optional<Notice> optionalNotice = noticeRepository.findById(id);
        Optional<User> optionalUser = userRepository.findById(id);
        
      
            Notice notice = optionalNotice.get();
            User user = optionalUser.get();
            
            
           
            
            
                Scrap scrap = new Scrap();
                scrap.setNotice(notice);
                scrap.setUser(user);
                
                scrapRepository.save(scrap);
                
                // 관심 기업이 성공적으로 저장되었을 경우, 추가 로직을 수행할 수 있습니다.
        
}
}

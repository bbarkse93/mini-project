package shop.mtcoding.blogv2.scrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScrapService {
    @Autowired
    private ScrapRepository scrapRepository;

    public List<Scrap> getScrapsByUserId(Integer userId) {
        return scrapRepository.findAll(1);
    }
}

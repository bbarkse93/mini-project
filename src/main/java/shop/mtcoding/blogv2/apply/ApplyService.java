package shop.mtcoding.blogv2.apply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    @Autowired 
    private ApplyRepository applyRepository;

    // 상태에 따라 지원 현황 조회
    public List<Apply> getAppliesByStatus(boolean status) {
        return applyRepository.findByStatus(status);
    }
}

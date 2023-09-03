package shop.mtcoding.blogv2.apply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;


    // 개인 지원 목록 조회
    @GetMapping("/individual")
    public List<Apply> getIndividualApplies() {
        return applyService.getAppliesByStatus(true);
    }

    @GetMapping("/userApplyStatus")
public  ModelAndView getUserApplyStatus() {
    ModelAndView modelAndView = new ModelAndView("user/userApplyStatus"); // 뷰 이름 설정
    List<Apply> individualApplies = applyService.getAppliesByStatus(true);
    modelAndView.addObject("individual", individualApplies); // 머스태치 템플릿에서 사용할 데이터 추가
    return modelAndView;
}

  
    
}

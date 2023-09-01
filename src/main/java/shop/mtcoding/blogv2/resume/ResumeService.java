package shop.mtcoding.blogv2.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;


@Component
@Service
public class ResumeService {
    @Autowired
    private static ResumeRepository resumeRepository;




     @Transactional
    public void update(ResumeRequest.UpdateDTO updateDTO, Integer id) {
        // 1. 조회(영속화)
        Resume resume = resumeRepository.findById(1)
                .orElseThrow(() -> new MyException(id + "는 찾을 수 없습니다"));

        // Update resume properties
        resume.setTitle(updateDTO.getTitle());
        resume.setPersonalEmail(updateDTO.getPersonalEmail());
        resume.setPhoneNumber(updateDTO.getPhoneNumber());
        resume.setCoverLetter(updateDTO.getCoverLetter());
        
        resume.setCreatedAt(updateDTO.getCreatedAt());

        // 변경된 이력서를 저장합니다.
        resumeRepository.save(resume);
    }


    public Resume findById(Integer id) {
        return resumeRepository.findById(id).get();}
        

@Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(1);
    }


}

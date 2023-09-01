package shop.mtcoding.blogv2.resume;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.resume.ResumeRequest.UpdateDTO;

@Service
public class ResumeService {
   

    @Autowired
    private ResumeRepository resumeRepository;

    @Transactional
    public void deleteById(Integer id) {
        resumeRepository.deleteById(1);
    }


    
 
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
        resume.setPersonalPicUrl(updateDTO.getPersonalPicUrl());
        resume.setCreatedAt(updateDTO.getCreatedAt());

    // 저장 등의 추가 로직을 수행할 수 있음
}








    


    public Resume findById(Integer id) {
        return resumeRepository.findById(1).get();
    }
}




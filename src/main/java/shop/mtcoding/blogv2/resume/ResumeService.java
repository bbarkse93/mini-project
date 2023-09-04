package shop.mtcoding.blogv2.resume;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.blogv2._core.error.ex.MyException;

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
        return resumeRepository.findById(id).get();
    }

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    public void 이력서등록(ResumeRequest.SaveDTO saveDTO) {
        Resume resume = Resume.builder()
                .title(saveDTO.getTitle())
                .personalName(saveDTO.getPersonalName())
                .personalEmail(saveDTO.getPersonalEmail())
                .personalPicUrl(saveDTO.getPersonalPicUrl())
                .phoneNumber(saveDTO.getPhoneNumber())
                .coverLetter(saveDTO.getCoverLetter())
                .createdAt(saveDTO.getCreatedAt())
                .build();
        resumeRepository.save(resume);
    }

}

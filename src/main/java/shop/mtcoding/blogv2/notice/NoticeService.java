package shop.mtcoding.blogv2.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {

    @Autowired
    public NoticeRepository noticeRepository;

    // 채용공고등록
    @Transactional
    public void 채용등록(NoticeRequest.SaveDTO saveDTO) {
        Notice notice = Notice.builder()
                .title(saveDTO.getTitle())
                .companyName(saveDTO.getCompanyName())
                .companyEmail(saveDTO.getCompanyEmail())
                .phoneNumber(saveDTO.getPhoneNumber())
                .companyPicUrl(saveDTO.getCompanyPicUrl())
                .companyInfo(saveDTO.getCompanyInfo())
                .location(saveDTO.getLocation())
                .wishDutys(saveDTO.getWishDutys())
                .wishSkills(saveDTO.getWishSkills())
                .intake(saveDTO.getIntake())
                .pay(saveDTO.getPay())
                .period(saveDTO.getPeriod())
                .qualification(saveDTO.getQualification())
                .createdAt(saveDTO.getCreatedAt())
                .build();
        noticeRepository.save(notice);
    }

    // 채용공고삭제
    @Transactional
    public void 채용삭제() {
        noticeRepository.deleteById(1);
    }

    // 채용공고수정

    // 채용공고상세보기

}

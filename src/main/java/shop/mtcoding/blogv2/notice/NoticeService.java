package shop.mtcoding.blogv2.notice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.notice.NoticeRequest.UpdateDTO;

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
    public void 채용삭제(Integer id) {
        noticeRepository.deleteById(1);
    }

    @Transactional
    public void 수정화면(@PathVariable Integer id) {
        noticeRepository.findById(1);
    }

    @Transactional
    public void 채용수정(Integer id, UpdateDTO updateDTO) {
        Optional<Notice> noticeOP = noticeRepository.findById(id);
        if (noticeOP.isPresent()) {
            Notice notice = noticeOP.get();
            notice.setTitle(updateDTO.getTitle());
            notice.setCompanyName(updateDTO.getCompanyName());
            notice.setCompanyEmail(updateDTO.getCompanyEmail());
            notice.setPhoneNumber(updateDTO.getPhoneNumber());
            notice.setCompanyPicUrl(updateDTO.getCompanyPicUrl());
            notice.setCompanyInfo(updateDTO.getCompanyInfo());
            notice.setLocation(updateDTO.getLocation());
            notice.setWishDutys(updateDTO.getWishDutys());
            notice.setWishSkills(updateDTO.getWishSkills());
            notice.setIntake(updateDTO.getIntake());
            notice.setPay(updateDTO.getPay());
            notice.setPeriod(updateDTO.getPeriod());
            notice.setQualification(updateDTO.getQualification());
            notice.setCreatedAt(updateDTO.getCreatedAt());
        } else {
            throw new MyException(id + "는 찾을 수 없습니다");
        }

        // public Notice 상세보기(@PathVariable Integer id) {
        // Optional<Notice> noticeOP = noticeRepository.findById(1);
        // if (noticeOP.isPresent()) {
        // return noticeOP.get();
        // } else {
        // throw new MyApiException(id + "는 찾을 수 없습니다");
        // }
        // }

        // @Transactional
        // public void 채용수정(Integer id, UpdateDTO updateDTO) {
        // Optional<Notice> noticeOP = noticeRepository.findById(id);

        // }

        // @Transactional
        // public void 게시글수정하기(Integer id, UpdateDTO updateDTO) {
        // Optional<Board> boardOP = boardRepository.findById(id);
        // if (boardOP.isPresent()) {
        // Board board = boardOP.get();
        // board.setTitle(updateDTO.getTitle());
        // board.setContent(updateDTO.getContent());
        // } else {
        // throw new MyException(id + "는 찾을 수 없습니다");
        // }
        // } // flush (더티체킹)

        // 채용공고수정

        // 채용공고상세보기

    }
}
# 스프링부트 블로그 V2

## 기획 끝

## 화면설계 끝

## 화면코드 끝

## 개발 기간

- 2023.08.24. ~ 2023.09.06.

## 기술 스택

- JDK 11
- Spring Boot 2.7.5
- 프로덕션 MySQL DB
- 테스트 H2 DB
- Mustache
- Java Script
- JPA

## 1단계 기능

- (특징 : 자바스크립트, 예외처리)
- 회원가입
- 로그인
- 회원정보 보기
  - 개인회원 정보 수정하기
    - 이력서 등록
    - 이력서 조회
    - 이력서 수정
    - 이력서 삭제
    - 이력서 상세보기
    - 지원현황 조회
    - 관심 카테고리
    - 추천 공고 보기
    - 채용 공고 북마크
  - 기업회원 정보 수정하기
    - 채용공고 등록
    - 채용공고 조회
    - 채용공고 수정
    - 채용공고 삭제
    - 채용공고 상세보기
    - 관심 이력서 등록
- 고객센터
  - 게시글 등록
  - 게시글 조회 (AJAX)
  - 게시글 수정
  - 게시글 삭제
- 예외처리

## 2단계 기능

- 유저네임 중복체크 (AJAX)
- 검색하기
- 회원가입시 사진등록

## 3단계 기능

- 섬머노트
- 필터(Filter)
- 유효성검사 자동화

## 테이블 쿼리

```sql
CREATE database blogdb;

USE blogdb;

-- User Table
CREATE TABLE user_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP,
    distinguish BOOLEAN,
    email VARCHAR(20),
    name VARCHAR(20),
    password VARCHAR(60) NOT NULL,
    pic_url VARCHAR(255),
    regist_number VARCHAR(10),
    tel_number VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    UNIQUE KEY (username)
);

-- Skill Table
CREATE TABLE skill_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(255)
);

-- Duty Table
CREATE TABLE duty_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    duty_name VARCHAR(255)
);

-- Resume Table
CREATE TABLE resume_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cover_letter VARCHAR(255),
    created_at TIMESTAMP,
    edu VARCHAR(255),
    personal_email VARCHAR(255),
    personal_name VARCHAR(255),
    personal_pic_url VARCHAR(255),
    phone_number VARCHAR(255),
    title VARCHAR(100),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

-- Edu Table
CREATE TABLE edu_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    grade VARCHAR(255),
    resume_id INT,
    FOREIGN KEY (resume_id) REFERENCES resume_tb (id)
);

-- Notice Table
CREATE TABLE notice_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    company_email VARCHAR(255),
    company_info VARCHAR(255),
    company_name VARCHAR(255),
    company_pic_url VARCHAR(255),
    created_at TIMESTAMP,
    intake VARCHAR(255),
    location VARCHAR(255),
    pay VARCHAR(255),
    period VARCHAR(255),
    phone_number VARCHAR(255),
    qualification VARCHAR(255),
    title VARCHAR(100) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

-- Event Table
CREATE TABLE event_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_address VARCHAR(255),
    event_pic_url VARCHAR(255)
);

-- Location Table
CREATE TABLE location_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    location_name VARCHAR(255),
    notice_id INT,
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id)
);

-- Board Table
CREATE TABLE board_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    content TEXT,
    created_at DATE,
    email VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    title VARCHAR(100) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

-- Apply Table
CREATE TABLE apply_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    status BOOLEAN,
    notice_id INT,
    resume_id INT,
    user_id INT,
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id),
    FOREIGN KEY (resume_id) REFERENCES resume_tb (id),
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

-- Wish Duty Table
CREATE TABLE wish_duty_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    duty_id INT,
    notice_id INT,
    resume_id INT,
    FOREIGN KEY (duty_id) REFERENCES duty_tb (id),
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id),
    FOREIGN KEY (resume_id) REFERENCES resume_tb (id)
);

-- Wish Skill Table
CREATE TABLE wish_skill_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    notice_id INT,
    resume_id INT,
    skill_id INT,
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id),
    FOREIGN KEY (resume_id) REFERENCES resume_tb (id),
    FOREIGN KEY (skill_id) REFERENCES skill_tb (id)
);

-- Bookmark Table
CREATE TABLE bookmark_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    notice_id INT,
    resume_id INT,
    user_id INT,
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id),
    FOREIGN KEY (resume_id) REFERENCES resume_tb (id),
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

-- Scrap Table
CREATE TABLE scrap_tb (
    id INT AUTO_INCREMENT PRIMARY KEY,
    notice_id INT,
    user_id INT,
    FOREIGN KEY (notice_id) REFERENCES notice_tb (id),
    FOREIGN KEY (user_id) REFERENCES user_tb (id)
);

```

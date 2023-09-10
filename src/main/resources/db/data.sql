-- 유저 더미
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('ssar', '$2a$10$00uiUIAb.JpCPGeltpBhHupRj0gkm7/k3ojUyHV/BqRBI.tPfHWzS', '쌀', '010-1234-5678','ssar@nate.com', '1995-01-01', 'basic.jpg', true, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('cos', '$2a$10$00uiUIAb.JpCPGeltpBhHupRj0gkm7/k3ojUyHV/BqRBI.tPfHWzS', '코스', '010-1234-5678', 'cos@nate.com', '1995-01-01', 'basic.jpg', true, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('company1', '$2a$10$00uiUIAb.JpCPGeltpBhHupRj0gkm7/k3ojUyHV/BqRBI.tPfHWzS', '회사1', '010-1234-5678', 'cos@nate.com', '000000000', 'basic.jpg', false, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('company2', '$2a$10$00uiUIAb.JpCPGeltpBhHupRj0gkm7/k3ojUyHV/BqRBI.tPfHWzS', '회사z', '010-1234-5678', 'cos@nate.com', '000000000', 'basic.jpg', false, NOW());

-- 이력서
insert into resume_tb(user_id, title,personal_name, personal_email, phone_number, cover_letter, personal_pic_url, created_at, edu) 
values(1, '이력서1', '쌀', 'ssar@nate.com', '010-1234-5678', '자기소개서', 'basic.jpg', NOW(), '고졸');
insert into resume_tb(user_id, title,personal_name, personal_email, phone_number, cover_letter, personal_pic_url, created_at, edu) 
values(1, '이력서2', '쌀', 'ssar@nate.com', '010-1234-5678', '자기소개서', 'basic.jpg', NOW(), '고졸');
insert into resume_tb(user_id, title, personal_name, personal_email, phone_number, cover_letter, personal_pic_url, created_at, edu) 
values(2, '이력서3', '코스', 'cos@nate.com', '010-1111-5678', '자기소개서', 'basic.jpg', NOW(), '대졸');
insert into resume_tb(user_id, title, personal_name, personal_email, phone_number, cover_letter, personal_pic_url, created_at, edu) 
values(2, '이력서4', '코스', 'cos@nate.com', '010-1111-5678', '자기소개서', 'basic.jpg', NOW(), '대졸');

 -- 채용공고 
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3,'Lomin@naver.com','로민정보','Lomin','company.jpg',NOW(),'33명','서울','3373만원','2023.10.31까지','010-1234-5678','개발 경력을 보유하신 분','웹 프론트엔드 개발자');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3,'newsjelly@naver.com','뉴스젤리정보','newsjelly','뉴스젤리.png',NOW(),'33명','서울','3373만원','2023.09.22까지','010-1234-1313','데이터 시각화 관련 웹 프로젝트의 프론트엔드 개발','웹 newsjelly 개발자');
insert into notice_tb(user_id, company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values(3,'highdev@naver.com','하이데브정보','highdev','highdev.png',NOW(),'1명','부산','업계연봉최고대우','상시','010-1234-1514','VAN 서버 관리','DBA/DB 개발자');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3,'tnh@naver.com','티엔에이치정보','tnh','tnh.png',NOW(),'2명','부산','8000만원','상시','010-1234-1511',' React를 이용하여 당사 프런트 서비스 및 솔루션 개발','React 개발자');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3,'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구company2');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구company2');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(3, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구company2');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구company2');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구company2');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(user_id, company_email, company_info, company_name, company_pic_url, created_at, intake, location, pay, period, phone_number, qualification, title)
values(4, 'naver@nate.com','네이버정보','네이버','company.jpg',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');

-- 지원하기 테이블 
INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (1, 1, false, 1); 
INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (2, 1, true, 1); 
INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (3, 1, true, 1); 

-- 공고 스크랩
INSERT INTO scrap_tb (notice_id,user_id) VALUES (1,1);
INSERT INTO scrap_tb (notice_id,user_id) VALUES (2,1);
INSERT INTO scrap_tb (notice_id,user_id) VALUES (3,1);

-- 이벤트
INSERT INTO event_tb (event_pic_url, event_address) VALUES ('eventimage.png','www.naver.com');
INSERT INTO event_tb (event_pic_url, event_address) VALUES ('youth.png','apply.jobaba.net/bsns/bsnsDetailView.do?bsnsSeq=2260');
INSERT INTO event_tb (event_pic_url, event_address) VALUES ('idea.png','www.newjobidea.com');
INSERT INTO event_tb (event_pic_url, event_address) VALUES ('boostcamp.png','apply.connect.or.kr/connect/applyDetail?annoId=20009497');

-- 기술스택 코드 테이블
insert into skill_tb(skill_name) values('Java');
insert into skill_tb(skill_name) values('MySQL');
insert into skill_tb(skill_name) values('JavaScript');
insert into skill_tb(skill_name) values('Spring');
insert into skill_tb(skill_name) values('HTML/CSS');
insert into skill_tb(skill_name) values('React');
insert into skill_tb(skill_name) values('Flutter');
insert into skill_tb(skill_name) values('Unity');

-- 공고 원하는 기술 스택
insert into wish_skill_tb(skill_id,notice_id) values(1,1);
insert into wish_skill_tb(skill_id,notice_id) values(2,1);
insert into wish_skill_tb(skill_id,notice_id) values(1,2);
insert into wish_skill_tb(skill_id,notice_id) values(3,2);
insert into wish_skill_tb(skill_id,notice_id) values(5,3);
insert into wish_skill_tb(skill_id,notice_id) values(6,3);

-- 이력서 보유 기술스택
insert into wish_skill_tb(skill_id,resume_id) values(1,1);
insert into wish_skill_tb(skill_id,resume_id) values(2,1);
insert into wish_skill_tb(skill_id,resume_id) values(3,2);
insert into wish_skill_tb(skill_id,resume_id) values(4,2);
insert into wish_skill_tb(skill_id,resume_id) values(5,3);
insert into wish_skill_tb(skill_id,resume_id) values(6,3);

-- 직무 코드 테이블
insert into duty_tb(duty_name) values('서버 개발자');
insert into duty_tb(duty_name) values('백엔드 개발자');
insert into duty_tb(duty_name) values('프론트엔드 개발자');
insert into duty_tb(duty_name) values('안드로이드 개발자');
insert into duty_tb(duty_name) values('ios개발자');
insert into duty_tb(duty_name) values('풀스택 개발자');
insert into duty_tb(duty_name) values('빅데이터 엔지니어');
insert into duty_tb(duty_name) values('임베디드 개발자');
insert into duty_tb(duty_name) values('Devops 개발자');

-- 공고 원하는 직무
insert into wish_duty_tb(duty_id,notice_id) values(2,1);
insert into wish_duty_tb(duty_id,notice_id) values(1,2);
insert into wish_duty_tb(duty_id,notice_id) values(3,2);
insert into wish_duty_tb(duty_id,notice_id) values(5,3);
insert into wish_duty_tb(duty_id,notice_id) values(1,1);
insert into wish_duty_tb(duty_id,notice_id) values(6,3);

-- 이력서 원하는 직무
insert into wish_duty_tb(duty_id,resume_id) values(1,1);
insert into wish_duty_tb(duty_id,resume_id) values(2,1);
insert into wish_duty_tb(duty_id,resume_id) values(3,2);
insert into wish_duty_tb(duty_id,resume_id) values(4,2);
insert into wish_duty_tb(duty_id,resume_id) values(5,3);
insert into wish_duty_tb(duty_id,resume_id) values(6,3);

-- 학력 코드 테이블
insert into edu_tb(grade) values('고졸');
insert into edu_tb(grade) values('대학교 재학');
insert into edu_tb(grade) values('대학교 휴학');
insert into edu_tb(grade) values('대학교 중퇴');
insert into edu_tb(grade) values('대학교 졸업');
insert into edu_tb(grade) values('석사');
insert into edu_tb(grade) values('박사');
insert into edu_tb(grade) values('기타');

-- 지역 code테이블
insert into location_tb(location_name) values('서울');
insert into location_tb(location_name) values('대전');
insert into location_tb(location_name) values('대구');
insert into location_tb(location_name) values('광주');
insert into location_tb(location_name) values('부산');
insert into location_tb(location_name) values('제주');

-- 고객센터 
insert into board_tb(user_id, title, email, phone_number, content, created_at) values(1, '문의 사항','ssar@nate.com', '010-1234-5678', '문의합니다', NOW());
insert into board_tb(user_id, title, email, phone_number, content, created_at) values(2, '서비스 문의','cos@nate.com', '010-1111-2222', '문의합니다', NOW());
insert into board_tb(user_id, title, email, phone_number, content, created_at) values(2, '서비스 문의','cos@nate.com', '010-1111-2222', '문의합니다', NOW());

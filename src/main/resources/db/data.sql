insert into board_tb(title, email, phone_number, content, created_at) values('문의 사항','ssar@nate.com', '010-1234-5678', '문의합니다', NOW());
insert into board_tb(title, email, phone_number, content, created_at) values('서비스 문의','cos@nate.com', '010-1111-2222', '문의합니다', NOW());
insert into board_tb(title, email, phone_number, content, created_at) values('서비스 문의','cos@nate.com', '010-1111-2222', '문의합니다', NOW());


insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('ssar', '1234', '쌀', '010-1234-5678','ssar@nate.com', '1995-01-01', 'basic.png', true, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('cos', '1234', '코스', '010-1234-5678', 'cos@nate.com', '1995-01-01', 'basic.png', true, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('company1', '1234', '회사1', '010-1234-5678', 'cos@nate.com', '000000000', 'basic.png', false, NOW());
insert into user_tb(username, password, name, tel_number, email, regist_number, pic_url, distinguish, created_at) 
values('company2', '1234', '회사z', '010-1234-5678', 'cos@nate.com', '000000000', 'basic.png', false, NOW());


insert into resume_tb(title,personal_name,personal_email,phone_number,cover_letter,personal_pic_url,created_at,edu) values('이력서1','쌀','ssar@nate.com','010-1234-5678','자기소개서','basic.png',NOW(),'고졸');
insert into resume_tb(title,personal_name,personal_email,phone_number,cover_letter,personal_pic_url,created_at,edu) values('이력서2','쌀','ssar@nate.com','010-1234-5678','자기소개서','basic.png',NOW(),'고졸');
insert into resume_tb(title,personal_name,personal_email,phone_number,cover_letter,personal_pic_url,created_at,edu) values('이력서3','코스','cos@nate.com','010-1111-5678','자기소개서','basic.png',NOW(),'대졸');
insert into resume_tb(title,personal_name,personal_email,phone_number,cover_letter,personal_pic_url,created_at,edu) values('이력서4','코스','cos@nate.com','010-1111-5678','자기소개서','basic.png',NOW(),'대졸');
 
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('naver@nate.com','네이버정보','네이버','basic.png',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('naver@nate.com','네이버정보','네이버','basic.png',NOW(),'1명','서울','4500만원','21일간','010-1234-5678','사지멀쩡한 자','백엔드급구');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('kakao@nate.com','카카오정보','카카오','basic.png',NOW(),'1명','부산','6000만원','14일간','010-1111-2222','사내규정','서버개발자구함');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('kakao@nate.com','카카오정보','카카오','basic.png',NOW(),'1명','제주','8000만원','30일간','010-1111-2222','사내규정','풀스택개발자');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('Lomin@naver.com','로민정보','Lomin','basic.png',NOW(),'33명','서울','3373만원','2023.10.31까지','010-1234-5678','개발 경력을 보유하신 분','웹 프론트엔드 개발자');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('newsjelly@naver.com','뉴스젤리정보','newsjelly','뉴스젤리.png',NOW(),'33명','서울','3373만원','2023.09.22까지','010-1234-1313','데이터 시각화 관련 웹 프로젝트의 프론트엔드 개발','웹 newsjelly 개발자');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('highdev@naver.com','하이데브정보','highdev','highdev.png',NOW(),'1명','부산','업계연봉최고대우','상시','010-1234-1514','VAN 서버 관리','DBA/DB 개발자');
insert into notice_tb(company_email,company_info,company_name,company_pic_url,created_at,intake,location,pay,period,phone_number,qualification,title)
values('tnh@naver.com','티엔에이치정보','tnh','tnh.png',NOW(),'2명','부산','8000만원','상시','010-1234-1511',' React를 이용하여 당사 프런트 서비스 및 솔루션 개발','React 개발자');


INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (1, 1, false, 1); 
INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (2, 1, true, 1); 
INSERT INTO apply_tb (notice_id, resume_id, status, user_id) VALUES (3, 1, true, 1); 

INSERT INTO scrap_tb (notice_id,user_id) VALUES (1,1);
INSERT INTO scrap_tb (notice_id,user_id) VALUES (2,1);
INSERT INTO scrap_tb (notice_id,user_id) VALUES (3,1);


insert into skill_tb(skill_name) values('Java');
insert into skill_tb(skill_name) values('MySQL');
insert into skill_tb(skill_name) values('JavaScript');
insert into skill_tb(skill_name) values('Spring');
insert into skill_tb(skill_name) values('HTML/CSS');
insert into skill_tb(skill_name) values('React');
insert into skill_tb(skill_name) values('Flutter');
insert into skill_tb(skill_name) values('Unity');

insert into wish_skill_tb(skill_id,notice_id) values(1,1);
insert into wish_skill_tb(skill_id,notice_id) values(2,1);
insert into wish_skill_tb(skill_id,notice_id) values(1,2);
insert into wish_skill_tb(skill_id,notice_id) values(3,2);
insert into wish_skill_tb(skill_id,notice_id) values(5,3);
insert into wish_skill_tb(skill_id,notice_id) values(6,3);


insert into wish_skill_tb(skill_id,resume_id) values(1,1);
insert into wish_skill_tb(skill_id,resume_id) values(2,1);
insert into wish_skill_tb(skill_id,resume_id) values(3,2);
insert into wish_skill_tb(skill_id,resume_id) values(4,2);
insert into wish_skill_tb(skill_id,resume_id) values(5,3);
insert into wish_skill_tb(skill_id,resume_id) values(6,3);




insert into duty_tb(duty_name) values('서버 개발자');
insert into duty_tb(duty_name) values('백엔드 개발자');
insert into duty_tb(duty_name) values('프론트엔드 개발자');
insert into duty_tb(duty_name) values('안드로이드 개발자');
insert into duty_tb(duty_name) values('ios개발자');
insert into duty_tb(duty_name) values('풀스택 개발자');
insert into duty_tb(duty_name) values('빅데이터 엔지니어');
insert into duty_tb(duty_name) values('임베디드 개발자');
insert into duty_tb(duty_name) values('Devops 개발자');

insert into wish_duty_tb(duty_id,notice_id) values(2,1);
insert into wish_duty_tb(duty_id,notice_id) values(1,2);
insert into wish_duty_tb(duty_id,notice_id) values(3,2);
insert into wish_duty_tb(duty_id,notice_id) values(5,3);
insert into wish_duty_tb(duty_id,notice_id) values(1,1);
insert into wish_duty_tb(duty_id,notice_id) values(6,3);


insert into wish_duty_tb(duty_id,resume_id) values(1,1);
insert into wish_duty_tb(duty_id,resume_id) values(2,1);
insert into wish_duty_tb(duty_id,resume_id) values(3,2);
insert into wish_duty_tb(duty_id,resume_id) values(4,2);
insert into wish_duty_tb(duty_id,resume_id) values(5,3);
insert into wish_duty_tb(duty_id,resume_id) values(6,3);



insert into edu_tb(grade) values('고졸');
insert into edu_tb(grade) values('대학교 재학');
insert into edu_tb(grade) values('대학교 휴학');
insert into edu_tb(grade) values('대학교 중퇴');
insert into edu_tb(grade) values('대학교 졸업');
insert into edu_tb(grade) values('석사');
insert into edu_tb(grade) values('박사');
insert into edu_tb(grade) values('기타');

insert into location_tb(location_name) values('서울');
insert into location_tb(location_name) values('대전');
insert into location_tb(location_name) values('대구');
insert into location_tb(location_name) values('광주');
insert into location_tb(location_name) values('부산');
insert into location_tb(location_name) values('제주');

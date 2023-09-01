insert into board_tb(title, email, phone_number, created_at) values('문의 사항','ssar@nate.com','010-1234-5678',NOW());
insert into board_tb(title, email, phone_number, created_at) values('서비스 문의','cos@nate.com','010-1111-2222',NOW());
insert into board_tb(title, email, phone_number, created_at) values('서비스 문의','cos@nate.com','010-1111-2222',NOW());

insert into user_tb(username, password, personal_name, personal_birth, email, personal_id, created_at) values('ssar','1234','쌀','1995-01-01','ssar@nate.com', 1234, NOW());
insert into user_tb(username, password, personal_name, personal_birth, email, personal_id, created_at) values('cos', '1234', '코스', '1995-01-01', 'cos@nate.com', 1234, NOW());
insert into user_tb(username, password, company_name, proprietary_number, email, company_id, created_at) values('company1', '1234', '회사1', '0000000000', 'cos@nate.com', 1234, NOW());
insert into user_tb(username, password, company_name, proprietary_number, email, company_id, created_at) values('company2', '1234', '회사2', '0000000001', 'cos@nate.com', 1234, NOW());

insert into resume_tb(title,personal_email,phone_number,cover_letter,personal_picture,created_at) values('이력서1','ssar@nate.com','010-1234-5678','자기소개서','basic.png',NOW());
insert into resume_tb(title,personal_email,phone_number,cover_letter,personal_picture,created_at) values('이력서2','cos@nate.com','010-1111-5678','자기소개서','basic.png',NOW());

 
insert into notice_tb(company_email,company_info,company_name,created_at,intake,location,pay,period,phone_number,qualification,title)
values('naver@nate.com','네이버정보','네이버',NOW(),'2명','서울','5000만원','21일간','010-1234-5678','사지멀쩡한 자','프론트급구');
insert into notice_tb(company_email,company_info,company_name,created_at,intake,location,pay,period,phone_number,qualification,title)
values('naver@nate.com','네이버정보','네이버',NOW(),'1명','서울','4500만원','21일간','010-1234-5678','사지멀쩡한 자','백엔드급구');
insert into notice_tb(company_email,company_info,company_name,created_at,intake,location,pay,period,phone_number,qualification,title)
values('kakao@nate.com','카카오정보','카카오',NOW(),'1명','부산','6000만원','14일간','010-1111-2222','사내규정','서버개발자구함');
insert into notice_tb(company_email,company_info,company_name,created_at,intake,location,pay,period,phone_number,qualification,title)
values('kakao@nate.com','카카오정보','카카오',NOW(),'1명','제주','8000만원','30일간','010-1111-2222','사내규정','풀스택개발자');

insert into skill_tb(skill_name) values('java');
insert into skill_tb(skill_name) values('MySQL');
insert into skill_tb(skill_name) values('javascript');
insert into skill_tb(skill_name) values('spring');
insert into skill_tb(skill_name) values('html/css');
insert into skill_tb(skill_name) values('react');
insert into skill_tb(skill_name) values('flutter');
insert into skill_tb(skill_name) values('unity');

insert into duty_tb(duty_name) values('서버 개발자');
insert into duty_tb(duty_name) values('백엔드 개발자');
insert into duty_tb(duty_name) values('프론트엔드 개발자');
insert into duty_tb(duty_name) values('안드로이드 개발자');
insert into duty_tb(duty_name) values('ios개발자');
insert into duty_tb(duty_name) values('풀스택 개발자');
insert into duty_tb(duty_name) values('빅데이터 엔지니어');
insert into duty_tb(duty_name) values('임베디드 개발자');
insert into duty_tb(duty_name) values('Devops 개발자');

insert into edu_tb(grade) values('고졸');
insert into edu_tb(grade) values('대학교 재학');
insert into edu_tb(grade) values('대학교 휴학');
insert into edu_tb(grade) values('대학교 중퇴');
insert into edu_tb(grade) values('대학교 졸업');
insert into edu_tb(grade) values('석사');
insert into edu_tb(grade) values('박사');
insert into edu_tb(grade) values('기타');
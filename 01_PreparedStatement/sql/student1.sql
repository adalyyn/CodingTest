--==================================
-- 관리자계정
--==================================
--id : student / pw : student 계정생성

alter session set "_oracle_script" = true;
create user student1 --id는 대소문자 구분하지 않음
identified by student1   --비밀번호는 대소문자 구분함
default tablespace users;

grant connect, resource to student1;
alter user student1 quota unlimited on users;


--==================================
-- student계정
--==================================
--member 테이블
create table member(
    id varchar2(20),
    name varchar2(100) not null,
    gender char(1),
    birthday date,
    email varchar2(500) not null,
    point number default 1000,
    reg_date timestamp default systimestamp,
    constraint pk_member_id primary key(id),
    constraint uq_member_email unique(email),
    constraint ck_member_gender check(gender in ('M', 'F'))
);

--drop table member;

insert into
    member
values(
    'honggd', '홍길동', 'M', '1999-09-09', 'honggd@naver.com', default, default
);

insert into
    member
values(
    'sinsa', '신사임당', 'F', '1992-05-05', 'sinsa@naver.com', default, default
);

insert into
    member
values(
    'gogd', '고길동', 'M', '1980-02-15', 'gogd@naver.com', default, default
);

insert into
    member
values(
    'leess', '이순신', null, null, 'leess@naver.com', default, default
);

insert into
    member
values(
    'qwerty', '쿼티', 'F', null, 'qwerty@naver.com', default, default
);

select * from member;
commit;

desc member;


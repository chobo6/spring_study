CREATE TABLE t_book(
    id number(3) primary key,
    title varchar2(100),
    author varchar2(20),
    price number(6)
);

create sequence seq_t_book_pk
start with 1
increment by 1
minvalue 1
maxvalue 999
nocycle;
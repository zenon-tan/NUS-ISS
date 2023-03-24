# create database rsvp;

use rsvp;

create table rsvp(
	id varchar(8) not null,
    name varchar(128) not null,
    email varchar(128) not null,
    phone char(8) not null,
    confirmation_date Date not null,
    comments text,
    primary key(id)
);

select * from rsvp;

insert into rsvp (id, name, email, phone, confirmation_date, comments)
values("f57jd001", "Kim Kardashian", "kkk@kkk.com", "09661122", "2023-02-01", "Don't be fucking RUDE");

update rsvp set 
comments = "Is butter a carb?"
where id = "f57jd001";

select count(*) from rsvp;
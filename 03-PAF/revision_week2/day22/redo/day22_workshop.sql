-- create database rsvp;
-- use rsvp;

create table rsvp(
	name varchar(125) not null,
    email varchar(255) not null,
    phone char(8) not null,
    confirmation_date Date not null,
    comments text,
    primary key(phone)
);

insert into rsvp(name, email, phone, confirmation_date, comments)
values("Kim Kardashian", "kimk@gmail.com", "66660000", "2023-02-24", "Whorelie Jenner");

insert into rsvp(name, email, phone, confirmation_date, comments)
values("Khloe Kardashian", "koko@gmail.com", "77770000", "2023-02-24", "Whorelie Jenner");

update rsvp set 
name = "Kris Jenner",
email = "pmk@epstein.com",
confirmation_date = "2023-02-25",
comments = "im a pimp queen"
where phone = "66660000";

select * from rsvp;

select * from rsvp where name like "%kim%";

select count(*) from rsvp;
#MySQL Query Practice

-- Create and use database 
create database practice;
use practice;

-- Create a table
create table tv_shows(
	prog_id int not null auto_increment,
    name char(64) not null,
    lang char(64) not null,
    official_site varchar(256),
    rating enum('G', 'PG', 'NC16', 'M18', 'R21') not null,
    user_rating float default '0.0',
    release_date date,
    image varchar(256),
    primary key(prog_id),
    check(user_rating between 0.0 and 10.0)
);

-- Select
select * from tv_shows;
select * from tv_shows limit 100 offset 100;
select * from tv_shows where name = "Game of Thrones";
select * from tv_shows where name like "%game%";
select release_date from tv_shows where release_date between "2010-01-01" and "2012-01-01";
select * from tv_shows where ratings in ('M18', 'R21');
select name from tv_shows where lang = "eng" and user_rating > '8.5';

-- Aggregation: Distinct
select distinct lang from tv_shows;
select distinct lang, rating from tv_shows;

-- Aggregation: Count
select count(*) from tv_shows where lang like 'eng%';
select count(distinct name) from tv_shows where lang like 'eng%';

-- Aggregation: Arithmetic
select avg(user_rating) from tv_shows where lang like 'eng%';
select sum(length(image)) from tv_shows where lang like 'eng%';

-- Aggregating by Group and Order
use northwind;
select * from customers;
select city, count(city) as freq from customers group by city order by count(city) desc;
select city, count(city) as freq from customers group by city having count(city) >= 2;

-- CRUD operations

use practice;
select * from tv_shows;
-- Insert
insert into tv_shows(name, lang, rating, user_rating, release_date)
values("Game of Thrones", "English", "NC16", "8.4", "2010-01-01");
-- Update
update tv_shows set 
release_date = "2010-04-04",
user_rating = 7.1
where prog_id = 1;
-- Delete
delete from tv_shows where prog_id = 1;

-- Joins
create table episodes(
	ep_id int not null auto_increment,
    season int not null,
    episode int not null,
    title varchar(256) not null,
    synopsis text,
    release_date date not null,
    prog_id int not null,
    primary key(ep_id),
    foreign key(prog_id) references tv_shows(prog_id)
);

insert into episodes(season, episode, title, release_date, prog_id)
values(1, 1, "Winter is Coming", "2012-04-6", 1);

select * from episodes;

select tv.name, tv.lang, ep.season, ep.episode, ep.title
from tv_shows tv
inner join episodes ep
on tv.prog_id = ep.prog_id;

-- Views
create view got as
select tv.name, tv.lang, ep.season, ep.episode, ep.title
from tv_shows tv
inner join episodes ep
on tv.prog_id = ep.prog_id
where tv.name like "Game of Thrones";

select * from got;
















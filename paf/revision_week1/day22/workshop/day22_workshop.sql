create database rsvp;
use rsvp;

create table rsvp (
	id varchar(8) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    phone numeric(8) not null,
    confirmation_date Date not null,
    response Boolean not null,
    comments varchar(255),
    primary key(id)
);

select * from rsvp;

insert into rsvp (id, name, email, phone, confirmation_date, response, comments)
values(00000000, 'Katy Perry', 'katy@gmail.com', 88888888, '2023-01-02', True, 'hungry');

update rsvp set name = 'Katy Perry', email = 'katy@gmail.com', phone = 88888888, 
confirmation_date = '2023-01-02', response = True, comments = 'hungry'
where id = '0';

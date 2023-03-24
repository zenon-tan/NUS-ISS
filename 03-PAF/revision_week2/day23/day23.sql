create database shows;
use shows;

create table tv_shows(
	show_id int not null auto_increment,
    name char(64) not null,
    lang char(16) not null,
    official_site varchar(256),
    rating char(16) not null,
    user_ratings float not null,
    release_date Date not null,
    image blob,
    primary key(show_id)
);

create table episodes(
	ep_id int not null auto_increment,
    season int not null,
    episode int not null,
    title varchar(256) not null,
    sypnosis text,
    release_date Date not null,
    show_id int not null,
    primary key(ep_id),
    foreign key(show_id) references tv_shows(show_id)
);

select * from tv_shows;

create view show_thrones_episodes as
select tv.name, tv.lang, tv.rating, ep.season, ep.episode, ep.title, ep.sypnosis
from tv_shows as tv
inner join episodes as ep
on tv.show_id = ep.show_id
where tv.name like "%thrones";
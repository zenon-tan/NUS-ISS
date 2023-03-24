create database day24_workshop;

use day24_workshop;

create table orders(
	
    order_id char(8) not null,
    order_date Date not null,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal(2,2) default "0.05",
    primary key(order_id)
);

create table order_details(
	
    id int not null auto_increment,
    order_id char(8) not null,
    product varchar(64) not null,
    unit_price decimal not null,
    discount decimal default "1.0",
    quantity int not null,
    primary key(id),
    foreign key(order_id) references orders(order_id)
);

select * from orders;
select * from order_details;

insert into orders(order_id, order_date, customer_name, ship_address, notes)
values("ff56rrb7", "2023-02-24", "Kim Kardashian", "Calabasas", "Whorelie Jenner");

insert into order_details(order_id, product, unit_price, quantity) 
values("ff56rrb7", "Lipkit", 1.20, "20");
create database day24_workshop;
use day24_workshop;

create table orders (
	id varchar(8) not null,
    order_date Date not null,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal(2,2) default '0.05',
    primary key(id)
);

create table order_details(
	id int not null auto_increment,
    order_id varchar(8) not null,
    product varchar(64) not null,
    unit_price decimal(3,2) not null,
    discount decimal(3,2) default '1.0',
    quantity int not null,
    primary key(id),
    foreign key(order_id) references orders(id)
);

select * from orders;
select * from order_details;

select * from orders where id = '68287f5f';
select * from order_details where order_id = '81f49d36';

insert into orders(id, order_date, customer_name, ship_address, notes)
values('4f6hbk87', '2023-02-6', 'Katy Perry', 'Los Angeles', 'Witness');

insert into order_details(order_id, product, unit_price, discount, quantity)
values('4f6hbk87', 'Tomatoes', '0.40', '2.0', '5');

insert into order_details(order_id, product, unit_price, quantity)
values('4f6hbk87', 'Tomatoes', '0.40', '5');





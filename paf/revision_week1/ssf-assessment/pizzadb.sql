create database pizza;
use pizza;

create table pizza_cart(

	id int not null auto_increment,
    order_id varchar(8), 
    quantity int not null,
    price decimal(3,2) not null,
    primary key(id)
	
);

create table pizza(
	
    pizza_id int not null,
    pizza_name varchar(128) not null,
    size enum('s', 'm', 'l') not null,
    price decimal(3,2) not null,
    primary key(pizza_id),
    foreign key(pizza_id) references pizza_cart(id)
    
);

create table customer(
	
    id varchar(8) not null,
    name varchar(128) not null,
    phone varchar(8) not null,
    address varchar(255) not null,
    primary key(id)
);

create table discount(
	
    id int not null auto_increment,
    code varchar(60) not null,
    description varchar(255) not null,
    discount int not null default '0',
    primary key(id)
);


create table pizza_order(
	
    id varchar(8) not null,
    order_date Date not null,
    isRush Boolean not null default False,
    comments varchar(255),
    customer_id varchar(8) not null,
    discount_id int not null,
    cart_id int not null,
    primary key(id),
    foreign key(customer_id) references customer(id),
    foreign key(discount_id) references discount(id),
    foreign key(cart_id) references pizza_cart(id)
    
    
);

select * from pizza;
select * from pizza_cart;
select * from pizza_order;
select * from discount;
select * from customer;
select * from pizzadb;

insert into pizzadb(pizza_name, img_url, price)
values('bella', '/images/Bella.png', 20);
insert into pizzadb(pizza_name, img_url, price)
values('margherita', '/images/Margherita.png', '22');
insert into pizzadb(pizza_name, img_url, price)
values('marinara', '/images/Marinara.png', '22');
insert into pizzadb(pizza_name, img_url, price)
values('SpianataCalabrese', '/images/SpianataCalabrese.png', '24');
insert into pizzadb(pizza_name, img_url, price)
values('trioformaggio', '/images/trioformaggio.png', '24');




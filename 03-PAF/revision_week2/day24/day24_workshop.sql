create database day24_workshop;
use day24_workshop;

create table orders(
	
    order_id int not null auto_increment,
    order_date Date not null,
    customer_name varchar(128) not null,
    ship_address varchar(128) not null,
    notes text,
    tax decimal(2,2) default 0.05,
    primary key(order_id)

);

create table order_details(

	id int not null auto_increment,
    product varchar(64) not null,
    unit_price decimal(3,2),
    discount decimal(3,2) default 1.0,
    order_id int not null,
    quantity int not null,
    primary key(id),
    foreign key(order_id) references orders(order_id)
);

insert into orders(order_date, customer_name, ship_address, notes, tax)
values("2023-02-20", "Kim Kardashian", "LA", "Don't be fucking RUDE", "0.5");

select * from orders;

insert into order_details(product, unit_price, discount, order_id, quantity)
values("Ozempic", "5.0", "0.01", "1", "100");

select * from order_details;

CREATE TABLE `orders` (
  `order_id` varchar(8) NOT NULL,
  `order_date` date NOT NULL,
  `customer_name` varchar(128) NOT NULL,
  `ship_address` varchar(128) NOT NULL,
  `notes` text,
  `tax` decimal(2,2) DEFAULT '0.05',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product` varchar(64) NOT NULL,
  `unit_price` decimal(3,2) DEFAULT NULL,
  `discount` decimal(3,2) DEFAULT '1.00',
  `order_id` varchar(8) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


create table pizza(
	
    pizza_name varchar(128) not null,
    size Decimal(3,2) not null,
    quantity INT not null,
    cart_id varchar(8) not null,
    primary key(pizza_name),
    foreign key(cart_id) references pizza_cart(cart_id)
    
);

create table pizza_cart(pizza_order

	cart_id varchar(8) not null,
    price Float not null,
    primary key(cart_id)
    

);

create table customer(
	customer_id varchar(8) not null,
    name varchar(255) not null,
    phone varchar(8) not null,
    address varchar(255) not null,
    primary key(customer_id)
);

create table pizza_order(
	id varchar(8) not null,
    deliveryDate Date not null,
    is_rush varchar(8) not null,
    comments varchar(255),
    total_cost Float not null,
    cart_id varchar(8) not null,
    customer_id varchar(8) not null,
    primary key(id),
    foreign key(cart_id) references pizza_cart(cart_id),
    foreign key(customer_id) references customer(customer_id)
);

select * from pizza;
select * from pizza_cart;
select * from pizza_order;
select * from customer;

       insert into pizza(pizza_name, size, quantity)
        values('ice', '1.5', 6);
        
        
SET FOREIGN_KEY_CHECKS=0;
select c.name, c.address, po.address, po.deliver_date, pc.price, po.is_rush, po.total_cost,
pc.pizza_list from customer c
inner join pizza_order pc
on c.customer_id = pc.customer_id
;

select * from customer;
select * from pizza_order;
select * from pizza_cart;
select * from pizza;
select * from pizzadb;

select po.id, c.customer_id, c.name, c.address, po.total_cost
from customer c
inner join pizza_order po
on c.customer_id = po.customer_id
;

select po.id, c.name, c.address, po.delivery_date, pc.price, po.total_cost,
po.is_rush, po.comments,
p.pizza_name, p.size, p.quantity from customer c
inner join pizza_order po
on c.customer_id = po.customer_id
inner join pizza_cart pc
on po.id = pc.order_id
inner join pizza p
on p.cart_id = pc.cart_id
where id = '706e5db7'
;
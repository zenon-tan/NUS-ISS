use northwind;

select * from orders;
select * from order_details;
select * from products;

select o.id, o.order_date, o.customer_id,
od.quantity*od.unit_price as order_price,
od.quantity*p.standard_cost as cost_price
from orders o
inner join order_details od
on od.id = od.order_id
inner join products p
on od.product_id = p.id
where o.id = 31;
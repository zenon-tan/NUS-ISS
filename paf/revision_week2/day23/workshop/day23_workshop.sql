use northwind;

select * from orders;
select * from products;
select * from order_details;

select o.id, o.order_date, o.customer_id, 
od.quantity*od.unit_price as total_price, od.quantity*p.standard_cost as cost_price
from orders o
inner join order_details od
on o.id = od.order_id
inner join products p
on p.id = od.product_id
where o.id = 31;

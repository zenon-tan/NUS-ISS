-- use northwind;

select o.id, o.customer_id, o.order_date, od.quantity*od.unit_price as total_price, od.quantity*p.standard_cost as cost_price from orders o
inner join order_details od
on o.id = od.order_id
inner join products p
on od.product_id = p.id
where o.id = 31
;

select * from order_details;
select * from products;

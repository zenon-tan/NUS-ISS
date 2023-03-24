package day23.workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day23.workshop.models.Orders;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ORDERS_BY_ID = """

    select o.id, o.customer_id, o.order_date, 
    od.quantity*od.unit_price as total_price, od.quantity*p.standard_cost as cost_price from orders o
    inner join order_details od
    on o.id = od.order_id
    inner join products p
    on od.product_id = p.id
    where o.id = ?
            
            """;

    public List<Orders> getAllOrdersById(int id) {

        return jdbcTemplate.query(SQL_GET_ORDERS_BY_ID, new BeanPropertyRowMapper().newInstance(Orders.class), id);
        
    }
    
}

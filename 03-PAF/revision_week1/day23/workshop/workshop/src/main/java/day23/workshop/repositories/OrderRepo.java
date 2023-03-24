package day23.workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day23.workshop.models.Order;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ORDER_FROM_ID_SQL = """
        select o.id, o.order_date, o.customer_id,
        od.quantity*od.unit_price as order_price,
        od.quantity*p.standard_cost as cost_price
        from orders o
        inner join order_details od
        on od.id = od.order_id
        inner join products p
        on od.product_id = p.id
        where o.id = ?;
            """;

    public List<Order> getOrdersFromId(int id) {
        
        return jdbcTemplate.query(GET_ORDER_FROM_ID_SQL, new BeanPropertyRowMapper().newInstance(Order.class), id);
    }
    
}

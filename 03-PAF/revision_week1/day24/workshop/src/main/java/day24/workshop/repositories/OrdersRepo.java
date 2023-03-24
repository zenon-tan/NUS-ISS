package day24.workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.workshop.models.Orders;

@Repository
public class OrdersRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_ORDERS_SQL = "select * from orders";
    private static final String ADD_ORDER_SQL = """
        insert into orders(id, order_date, customer_name, ship_address, notes)
        values(?, ?, ?, ?, ?);
            """;

    private static final String GET_ORDER_BY_ID_SQL = """
            select * from orders where id = ?
            """;

    public Boolean addOrder(Orders order) {
        Orders newOrder = new Orders();
        order.setId(newOrder.getId());
        return jdbcTemplate.update(ADD_ORDER_SQL, order.getId(), order.getOrderDate(), order.getCustomerName(),
        order.getShipAddress(), order.getNotes()) > 0;
    }

    public Orders getOrderById(String id) {
        return jdbcTemplate.queryForObject(GET_ORDER_BY_ID_SQL, 
        new BeanPropertyRowMapper().newInstance(Orders.class), id);

    }
    

    
}

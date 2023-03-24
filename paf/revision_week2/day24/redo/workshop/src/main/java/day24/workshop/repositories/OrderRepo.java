package day24.workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.workshop.models.Orders;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_ORDER = """
        insert into orders(order_id, order_date, customer_name, ship_address, notes)
        values(?, ?, ?, ?, ?)
            """;

    public Boolean insertOrder(Orders order) {

        return jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), order.getOrderDate(), 
        order.getCustomerName(), order.getShipAddress(), order.getNotes()) > 0;
        
    }
    
}

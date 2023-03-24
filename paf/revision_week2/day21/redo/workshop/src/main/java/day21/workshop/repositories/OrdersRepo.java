package day21.workshop.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day21.workshop.models.Orders;

@Repository
public class OrdersRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ORDERS_BY_CID = """
            select * from orders where customer_id = ?
            """;

    public List<Orders> getAllOrdersByCustId(int cid) {

        try {
            return jdbcTemplate.query(SQL_GET_ORDERS_BY_CID, new BeanPropertyRowMapper().newInstance(Orders.class), cid);
            
        } catch (DataAccessException e) {

            return new ArrayList<>();
        }
        
    }
    
}

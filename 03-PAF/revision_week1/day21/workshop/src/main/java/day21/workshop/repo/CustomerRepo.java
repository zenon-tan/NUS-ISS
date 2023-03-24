package day21.workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day21.workshop.models.Customer;
import day21.workshop.models.Order;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_CUSTOMERS_SQL = """
        select id, company, first_name, last_name, job_title, address, city, state_province 
        from customers limit ? offset ?
            """;

    private static final String GET_CUSTOMER_BY_SQL = """
        select id, company, first_name, last_name, job_title, address, city, state_province 
        from customers
        where id = ?
            """;

    private static final String GET_ORDER_BY_CUST_ID_SQL = """
        select id, customer_id, order_date, shipped_date, ship_address, ship_city, ship_state_province
        from orders
        where customer_id = ?
            """;

    public List<Customer> getAllCustomers(int limit, int offset) {

        return jdbcTemplate.query(GET_ALL_CUSTOMERS_SQL, 
        new BeanPropertyRowMapper().newInstance(Customer.class), limit, offset);

        
    }

    public List<Customer> getCustomerById(int id) {

        return jdbcTemplate.query(GET_CUSTOMER_BY_SQL, 
        new BeanPropertyRowMapper().newInstance(Customer.class), id);

        
    }

    public List<Order> getOrderByCustomerId(int cust_id) {
        
        return jdbcTemplate.query(GET_ORDER_BY_CUST_ID_SQL, 
        new BeanPropertyRowMapper().newInstance(Order.class), cust_id);
    }
    
}

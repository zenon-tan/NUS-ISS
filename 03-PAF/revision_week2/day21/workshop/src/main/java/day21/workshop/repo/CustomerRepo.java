package day21.workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day21.workshop.models.Customer;
import day21.workshop.models.Orders;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_ALL_CUSTOMERS = """
        select id, company, first_name, last_name, address, city from customers limit ? offset ?
            """;

    private static final String SQL_GET_CUSTOMER_BY_ID = """
        select id, company, first_name, last_name, address, city from customers
        where id = ?
            """;

    private static final String SQL_GET_ORDER_BY_CUSTOMER_ID = """
            select id, order_date, ship_address, ship_city, ship_country_region from orders
            where customer_id = ?
            """;

    public List<Customer> getAllCustomer(int offset, int limit) {

        return jdbcTemplate.query(SQL_GET_ALL_CUSTOMERS, new BeanPropertyRowMapper().newInstance(Customer.class), limit, offset);
        
    }

    public Customer getCustomerById(int id) {

        return jdbcTemplate.queryForObject(SQL_GET_CUSTOMER_BY_ID, new BeanPropertyRowMapper<>().newInstance(Customer.class), id);


    }

    public List<Orders> getOrdersByCustomerId(int id) {
        return jdbcTemplate.query(SQL_GET_ORDER_BY_CUSTOMER_ID, new BeanPropertyRowMapper().newInstance(Orders.class), id);

    }
}

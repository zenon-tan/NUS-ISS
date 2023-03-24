package ssfassessment.pizza.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssfassessment.pizza.models.Customer;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_CUSTOMER_SQL = """
        insert into customer(customer_id, name, phone, address)
        values(?, ?, ?, ?)
        """;

    private static final String GET_CART_BY_ID_SQL = """
        select * from customer where customer_id = ?
        """;

    public Boolean insertCustomer(Customer customer) {

        return jdbcTemplate.update(INSERT_CUSTOMER_SQL, customer.getCustomerId(),
        customer.getName(), customer.getPhone(), customer.getAddress()) > 0;
    }

    public Customer getCustomerById(String custId) {

        return jdbcTemplate.queryForObject(GET_CART_BY_ID_SQL, 
        new BeanPropertyRowMapper().newInstance(Customer.class), custId);
    }


    
}

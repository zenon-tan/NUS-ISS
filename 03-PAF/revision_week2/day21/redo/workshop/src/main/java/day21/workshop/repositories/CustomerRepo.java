package day21.workshop.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day21.workshop.models.Customer;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final String SQL_GET_ALL_CUSTOMERS = """
        select * from customers limit ? offset ?
            """;

    public static final String SQL_GET_CUSTOMER_BY_ID = """
            select * from customers where id = ?
            """;

    public List<Customer> getAllCustomer(int limit, int offset) {

        return jdbcTemplate.query(SQL_GET_ALL_CUSTOMERS, new BeanPropertyRowMapper().newInstance(Customer.class), limit, offset);
        
    }

    public Optional<Customer> getCustomerById(int id) {

        try {

            Customer result = jdbcTemplate.queryForObject(SQL_GET_CUSTOMER_BY_ID, new BeanPropertyRowMapper<>().newInstance(Customer.class), id);
            return Optional.of(result);
            
        } catch (DataAccessException e) {
            return Optional.empty();
        }

    }
    
}

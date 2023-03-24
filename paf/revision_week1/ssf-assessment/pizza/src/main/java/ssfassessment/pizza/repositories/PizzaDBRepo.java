package ssfassessment.pizza.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssfassessment.pizza.models.PizzaDB;

@Repository
public class PizzaDBRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String GET_ALL_PIZZA_SQL = "select * from pizzadb";
    private static final String GET_PIZZA_PRICE_SQL = "select price from pizzadb where pizza_name = ?";
    private static final String GET_PIZZA_BY_NAME_SQL = "select * from pizzadb where pizza_name = ?";


    public List<PizzaDB> getAllPizzas() {
        return jdbcTemplate.query(GET_ALL_PIZZA_SQL, new BeanPropertyRowMapper().newInstance(PizzaDB.class));
    }

    public int getPizzaPrice(String pizzaName) {
        return jdbcTemplate.queryForObject(GET_PIZZA_PRICE_SQL, new BeanPropertyRowMapper<>().newInstance(Integer.class), pizzaName);
    }

    public PizzaDB getPizzaDbByName(String pizzaName) {
        return jdbcTemplate.queryForObject(GET_PIZZA_BY_NAME_SQL,
         new BeanPropertyRowMapper<>().newInstance(PizzaDB.class), pizzaName);
    }
    
}

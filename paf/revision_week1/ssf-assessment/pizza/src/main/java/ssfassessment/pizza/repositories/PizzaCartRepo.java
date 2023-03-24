package ssfassessment.pizza.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssfassessment.pizza.models.PizzaCart;
import ssfassessment.pizza.models.PizzaOrder;

@Repository
public class PizzaCartRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT_CART_SQL = """
            insert into pizza_cart(cart_id, price, order_id)
            values(?, ?, ?)
            """;

    private static final String GET_CART_BY_ID_SQL = """
            select * from pizza_cart where order_id = ?
            """;

    public Boolean insertCart(PizzaOrder order) {

        return jdbcTemplate.update(INSERT_CART_SQL, order.getCart().getCartId(), 
        order.getCart().getPrice(), order.getId()) > 0;
    }

    public PizzaCart getCartById(String orderId) {
        return jdbcTemplate.queryForObject(GET_CART_BY_ID_SQL, 
        new BeanPropertyRowMapper().newInstance(PizzaCart.class), orderId);
    }
    
}

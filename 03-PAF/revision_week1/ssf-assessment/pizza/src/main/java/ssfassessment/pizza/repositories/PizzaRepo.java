package ssfassessment.pizza.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssfassessment.pizza.models.Pizza;
import ssfassessment.pizza.models.PizzaCart;

@Repository
public class PizzaRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PizzaDBRepo pdbRepo;

    private static final String ADD_PIZZA_SQL = """
        insert into pizza(pizza_name, size, quantity, cart_id)
        values(?, ?, ?, ?)
            
            """;

    private static final String SELECT_PIZZA_BY_ID = """
            select * from pizza where cart_id = ?
            """;

    public int[] addPizza(PizzaCart cart) {

        List<Object[]> arrData = cart.getPizzaList().stream()
        .map(i -> {

            Object[] l = new Object[4];
            l[0] = i.getPizzaName();
            l[1] = i.getSize();
            l[2] = i.getQuantity();
            l[3] = cart.getCartId();

            return l;



        }).toList();

        return jdbcTemplate.batchUpdate(ADD_PIZZA_SQL, arrData);

    }

    public List<Pizza> getPizzaByCartId(String cartId) {
        return jdbcTemplate.query(SELECT_PIZZA_BY_ID, new BeanPropertyRowMapper().newInstance(Pizza.class),
        cartId);
    }
    
}

package ssfassessment.pizza.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ssfassessment.pizza.models.PizzaCart;
import ssfassessment.pizza.models.PizzaOrder;

@Repository
public class PizzaOrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

    private static final String INSERT_ORDER_SQL = """
            insert into pizza_order(id, delivery_date, is_rush, comments, total_cost, customer_id)
            values(?, ?, ?, ?, ?, ?)
            """;

    private static final String SELECT_ORDER_BY_ID = """
            select * from pizza_order where id = ?
            """;

    public Boolean insertOrder(PizzaOrder order) {
        
        return jdbcTemplate.update(INSERT_ORDER_SQL, 
        order.getId(), order.getDeliveryDate(), order.getIsRush(), order.getComments(),
        order.getTotalCost(), order.getCustomer().getCustomerId()) >0;
    }

    public PizzaOrder selectOrderById(String cartId) {
        return jdbcTemplate.queryForObject(SELECT_ORDER_BY_ID, 
        new BeanPropertyRowMapper().newInstance(PizzaOrder.class), cartId);
    }

    public Boolean insertOrderToMongo(PizzaOrder order) {

        Document doc = new Document();
        doc.append("id", order.getCustomerId());
        doc.append("cost", order.getTotalCost());
        doc.append("date", order.getDeliveryDate().toString());

        Document newDoc = mongoTemplate.insert(doc, "pizzaOrder");
        if(!newDoc.isEmpty()) {
            return true;
        }

        return false;

    }
    
}

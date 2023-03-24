package day24.workshop.repositories;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.workshop.models.OrderDetails;

@Repository
public class OrderDetailsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_ITEMS = """
        insert into order_details(order_id, product, unit_price, quantity) 
        values(?, ?, ?, ?)
            """;

    public Boolean insertOrderDetails(List<OrderDetails> items, String orderId) {

        List<Object[]> arr = items.stream()
        .map(i -> {

            Object[] item = new Object[4];
            item[0] = orderId;
            item[1] = i.getProduct();
            item[2] = i.getUnitPrice();
            item[3] = i.getQuantity();

            return item;

        }).toList();

        int[] result = jdbcTemplate.batchUpdate(SQL_INSERT_ITEMS, arr);

        int[] updated = Arrays.stream(result).filter(i -> i >= 1).toArray();

        return updated.length == items.size();
        
    }
    
}

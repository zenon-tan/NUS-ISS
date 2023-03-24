package day24.workshop.repositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.workshop.models.OrderDetails;

import static day24.workshop.repositories.SQL.*;

@Repository
public class OrderDetailsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Boolean batchInsertOrderDetails(List<OrderDetails> items, String orderId) {

        List<Object[]> arrObjects = items.stream()
        .map(i -> {
            Object[] v = new Object[5];
            v[0] = i.getProduct();
            v[1] = i.getUnitPrice();
            v[2] = i.getDiscount();
            v[3] = orderId;
            v[4] = i.getQuantity();

            return v;

        }).toList();

        int[] results = jdbcTemplate.batchUpdate(SQL_INSERT_ORDER_DETAILS, arrObjects);

        List<Integer> resultsList = Arrays.stream(results).boxed().toList();

        int inserted = resultsList.stream()
        .filter(i -> i >= 1)
        .toList().size();

        if(inserted == arrObjects.size()) {
            return true;
        }

        return false;
        
    }

    public List<OrderDetails> getItemsById(String id) {
        return jdbcTemplate.query(SQL_GET_ITEMS_BY_ID, new BeanPropertyRowMapper().newInstance(OrderDetails.class), id);
    }
    
}

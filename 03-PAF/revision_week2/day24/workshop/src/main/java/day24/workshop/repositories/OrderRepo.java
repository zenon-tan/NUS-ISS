package day24.workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import day24.workshop.models.Orders;
import jakarta.json.JsonObject;

import static day24.workshop.repositories.SQL.*;

@Repository
public class OrderRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public Boolean insertOrder(Orders order) {

        return jdbcTemplate.update(SQL_INSERT_ORDER, order.getOrderId(), order.getOrderDate(), order.getCustomerName(), order.getShipAddress(),
        order.getNotes(), order.getTax()) > 0;

    }

    public Orders getOrderById(String id) {

        return jdbcTemplate.queryForObject(SQL_GET_ORDER_BY_ID, new BeanPropertyRowMapper<>().newInstance(Orders.class), id);

    }

    public void saveToRedis(String id, JsonObject json) {

        redisTemplate.opsForValue().set(id, json.toString());
    }

    public String findOrderFromRedis(String id) {

        return redisTemplate.opsForValue().get(id);
        
    }


    
}

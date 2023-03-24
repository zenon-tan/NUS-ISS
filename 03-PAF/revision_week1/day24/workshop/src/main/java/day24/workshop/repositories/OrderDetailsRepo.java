package day24.workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import day24.workshop.models.OrderDetails;

@Repository
public class OrderDetailsRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String ADD_TO_ORDER_DETAILS_SQL = """
        insert into order_details(order_id, product, unit_price, quantity)
        values(?, ?, ?, ?);
            """;

    private static final String GET_ORDER_DETAILS_BY_ID_SQL = "select * from order_details where order_id = ?";

    public int[] addOrderDetails(List<OrderDetails> orderList, String orderId) {

        List<Object[]> arrData = orderList.stream()
        .map(i -> {
            Object[] l = new Object[4];
            l[0] = orderId;
            l[1] = i.getProduct();
            l[2] = i.getUnitPrice();
            l[3] = i.getQuantity();
            return l;
        }).toList();

        int[] results = jdbcTemplate.batchUpdate(ADD_TO_ORDER_DETAILS_SQL, arrData);
        return results;

    }

    public List<OrderDetails> getAllOrderDetailsById(String id) {

        return jdbcTemplate.query(GET_ORDER_DETAILS_BY_ID_SQL, new BeanPropertyRowMapper().newInstance(OrderDetails.class), id);

    }

    
}

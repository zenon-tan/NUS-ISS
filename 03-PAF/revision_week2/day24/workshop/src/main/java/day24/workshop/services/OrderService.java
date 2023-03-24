package day24.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import day24.workshop.exception.OrderException;
import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import day24.workshop.repositories.OrderDetailsRepo;
import day24.workshop.repositories.OrderRepo;
import day24.workshop.utils.JsonConverters;
import jakarta.json.JsonObject;

import static day24.workshop.repositories.SQL.*;

@Service
public class OrderService {

    @Autowired
    OrderRepo oRepo;

    @Autowired
    OrderDetailsRepo odRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void insertOrder(Orders order) throws OrderException{

        oRepo.insertOrder(order);
        if(order.getItems().size() > 3) {
            throw new OrderException("Only 3 items allowed!");
        }
        odRepo.batchInsertOrderDetails(order.getItems(), order.getOrderId());

        insertOrderToRedis(order);
        
    }

    public Orders getOrderById(String id) {

        Orders order = oRepo.getOrderById(id);
        order.setItems(odRepo.getItemsById(id));

        return order;
    }

    public void insertOrderToRedis(Orders order) {

        JsonObject json = JsonConverters.convertToJson(order);
        oRepo.saveToRedis(order.getOrderId(), json);
    }

    public String findOrderFromRedis(String id) {

        return oRepo.findOrderFromRedis(id);
    }




    
}

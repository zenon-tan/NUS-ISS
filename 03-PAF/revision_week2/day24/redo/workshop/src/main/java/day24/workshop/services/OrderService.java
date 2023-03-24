package day24.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day24.workshop.exception.OrderException;
import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import day24.workshop.repositories.OrderDetailsRepo;
import day24.workshop.repositories.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo oRepo;

    @Autowired
    OrderDetailsRepo odRepo;

    @Transactional(rollbackFor = OrderException.class)
    public void insertOrder(Orders order) throws OrderException {

        if(order.getItems().size() > 3) {
            throw new OrderException("You cannot order more than 3 items");
        }
        oRepo.insertOrder(order);
        odRepo.insertOrderDetails(order.getItems(), order.getOrderId());
        
    }
    
}

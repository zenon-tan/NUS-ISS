package day24.workshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day24.workshop.exceptions.OrderException;
import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import day24.workshop.repositories.OrderDetailsRepo;
import day24.workshop.repositories.OrdersRepo;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Service
public class OrderService {

    @Autowired
    OrdersRepo oRepo;

    @Autowired
    OrderDetailsRepo odRepo;

    @Transactional(rollbackFor = OrderException.class)
    public Boolean createOrder(Orders order) throws OrderException {
        oRepo.addOrder(order);
        if(order.getOrderList().size() > 4) {

            throw new OrderException("Only 4 items allowed");

        }
        odRepo.addOrderDetails(order.getOrderList(), order.getId());
        return true;

    }

    public Orders getOrderById(String id) {
        Orders result = oRepo.getOrderById(id);
        result.setOrderList(odRepo.getAllOrderDetailsById(id));

        return result;
    }

    public JsonObject getOrderJsonById(String id) {

        Orders result = oRepo.getOrderById(id);
        List<OrderDetails> items = odRepo.getAllOrderDetailsById(id);
        result.setOrderList(items);

        JsonArrayBuilder itemsList = Json.createArrayBuilder();

        for(OrderDetails item : items) {

            JsonObjectBuilder itemJson = Json.createObjectBuilder()
            .add("id", item.getId())
            .add("orderId", item.getOrderId())
            .add("product", item.getProduct())
            .add("unitPrice", item.getUnitPrice())
            .add("discount", item.getDiscount())
            .add("quantity", item.getQuantity());

            itemsList.add(itemJson);

        }

        JsonObject orderJson = Json.createObjectBuilder()
        .add("id", result.getId())
        .add("orderDate", result.getOrderDate().toString())
        .add("customerName", result.getCustomerName())
        .add("shipAddress", result.getShipAddress())
        .add("notes", result.getNotes())
        .add("tax", result.getTax())
        .add("items", itemsList)
        .build();

        return orderJson;

    }
    
}

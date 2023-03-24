package day24.workshop.utils;

import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class JsonConverters {

    public static JsonObject convertToJson(Orders order) {

        JsonArrayBuilder items = Json.createArrayBuilder();

        for(OrderDetails item : order.getItems()) {

            JsonObjectBuilder itemJson = Json.createObjectBuilder()
            .add("product", item.getProduct())
            .add("unit_price", item.getUnitPrice())
            .add("quantity", item.getQuantity());

            items.add(itemJson);

        }

        
        JsonObject orderJson = Json.createObjectBuilder()
        .add("order_id", order.getOrderId())
        .add("order_date", order.getOrderDate().toString())
        .add("customer_name", order.getCustomerName())
        .add("ship_address", order.getShipAddress())
        .add("notes", order.getNotes())
        .add("order_details", items)
        .build();

        return orderJson;
        
        
    }
    
}

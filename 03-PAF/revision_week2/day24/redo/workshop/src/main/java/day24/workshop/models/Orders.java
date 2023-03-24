package day24.workshop.models;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Orders {

    private String orderId;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Double tax;

    private List<OrderDetails> items;

    public Orders() {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
    }
    
}

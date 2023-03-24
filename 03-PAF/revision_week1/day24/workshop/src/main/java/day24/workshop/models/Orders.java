package day24.workshop.models;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Orders {

    private String id;
    private Date orderDate;
    private String customerName;
    private String shipAddress;
    private String notes;
    private Float tax;

    private List<OrderDetails> orderList;
    
    public Orders() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
    }
}

package day24.workshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    private int id;
    private String orderId;
    private String product;
    private Float unitPrice;
    private Float discount;
    private int quantity;
    
}

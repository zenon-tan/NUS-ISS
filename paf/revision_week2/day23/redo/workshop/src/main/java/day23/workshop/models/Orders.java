package day23.workshop.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private int id;
    private Date orderDate;
    private int customerId;
    private Double totalPrice;
    private Double costPrice;
    
}

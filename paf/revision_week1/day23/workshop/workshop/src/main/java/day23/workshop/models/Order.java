package day23.workshop.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String id;
    private Date orderDate;
    private String customerId;
    private Float orderPrice;
    private Float costPrice;
    
}

package ssfassessment.pizza.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PizzaOrder {

    private String id;
    
    @Future(message = "Delivery date can only be in the future")
    private Date deliveryDate;
    private String isRush;

    @Size(max = 255, message = "Keep your message within 255 characters")
    private String comments;
    private Float totalCost;
    private String customerId;

    private Customer customer;
    private PizzaCart cart;

    public PizzaOrder() {

        this.id = UUID.randomUUID().toString().substring(0,8);
    }
    
}

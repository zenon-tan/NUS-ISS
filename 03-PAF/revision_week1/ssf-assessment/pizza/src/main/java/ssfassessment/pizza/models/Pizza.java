package ssfassessment.pizza.models;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    private String pizzaName = "";
    private Float size;

    @Max(value = 10, message = "You can only add up to 10 quantities per pizza")
    private int quantity;

    private PizzaDB pizzaDb;
    
}

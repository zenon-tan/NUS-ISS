package ssfassessment.pizza.models;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PizzaCart {

    private String cartId;
    private Float price;

    @NotEmpty(message = "Please choose at least 1 pizza")
    private List<Pizza> pizzaList;

    public PizzaCart() {
        this.cartId = UUID.randomUUID().toString().substring(0,8);
    }
    
}

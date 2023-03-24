package ssfassessment.pizza.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDB {

    private int pizzaId;
    private String pizzaName;
    private Float price;
    private String imgUrl;
    
}

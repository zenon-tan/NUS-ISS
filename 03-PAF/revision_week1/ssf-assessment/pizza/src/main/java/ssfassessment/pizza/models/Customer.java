package ssfassessment.pizza.models;

import java.sql.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Customer {

    private String customerId;
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 64, message = "Name must be between 3 and 64 characters")
    private String name;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{8}$", message = "Please enter an 8-digit phone number")
    private String phone;

    @NotEmpty(message = "Address is required")
    private String address;
    private PizzaCart cart;


    public Customer() {

        this.customerId = UUID.randomUUID().toString().substring(0,8);

    }
    
}

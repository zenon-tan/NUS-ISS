package ssfassessment.pizza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import ssfassessment.pizza.services.OrderService;

@RestController
@RequestMapping("/api/order")
public class PizzaRestController {

    @Autowired
    OrderService oSrc;

    @GetMapping("/{id}")
    public ResponseEntity<String> getJsonById(@PathVariable(name = "id") String id) {

        JsonObject result = oSrc.getOrderJsonById(id);
        if(result.isEmpty()) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result.toString(), HttpStatus.OK);
    }
    
}

package day24.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day24.workshop.models.Orders;
import day24.workshop.services.OrderService;
import day24.workshop.utils.JsonConverters;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    OrderService oSrc;

    @GetMapping("/order/{id}")
    public ResponseEntity<String> getOrderById(@PathVariable(name = "id") String id) {

        String result = oSrc.findOrderFromRedis(id);

        if(result != null) {

            return new ResponseEntity<>(result, HttpStatus.OK);

        }
        

        return new ResponseEntity<>("Order does not exist", HttpStatus.NOT_FOUND);

        
    }
    
}

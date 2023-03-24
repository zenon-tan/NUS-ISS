package day23.workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day23.workshop.models.Order;
import day23.workshop.repositories.OrderRepo;

@RestController
@RequestMapping("/api/order/total")
public class OrderRestController {

    @Autowired
    OrderRepo oRepo;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getAllOrdersById(@PathVariable(name = "id") int id) {

        List<Order> result = oRepo.getOrdersFromId(id);
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(result, HttpStatus.OK);

        
    }
    
}

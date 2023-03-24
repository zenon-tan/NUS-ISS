package day21.workshop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day21.workshop.models.Customer;
import day21.workshop.models.Order;
import day21.workshop.repo.CustomerRepo;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerRepo cRepo;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers(
        @RequestParam(name = "limit", defaultValue = "5") int limit, @RequestParam(name = "offset", defaultValue = "0") int offset) {

            List<Customer> result = cRepo.getAllCustomers(limit, offset);
            if(result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
            
        }

    @GetMapping("/{id}")
    public ResponseEntity<List<Customer>> getCustomerById(@PathVariable(name = "id") int id) {

        List<Customer> result = cRepo.getCustomerById(id);
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getOrdersById(@PathVariable(name = "id") int id) {

        List<Order> result = cRepo.getOrderByCustomerId(id);
        if(result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
}

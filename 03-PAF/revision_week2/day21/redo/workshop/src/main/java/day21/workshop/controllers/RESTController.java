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
import day21.workshop.models.Orders;
import day21.workshop.repositories.CustomerRepo;
import day21.workshop.repositories.OrdersRepo;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    CustomerRepo cRepo;

    @Autowired
    OrdersRepo oRepo;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "5") int limit) {

        List<Customer> results = cRepo.getAllCustomer(limit, offset);

        return new ResponseEntity<>(results, HttpStatus.OK);

    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") int id) {

        Optional<Customer> result = cRepo.getCustomerById(id);

        if(result.isPresent()) {

            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
    @GetMapping("/customer/{id}/orders")
    public ResponseEntity<List<Orders>> getOrdersByCid(@PathVariable(name = "id")int id) {

        List<Orders> results = oRepo.getAllOrdersByCustId(id);

        if(results.isEmpty()) {
            return new ResponseEntity<>(results, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(results, HttpStatus.OK);
        
    }
}

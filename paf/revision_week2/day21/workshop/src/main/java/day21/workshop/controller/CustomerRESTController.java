package day21.workshop.controller;

import java.util.List;

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
import day21.workshop.repo.CustomerRepo;

@RestController
@RequestMapping
public class CustomerRESTController {

    @Autowired
    CustomerRepo cRepo;

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(defaultValue = "0") int offset, 
    @RequestParam(defaultValue = "5") int limit) {

        List<Customer> result = cRepo.getAllCustomer(offset, limit);

        if(result.size() == 0) {

            return new ResponseEntity<>(result, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    
    }

    @GetMapping("/api/customer/{id}")
    public ResponseEntity<Customer> getAllCustomers(@PathVariable(name = "id") int id) {

        Customer result = cRepo.getCustomerById(id);

        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/api/customer/{id}/orders")
    public ResponseEntity<List<Orders>> getAllOrdersById(@PathVariable(name = "id") int id) {

        List<Orders> result = cRepo.getOrdersByCustomerId(id);

        if(result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    
}

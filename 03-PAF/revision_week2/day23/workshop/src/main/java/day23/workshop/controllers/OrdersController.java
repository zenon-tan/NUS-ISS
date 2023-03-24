package day23.workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import day23.workshop.models.Orders;
import day23.workshop.repo.OrdersRepo;

@Controller
@RequestMapping
public class OrdersController {

    @Autowired
    OrdersRepo oRepo;

    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("id", new Orders());

        return "index";
        
    }

    @PostMapping("/")
    public String redirectToPage(@ModelAttribute(name = "id") Orders orders, Model model) {

        System.out.println(orders.getId());

        return "redirect:/order/total/" + orders.getId();

        
    }

    @GetMapping("/order/total/{id}")
    public String getOrdersById(@PathVariable int id, Model model) {

        List<Orders> result = oRepo.getOrdersById(id);
        model.addAttribute("id", id);
        model.addAttribute("orders", result);

        return "vieworder";

    }
    
    
}

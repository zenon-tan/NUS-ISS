package day23.workshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day23.workshop.models.Orders;
import day23.workshop.repositories.OrderRepo;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    OrderRepo oRepo;

    @GetMapping("/")
    public String getForm(Model model) {

        model.addAttribute("order", new Orders());

        return "index";
    }

    @PostMapping("/")
    public String getId(Model model, @ModelAttribute(name = "order") Orders order) {

        int orderId = order.getId();

        return "redirect:/order/total/" + orderId;
    }

    @GetMapping("/order/total/{id}")
    public String getAllOrders(@PathVariable(name = "id") int id, Model model) {

        List<Orders> results = oRepo.getAllOrdersById(id);
        model.addAttribute("id", id);
        model.addAttribute("orders", results);

        return "orders";

    }
    
}

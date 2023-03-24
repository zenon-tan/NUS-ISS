package day23.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import day23.workshop.models.Order;
import day23.workshop.repositories.OrderRepo;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    OrderRepo oRepo;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("id", new Id());
        return "index";
    }

    @PostMapping("/")
    public String redirectToResult(Model model, Id id) {
        return "redirect:/order/total/" + id.getId();
    }

    @GetMapping("/order/total/{id}")
    public String getAllOrdersById(@PathVariable(name = "id") int id, Model model) {

        model.addAttribute("id", id);
        model.addAttribute("allorders", oRepo.getOrdersFromId(id));
        return "results";
        
    }
    
}

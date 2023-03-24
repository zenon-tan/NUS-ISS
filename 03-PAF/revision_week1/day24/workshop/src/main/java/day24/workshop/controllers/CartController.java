package day24.workshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day24.workshop.exceptions.OrderException;
import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import day24.workshop.repositories.OrderDetailsRepo;
import day24.workshop.repositories.OrdersRepo;
import day24.workshop.services.OrderService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class CartController {

    @Autowired
    OrdersRepo oRepo;

    @Autowired
    OrderDetailsRepo odRepo;

    @Autowired
    OrderService oSrc;

    @GetMapping("/")
    public String showForm(Model model, HttpSession sessions) {

        List<OrderDetails> cart = (List<OrderDetails>) sessions.getAttribute("cart");
        if(cart == null) {
            
            cart = new ArrayList<>();
            sessions.setAttribute("cart", cart);
        }

        model.addAttribute("item", new OrderDetails());
        model.addAttribute("cart", new ArrayList<OrderDetails>());
        return "index";
    }

    @PostMapping("/cart")
    public String addToCart(Model model, OrderDetails item, HttpSession sessions) {

        List<OrderDetails> cart = (List<OrderDetails>) sessions.getAttribute("cart");

        cart.add(item);
        model.addAttribute("cart", cart);
        model.addAttribute("item", item);

        return "index";

    }


    @PostMapping("/checkout")
    public String checkout(Model model, HttpSession sessions) {

        List<OrderDetails> cart = (List<OrderDetails>) sessions.getAttribute("cart");
        Orders newOrder = new Orders();
        newOrder.setOrderList(cart);

        model.addAttribute("newOrder", newOrder);
        model.addAttribute("cart", cart);

        return "checkout";
    }

    @PostMapping("/confirmation")
    public String showConfirmation(Model model, Orders newOrder, HttpSession sessions) throws OrderException {
        List<OrderDetails> cart = (List<OrderDetails>) sessions.getAttribute("cart");
        newOrder.setOrderList(cart);
        try {

            Boolean saved = oSrc.createOrder(newOrder);
            if(saved) {

                sessions.invalidate();
    
            }
    
            
        } catch (OrderException e) {
            // TODO: handle exception
        }

        model.addAttribute("newOrder", newOrder);
        model.addAttribute("cart", cart);
        

        return "thankyou";

    }

    @GetMapping("/orders/{id}")
    public String getOrdersById(Model model, @PathVariable(name = "id") String id) {

        Orders order = oSrc.getOrderById(id);
        model.addAttribute("order", order);

        return "orderdetails";
    }
    
}

package day24.workshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day24.workshop.models.OrderDetails;
import day24.workshop.models.Orders;
import day24.workshop.services.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/")
public class OrderController {

    @Autowired
    OrderService oSrc;

    @GetMapping
    public String showForm(Model model) {

        model.addAttribute("item", new OrderDetails());

        return "index";
        
    }

    @PostMapping
    public String addToCart(Model model, HttpSession session, @ModelAttribute(name = "item") OrderDetails item) {

        List<OrderDetails> cart = (List<OrderDetails>) session.getAttribute("cart");
        if(cart == null) {

            cart = new ArrayList<>();
            session.setAttribute("cart", cart);

        }

        cart.add(item);

        model.addAttribute("cart", cart);

        return "index";

    }

    @PostMapping("/checkout")
    public String checkoutForm(Model model, HttpSession session) {

        List<OrderDetails> cart = (List<OrderDetails>) session.getAttribute("cart");

        Orders order = new Orders();
        order.setItems(cart);

        model.addAttribute("order", order);

        return "checkout";

    }

    @PostMapping("/thankyou")
    public String confirmation(Model model, @ModelAttribute(name = "order") Orders order, HttpSession session) {

        List<OrderDetails> cart = (List<OrderDetails>) session.getAttribute("cart");
        order.setItems(cart);
        oSrc.insertOrder(order);
        model.addAttribute("order", order);
        session.invalidate();

        return "thankyou";
        

    }
    
}

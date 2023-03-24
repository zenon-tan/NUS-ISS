package ssfassessment.pizza.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ssfassessment.pizza.exceptions.PizzaException;
import ssfassessment.pizza.models.Customer;
import ssfassessment.pizza.models.Pizza;
import ssfassessment.pizza.models.PizzaCart;
import ssfassessment.pizza.models.PizzaDB;
import ssfassessment.pizza.models.PizzaOrder;
import ssfassessment.pizza.repositories.PizzaDBRepo;
import ssfassessment.pizza.services.OrderService;

@Controller
@RequestMapping
public class PizzaController {

    @Autowired
    PizzaDBRepo pRepo;

    @Autowired
    OrderService oSrc;

    @GetMapping("/")
    public String showForm(Model model) {

        PizzaCart cart = new PizzaCart();

        List<Pizza> pizzaList = new ArrayList<>();

        List<PizzaDB> pizzas = pRepo.getAllPizzas();
        model.addAttribute("pizzas", pizzas);

        for(int i = 0; i <= pizzas.size(); i++) {

            pizzaList.add(new Pizza());

        }
        // System.out.println("List size:" + pizzaList.size());
        cart.setPizzaList(pizzaList);
        // System.out.println("List size:" + cart.getPizzaList().get(0).toString());
        model.addAttribute("cart", cart);
        return "index";

    }

    @PostMapping("/pizza")
    public String getPizza(@Valid PizzaCart cart, BindingResult result, Model model, HttpSession session) {

        if(cart == null) {
            cart = new PizzaCart();
            session.setAttribute("cart", new PizzaCart());
        }
        List<Pizza> orderedPizza = new ArrayList<>();

        for(Pizza p : cart.getPizzaList()) {
            if(p.getPizzaName() != null) {

                orderedPizza.add(p);
                System.out.println(p.getPizzaName());

            }
        }


        cart.setPizzaList(orderedPizza);

        if(result.hasErrors()) {

            return "index";
        }

        Float cost = oSrc.calculatePrice(cart.getPizzaList());
        cart.setPrice(cost);

        session.setAttribute("cart", cart);
        model.addAttribute("cart", cart);
        model.addAttribute("customer", new Customer());
        model.addAttribute("order", new PizzaOrder());

        return "orderform";
    }

    @PostMapping("/pizza/order")
    public String postOrderForm(@Valid Customer customer, 
    @Valid PizzaOrder order, BindingResult result, Model model, HttpSession session) throws PizzaException {

        if(result.hasErrors()) {
            return "orderform";
        }
        PizzaCart cart = (PizzaCart) session.getAttribute("cart");
       
        order.setCart(cart);
        order.setCustomer(customer);

        if(order.getIsRush() == null) {
            order.setIsRush("false");

            order.setTotalCost(order.getCart().getPrice());

        }else if(order.getIsRush().equals("true")) {
            order.setTotalCost(order.getCart().getPrice() + 2);
        }

        oSrc.insertIntoDB(order);

        model.addAttribute("order", order);

        return "ordered";
    }

    @GetMapping("/order/{id}")
    public String getOrderInfo(Model model, @PathVariable(name = "id") String id) {
        PizzaOrder order = oSrc.getOrderById(id);
        model.addAttribute("order", order);
        return "ordered";
    }
    
}

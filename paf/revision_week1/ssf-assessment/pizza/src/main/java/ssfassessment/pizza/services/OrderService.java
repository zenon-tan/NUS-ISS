package ssfassessment.pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import ssfassessment.pizza.exceptions.PizzaException;
import ssfassessment.pizza.models.Customer;
import ssfassessment.pizza.models.Pizza;
import ssfassessment.pizza.models.PizzaCart;
import ssfassessment.pizza.models.PizzaOrder;
import ssfassessment.pizza.repositories.CustomerRepo;
import ssfassessment.pizza.repositories.PizzaCartRepo;
import ssfassessment.pizza.repositories.PizzaOrderRepo;
import ssfassessment.pizza.repositories.PizzaRepo;

@Service
public class OrderService {

    @Autowired
    PizzaRepo pRepo;

    @Autowired
    PizzaCartRepo pcRepo;

    @Autowired
    PizzaOrderRepo poRepo;

    @Autowired
    CustomerRepo cRepo;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = PizzaException.class)
    public void insertIntoDB(PizzaOrder order){

        Boolean cRepoSaved = cRepo.insertCustomer(order.getCustomer());
        Boolean poRepoSaved = poRepo.insertOrder(order);
        Boolean pcRepoSaved = pcRepo.insertCart(order);

        // Boolean poMongoSaved = poRepo.insertOrderToMongo(order);
        pRepo.addPizza(order.getCart());

        if(!cRepoSaved || !poRepoSaved || !pcRepoSaved) {
            throw new PizzaException("Failed to save");
        }

        // if(true) {
        //     throw new PizzaException("Failed to save");
        // }

    }

    public JsonObject getOrderJsonById(String id) {
        PizzaOrder order = getOrderById(id);

        JsonObjectBuilder customer = Json.createObjectBuilder()
        .add("name", order.getCustomer().getName())
        .add("phone", order.getCustomer().getPhone())
        .add("address", order.getCustomer().getAddress());

        JsonArrayBuilder pizzas = Json.createArrayBuilder();

        for(Pizza p : order.getCart().getPizzaList()) {

            JsonObjectBuilder pizza = Json.createObjectBuilder()
            .add("pizza_name", p.getPizzaName())
            .add("size", p.getSize())
            .add("quantity", p.getQuantity());

            pizzas.add(pizza);
        }


        JsonObject result = Json.createObjectBuilder()
        .add("id", order.getId())
        .add("customer", customer)
        .add("delivery_date", order.getDeliveryDate().toString())
        .add("is_rush", order.getIsRush())
        .add("comments", order.getComments())
        .add("price", order.getCart().getPrice())
        .add("total_cost", order.getTotalCost())
        .add("pizzas_ordered", pizzas)
        .build();

        return result;
    }

    public PizzaOrder getOrderById(String id) {
        PizzaOrder result = poRepo.selectOrderById(id);

        Customer customer = cRepo.getCustomerById(result.getCustomerId());
        result.setCustomer(customer);

        PizzaCart cart = pcRepo.getCartById(id);
        result.setCart(cart);

        List<Pizza> pizzaList = pRepo.getPizzaByCartId(result.getCart().getCartId());
        cart.setPizzaList(pizzaList);

        return result;
    }

    public Float calculatePrice(List<Pizza> pizzaList) {

        Float cost = 0f;
        for(Pizza p : pizzaList) {

            if(p.getPizzaName().equalsIgnoreCase("bella")) {

                cost += 20 * p.getQuantity() * p.getSize();

            } else if(p.getPizzaName().equalsIgnoreCase("margherita") || p.getPizzaName().equalsIgnoreCase("marinara")) {

                cost += 22 * p.getQuantity() * p.getSize();

            } else if(p.getPizzaName().equalsIgnoreCase("spianatacalabrese") || p.getPizzaName().equalsIgnoreCase("trioformaggio")) {

                cost += 24 * p.getQuantity() * p.getSize();
            }
            
        }

        return cost;
    }
    
}

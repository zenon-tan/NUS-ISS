package day24.workshop.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import day24.workshop.exception.OrderException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({OrderException.class})
    public ModelAndView exceedOrderLimit(HttpServletRequest req, OrderException ex) {

        final ModelAndView mav = new ModelAndView("exceed_order_limit.html");
        mav.addObject("exceeded", "You cannot order more than 3 items!");

        return mav;
    }
    
}

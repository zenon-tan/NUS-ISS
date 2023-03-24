package day27.workshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import day27.workshop.models.Review;
import day27.workshop.services.ReviewService;

@Controller
@RequestMapping
public class ReviewController {

    @Autowired
    ReviewService rSrc;

    @GetMapping("/review")
    public String showForm(Model model) {

        model.addAttribute("review", new Review());

        return "form";
    }

    @PostMapping("/review")
    public String postReview(Model model, @ModelAttribute(name = "review") Review review) {

        String result = rSrc.insertReview(review);
        model.addAttribute("result", result);

        return "form";

    }

    @GetMapping("/review/{id}")
    public String showUpdateForm(Model model, @PathVariable(name = "id") String oId) {

        model.addAttribute("oId", oId);

        Review review = rSrc.getReviewById(oId);

        model.addAttribute("review", review);

        model.addAttribute("newReview", new Review());

        return "update";
    }

    @PostMapping("/review/{id}")
    public String updateReview(Model model, @PathVariable(name = "id") String oId, @ModelAttribute(name = "newReview") Review newReview) {

        model.addAttribute("oId", oId);

        String result = rSrc.updateReview(newReview, oId);
        model.addAttribute("result", result);

        Review updatedReview = rSrc.getReviewById(oId);
        model.addAttribute("review", updatedReview);


        return "update";
    }
    
}

package com.pricing.controller;

import com.pricing.model.Review;
import com.pricing.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable int productId) {
        return reviewRepository.findByProductId(productId);
    }
}
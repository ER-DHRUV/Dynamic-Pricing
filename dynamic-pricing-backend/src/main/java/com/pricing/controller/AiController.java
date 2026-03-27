package com.pricing.controller;

import com.pricing.model.Review;
import com.pricing.repository.ReviewRepository;
import com.pricing.service.GeminiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai")
@CrossOrigin
public class AiController {

@Autowired
private GeminiService geminiService;

@Autowired
private ReviewRepository reviewRepository;

@PostMapping("/description/{productId}")
public Map<String, String> getDescription(@PathVariable int productId) {

    List<Review> reviews = reviewRepository.findByProductId(productId);

    String combinedText = reviews.stream()
            .map(Review::getReviewText)
            .collect(Collectors.joining(" "));

    String summary = geminiService.generateSummary(combinedText);

    return Map.of("summary", summary);
}


}
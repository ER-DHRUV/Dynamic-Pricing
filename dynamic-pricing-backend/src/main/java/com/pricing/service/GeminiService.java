package com.pricing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent?key=";

    public String generateSummary(String text) {

        RestTemplate restTemplate = new RestTemplate();

        // 🔥 Prompt (VERY IMPORTANT)
        String prompt = "Summarize the following product reviews in 3-4 lines with sentiment and key insights:\n" + text;

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> requestBody = Map.of("contents", List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    URL + apiKey,
                    entity,
                    Map.class
            );

            // 🔥 Extract response safely
            List candidates = (List) response.getBody().get("candidates");
            Map first = (Map) candidates.get(0);
            Map contentMap = (Map) first.get("content");
            List parts = (List) contentMap.get("parts");
            Map textMap = (Map) parts.get(0);

            return textMap.get("text").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Unable to generate AI summary at the moment.";
        }
    }
}
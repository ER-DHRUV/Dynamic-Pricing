package com.pricing.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class MLService {

 private final String ML_API = "http://localhost:5000/optimize-price";

 public Map<String,Object> getOptimalPrice(Map<String,Object> data){

  RestTemplate restTemplate = new RestTemplate();

  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);

  HttpEntity<Map<String,Object>> request =
          new HttpEntity<>(data, headers);

  ResponseEntity<Map> response =
      restTemplate.postForEntity(ML_API, request, Map.class);

  return response.getBody();
 }
}
package com.pricing.service; // adjust this to your actual package structure

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;

import com.pricing.model.Product;
import com.pricing.model.PriceHistory;
import com.pricing.repository.ProductRepository;
import com.pricing.repository.PriceHistoryRepository;

@Service
public class PricingService {

 @Autowired
 ProductRepository productRepository;

 @Autowired
 PriceHistoryRepository historyRepository;

 @Autowired
 MLService mlService;

 public Map<String,Object> optimizeProduct(Long productId){

  Product product = productRepository.findById(productId).orElseThrow();

  Map<String,Object> data = new HashMap<>();

  data.put("Price", product.getCurrentPrice());
  data.put("Competitor Pricing", product.getCompetitorPrice());
  data.put("Discount", product.getDiscount());
  data.put("Promotion", product.isPromotion() ? 1 : 0);
  data.put("Inventory Level", product.getInventoryLevel());
  data.put("Category", product.getCategory());
  data.put("Region", product.getRegion());
  data.put("Epidemic", product.isEpidemic() ? 1 : 0);

  Map<String,Object> result = mlService.getOptimalPrice(data);

  double optimalPrice = (Double) result.get("optimal_price");
  double demand = (Double) result.get("predicted_demand");
  double revenue = (Double) result.get("expected_revenue");

  PriceHistory history = new PriceHistory();

  history.setProductId(productId);
  history.setOldPrice(product.getCurrentPrice());
  history.setNewPrice(optimalPrice);
  history.setPredictedDemand(demand);
  history.setExpectedRevenue(revenue);
  history.setUpdatedAt(LocalDateTime.now());

  historyRepository.save(history);

  return result;
 }
}
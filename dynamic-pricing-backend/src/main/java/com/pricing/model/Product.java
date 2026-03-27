package com.pricing.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 @Column(name="product_name")
 private String productName;

 @Column(name="current_price")
 private double currentPrice;

 @Column(name="competitor_price")
 private double competitorPrice;

 @Column(name="discount")
 private double discount;

 @Column(name="inventory_level")
 private int inventoryLevel;

    private String category;

    private String region;

    private boolean promotion;

    private boolean epidemic;

    // Default constructor
    public Product() {}

    // Parameterized constructor (optional)
    public Product(String productName, double currentPrice, double competitorPrice, double discount,
                   int inventoryLevel, String category, String region, boolean promotion, boolean epidemic) {
        this.productName = productName;
        this.currentPrice = currentPrice;
        this.competitorPrice = competitorPrice;
        this.discount = discount;
        this.inventoryLevel = inventoryLevel;
        this.category = category;
        this.region = region;
        this.promotion = promotion;
        this.epidemic = epidemic;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getCompetitorPrice() {
        return competitorPrice;
    }

    public void setCompetitorPrice(double competitorPrice) {
        this.competitorPrice = competitorPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public boolean isEpidemic() {
        return epidemic;
    }

    public void setEpidemic(boolean epidemic) {
        this.epidemic = epidemic;
    }
}

package com.example.fetch_assignment.model;

import java.util.List;

public class Receipt {

    private String id;
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private String total;
    private List<Item> items;

    public Receipt (String id, String retailer, String purchaseDate, String purchaseTime, String total, List<Item> items) {
        this.id = id;
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static record Item (String shortDescription, String price){}
}

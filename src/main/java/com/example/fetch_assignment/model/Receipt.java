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

    public void setId(String id) {
        this.id = id;
    }

    public static record Item (String shortDescription, String price){}
}

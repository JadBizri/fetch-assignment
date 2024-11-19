package com.example.fetch_assignment.model;

import java.util.List;

public record Receipt (long id, String retailer, String purchaseDate, String purchaseTime, String total, List<Item> items ){
    public static record Item (String shortDescription, String price){}
}

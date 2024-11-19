package com.example.fetch_assignment.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Receipt {

    private String id;

    @NotBlank(message="Retailer cannot be empty")
    private String retailer;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message="Purchase date must be in the form yyyy-mm-dd")
    private String purchaseDate;

    @Pattern(regexp = "\\d{2}:\\d{2}", message="Purchase time must be in the form hh:mm")
    private String purchaseTime;

    @Pattern(regexp = "\\d+\\.\\d{2}", message = "Total must be in a valid currency format with 2 decimals")
    private String total;

    @NotNull(message = "Items cannot be null")
    @Size(min = 1, message = "There must be at least one item")
    private List<@Valid Item> items;

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

    public static record Item (@NotBlank(message="Short description cannot be blank") String shortDescription,
                               @Pattern(regexp = "\\d+\\.\\d{2}", message = "Price must be in a valid currency format with 2 decimals") String price){}
}

package com.example.fetch_assignment.service;

import com.example.fetch_assignment.exceptions.InvalidInputException;
import com.example.fetch_assignment.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {

    private final Map<String, Integer> receiptPoints = new ConcurrentHashMap<>();

    private final List<Receipt> receipts = new ArrayList<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        //in case uuid gets duplicated because I know my luck
        while (receiptPoints.containsKey(id)) {
            id = UUID.randomUUID().toString();
        }
        receipt.setId(id);
        int points = calculatePoints(receipt);
        receiptPoints.put(id, points);
        receipts.add(receipt);
        return id;
    }

    public int getPoints(String id) {
        if (receiptPoints.containsKey(id)) return receiptPoints.get(id);
        else throw new InvalidInputException("Receipt not found: " + id);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        // One point for every alphanumeric character in the retailer name.
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // 50 points if the total is a round dollar amount with no cents.
        if (receipt.getTotal().matches("\\d+\\.00")) points += 50;

        // 25 points if the total is a multiple of 0.25.
        if (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) points += 25;

        // 5 points for every two items on the receipt.
        points += (receipt.getItems().size() / 2) * 5;

        // If the trimmed length of the item description is a multiple of 3,
        // multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for (Receipt.Item item : receipt.getItems()) {
            if (item.shortDescription().trim().length() % 3 == 0) {
                points += (int) Math.ceil(Double.parseDouble(item.price()) * 0.2);
            }
        }

        // 6 points if the day in the purchase date is odd.
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 != 0) points += 6;

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        String[] time = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        if ((hour == 14 && minute != 0) || (hour == 15)) points += 10;

        return points;
    }
}

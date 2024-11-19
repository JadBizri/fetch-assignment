package com.example.fetch_assignment.service;

import com.example.fetch_assignment.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReceiptService {

    private final Map<String, Integer> receiptPoints = new ConcurrentHashMap<>();

    private List<Receipt> receipts = new ArrayList<>();

    public String processReceipt(Receipt receipt) {
        //UUID https://docs.oracle.com/javase/8/docs/api/index.html?java/util/UUID.html
        String id = UUID.randomUUID().toString();
        int points = calculatePoints(receipt);
        receiptPoints.put(id, points);
        receipts.add(receipt);
        return id;
    }

    public int getPoints(String id) {
        return receiptPoints.getOrDefault(id, 0);
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        // One point for every alphanumeric character in the retailer name.
        points += receipt.retailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // 50 points if the total is a round dollar amount with no cents.
        if (receipt.total().matches("\\d+\\.00")) points += 50;

        // 25 points if the total is a multiple of 0.25.
        if (Double.parseDouble(receipt.total()) % 0.25 == 0) points += 25;

        // 5 points for every two items on the receipt.
        points += (receipt.items().size() / 2) * 5;

        // If the trimmed length of the item description is a multiple of 3,
        // multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        for (Receipt.Item item : receipt.items()) {
            if (item.shortDescription().trim().length() % 3 == 0) {
                points += (int) Math.ceil(Double.parseDouble(item.price()) * 0.2);
            }
        }

        // 6 points if the day in the purchase date is odd.
        int day = Integer.parseInt(receipt.purchaseDate().split("-")[2]);
        if (day % 2 != 0) points += 6;

        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        String[] time = receipt.purchaseTime().split(":");
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        if ((hour == 14 && minute != 0) || (hour == 15)) points += 10;

        return points;
    }
}

package com.example.fetch_assignment.controller;

import com.example.fetch_assignment.exceptions.InvalidInputException;
import com.example.fetch_assignment.model.Receipt;
import com.example.fetch_assignment.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
public class ReceiptController {

    private final ReceiptService receiptService;
    private final Pattern idPattern = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    // gets all receipts
    @GetMapping("/receipts")
    public List<Receipt> getReceipts() {
        return receiptService.getReceipts();
    }

    @GetMapping("/receipts/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable String id) {
        if (!idPattern.matcher(id).matches()) {
            throw new InvalidInputException("Invalid receipt id: " + id);
        }
        int points = receiptService.getPoints(id);
        return Map.of("points", points);
    }

    @PostMapping("/receipts/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        // TODO: validate receipt
        String id = receiptService.processReceipt(receipt);
        return Map.of("id", id);
    }
}

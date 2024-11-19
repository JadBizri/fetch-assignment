package com.example.fetch_assignment.controller;

import com.example.fetch_assignment.model.Receipt;
import com.example.fetch_assignment.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ReceiptController {

    private final ReceiptService receiptService;

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
        // (OPTIONAL) TODO: validate id to be in the form of a UUID (i.e. not '1' or '2') and ensure id exists
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

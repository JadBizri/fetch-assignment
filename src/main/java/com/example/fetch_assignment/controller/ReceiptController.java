package com.example.fetch_assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ReceiptController {

    @GetMapping("/receipts/{id}/points")
    public Map<String, Integer> getPoints(@PathVariable long id) {

    }
}

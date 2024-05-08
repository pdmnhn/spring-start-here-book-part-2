package org.example.controllers;

import java.util.List;
import org.example.models.Purchase;
import org.example.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @GetMapping
    List<Purchase> getAllPurchases() {
        return purchaseRepository.getAllPurchases();
    }

    @PostMapping
    public ResponseEntity<Void> createPurchaseOrder(@RequestBody Purchase purchase) {
        HttpStatus status = purchaseRepository.createPurchaseOrder(purchase)
                ? HttpStatus.CREATED
                : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).build();
    }
}

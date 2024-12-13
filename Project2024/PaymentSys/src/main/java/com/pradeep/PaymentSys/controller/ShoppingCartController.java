package com.pradeep.PaymentSys.controller;


import com.pradeep.PaymentSys.ItemRepository;
import com.pradeep.PaymentSys.model.Item;
import com.pradeep.PaymentSys.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {
    private final ItemRepository itemRepository;
    private final PaymentService paymentService;

    @Autowired
    public ShoppingCartController(ItemRepository itemRepository, PaymentService paymentService) {
        this.itemRepository = itemRepository;
        this.paymentService = paymentService;
    }

    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @PostMapping("/checkout/{paymentType}")
    public String checkout(@PathVariable String paymentType, @RequestParam double amount) {
        System.out.println(paymentType);
        paymentService.processPayment(paymentType, amount);
        return "Payment processed successfully.";
    }
}


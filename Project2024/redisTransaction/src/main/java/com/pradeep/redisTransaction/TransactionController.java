package com.pradeep.redisTransaction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction){
        transactionService.saveTransaction(transaction);
        return transaction;
    }

    //get a single trans
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable String id){
        Optional<Transaction> transaction =  transactionService.getTransactionById(id);

        if(transaction.isPresent()){
            return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
        transactionService.deleteTransactionById(id);
        return ResponseEntity.ok("Transaction deleted.");
    }

    @PostMapping("/generate/{count}")
    public ResponseEntity<String> generateTransactions(@PathVariable int count) {
        transactionService.generateBulkTransactions(count);
        return ResponseEntity.ok(count + " transactions generated successfully.");
    }
}

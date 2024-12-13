package com.pradeep.redisTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RedisTemplate<String, Transaction> redisTemplate;

    public Transaction saveTransaction(Transaction transaction) {
        redisTemplate.opsForValue().set(transaction.getId(), transaction);
        return transactionRepository.save(transaction);
    }

    // List of Indian names for senders and receivers
    private static final List<String> INDIAN_NAMES = List.of(
            "Aarav", "Vivaan", "Aditya", "Vihaan", "Arjun",
            "Sai", "Krishna", "Aryan", "Shiv", "Raghav",
            "Ishaan", "Kabir", "Arnav", "Laksh", "Advik",
            "Mira", "Riya", "Ananya", "Priya", "Saanvi",
            "Ishita", "Tanya", "Aditi", "Kavya", "Nisha",
            "Ravi", "Anil", "Suresh", "Deepak", "Manoj",
            "Rajesh", "Sunil", "Amit", "Pooja", "Neha",
            "Anjali", "Kiran", "Rekha", "Shalini", "Simran"
    );

    private static final List<String> CURRENCY = List.of(
            "USD", "INR", "EUR"
    );

    private static final Random random = new Random();
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // Method to generate bulk transactions
    public void generateBulkTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Transaction txn = new Transaction();
            txn.setId(UUID.randomUUID().toString());

            // Randomly assign sender and receiver names
            String sender = INDIAN_NAMES.get(random.nextInt(INDIAN_NAMES.size()));
            String receiver;
            do {
                receiver = INDIAN_NAMES.get(random.nextInt(INDIAN_NAMES.size()));
            } while (receiver.equals(sender));  // Ensure sender != receiver

            txn.setSender(sender);
            txn.setReceiver(receiver);

            // Random amount with 2 decimal places
            double amount = 100 + (10000 - 100) * random.nextDouble(); // Amount between 100 and 10,000
            txn.setAmount(Double.valueOf(decimalFormat.format(amount)));

            txn.setCurrency( CURRENCY.get(random.nextInt(CURRENCY.size())));  // Use INR for Indian transactions

            transactions.add(txn);
        }
        transactionRepository.saveAll(transactions);
    }

    public Optional<Transaction> getTransactionById(String id) {
        Transaction transaction = redisTemplate.opsForValue().get(id);
        if (transaction != null) {
            return Optional.of(transaction);
        }
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransactionById(String id) {
        transactionRepository.deleteById(id);
    }
}

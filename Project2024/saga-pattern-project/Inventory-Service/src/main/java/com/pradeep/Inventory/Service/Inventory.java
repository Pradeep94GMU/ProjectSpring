package com.pradeep.Inventory.Service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Inventory {

    private int id;
    private String productName;
    private int stock;

    // Getters and Setters

    public int getId() {
        return id;
    }


}

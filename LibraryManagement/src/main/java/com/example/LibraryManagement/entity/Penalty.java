package com.example.LibraryManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "penalty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "paid_penalty")
    private boolean isPaid;
    @Column(name = "penalty_amount")
    private Double amount;
}

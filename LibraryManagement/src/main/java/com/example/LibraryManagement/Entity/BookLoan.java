package com.example.LibraryManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rent_date")
    private LocalDateTime rentDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @ManyToOne
    @JoinColumn(name ="book_id")
    private Book bookLoan;
}

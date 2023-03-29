package com.example.LibraryManagement.Entity;

import com.example.LibraryManagement.Enums.BookAvailability;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer ISBN;
    @Column(name = "book_name")
    @NotNull
    private String bookName;
    @Column(name = "rent_price")
    @NotNull
    private Float rentPrice;
    @Column(name = "is_rented")
    @NotNull
    private boolean isRented;
    @Column(name = "number_of_copies")
    @NotNull
    private Integer numberOfCopies;
    @Column(name = "book_availability")
    @NotNull
    @Enumerated(EnumType.STRING)
    BookAvailability bookAvailability;
    @OneToMany(mappedBy = "book")
    private List<Reservation> reservationList;
    @OneToMany(mappedBy = "bookLoan")
    List<BookLoan> bookLoans;
    @ManyToMany
    Set<Author> authors;

}

package com.example.LibraryManagement.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private int bookID;
    private String bookName;
    private boolean rented;
    private float rentPrice;
    private int bookCopies;
    private String bookAvailability;
}

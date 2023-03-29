package com.example.LibraryManagement.mapper;

import com.example.LibraryManagement.Enums.BookAvailability;
import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.Entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    private BookMapper() {

    }

    public Book toEntity(BookDTO bookDTO) {
        return Book.builder()
                .ISBN(bookDTO.getBookID())
                .bookName(bookDTO.getBookName())
                .isRented(bookDTO.isRented())
                .numberOfCopies(bookDTO.getBookCopies())
                .rentPrice(bookDTO.getRentPrice())
                .bookAvailability(BookAvailability.valueOf(bookDTO.getBookAvailability()))
                .build();
    }

    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .bookID(book.getISBN())
                .bookName(book.getBookName())
                .rented(book.isRented())
                .bookCopies(book.getNumberOfCopies())
                .rentPrice(book.getRentPrice())
                .bookAvailability(String.valueOf(book.getBookAvailability()))
                .build();
    }
}

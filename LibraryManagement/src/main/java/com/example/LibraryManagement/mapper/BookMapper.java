package com.example.LibraryManagement.mapper;

import com.example.LibraryManagement.enums.Status;
import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.entity.Book;
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
                .status(Status.valueOf(bookDTO.getBookAvailability()))
                .build();
    }

    public BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .bookID(book.getISBN())
                .bookName(book.getBookName())
                .rented(book.isRented())
                .bookCopies(book.getNumberOfCopies())
                .rentPrice(book.getRentPrice())
                .bookAvailability(String.valueOf(book.getStatus()))
                .build();
    }
}

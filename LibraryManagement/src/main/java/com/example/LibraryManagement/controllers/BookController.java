package com.example.LibraryManagement.controllers;

import com.example.LibraryManagement.Repositories.BookRepository;
import com.example.LibraryManagement.Service.BookService;
import com.example.LibraryManagement.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "books")
@CrossOrigin
public class BookController {
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add-books")
    public ResponseEntity<Object> addNewBook(@RequestBody BookDTO book) {
        try {
            BookDTO insertBook = bookService.addBooks(book);
            return new ResponseEntity<>(insertBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-all-books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookDTOList = bookService.getAllBooks();
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable("id") Integer id) {
        BookDTO bookDTO = bookService.findById(id);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/get-available-books")
    public ResponseEntity<List<BookDTO>> getAvailableBooks() {
        List<BookDTO> bookDTOList = bookService.findAvailableBooks();
        return new ResponseEntity<>(bookDTOList, HttpStatus.OK);
    }

    @PutMapping(path = "/update-book-copies/{id}")
    public ResponseEntity<BookDTO> updateCopies(@RequestBody BookDTO bookDTO) {
        bookService.updateBook(bookDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.OK);
    }

    @PutMapping("/updateBook/{id}/{status}")
    public ResponseEntity<String> updateBookByStatus(@PathVariable Integer id, @PathVariable boolean status) {
        return new ResponseEntity<String>(bookRepository.updateBook(id, status) + " book(s) updated.", HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteBook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Integer id) {
        try {
            bookService.deleteById(id);
            return new ResponseEntity<>("Book has been successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

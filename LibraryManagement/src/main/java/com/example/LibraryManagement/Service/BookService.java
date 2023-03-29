package com.example.LibraryManagement.Service;

import com.example.LibraryManagement.Entity.Book;
import com.example.LibraryManagement.Repositories.BookRepository;
import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.exception.BadRequestException;
import com.example.LibraryManagement.exception.NotFoundException;
import com.example.LibraryManagement.mapper.BookMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> booksList = bookRepository.findAll();
        return booksList.stream()
                .map(bookMapper::toDTO)
                .collect(toList());
    }

    public BookDTO addBooks(BookDTO bookDTO) {
        checkIfExistsByName(bookDTO.getBookName());
        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    public BookDTO findById(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(bookMapper::toDTO).orElse(null);
    }

    public void updateBook(BookDTO bookDTO) {
        Optional<Book> book = bookRepository.findById(bookDTO.getBookID());
        if (book.isPresent()) {
            Book bookToUpdate = book.get();
            bookToUpdate.setNumberOfCopies(bookDTO.getBookCopies());
            bookRepository.save(bookToUpdate);
        }
    }

    public void deleteById(Integer id) {
        checkIfExistsByISBN(id);
        bookRepository.deleteById(id);
    }

    public List<BookDTO> findAvailableBooks() {
        List<Book> bookList = bookRepository.getAvailableBooks();
        return bookList.stream()
                .map(bookMapper::toDTO)
                .collect(toList());
    }

    private void checkIfExistsByName(String name) {
        if (bookRepository.existsByBookName(name)) {
            throw new BadRequestException("Book with name: %s already exists".formatted(name));
        }
    }

    private void checkIfExistsByISBN(Integer id) {
        if (!bookRepository.existsByISBN(id)) {
            throw new NotFoundException("Book with ISBN: %d does not exists".formatted(id));
        }
    }
}



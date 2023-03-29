package com.example.LibraryManagement.Repositories;

import com.example.LibraryManagement.Entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book b set b.isRented = :rented where b.ISBN = :id")
    Integer updateBook(@Param("id") Integer id, @Param("rented") boolean rented);

    @Transactional
    @Query("SELECT b from Book b WHERE b.bookAvailability = AVAILABLE")
    public List<Book> getAvailableBooks();

    boolean existsByBookName(String bookName);
    boolean existsByISBN(Integer id);
}

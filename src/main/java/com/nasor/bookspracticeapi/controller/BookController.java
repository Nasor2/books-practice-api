package com.nasor.bookspracticeapi.controller;

import com.nasor.bookspracticeapi.model.Book;
import com.nasor.bookspracticeapi.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return  ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        // find the book
        var book = bookRepository.findById(id);

        // if value is not present, return NOT_FOUND status and no body
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book.get());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookRepository.save(book);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // get actual URI (http://localhost:8080/books)
                .path("/{id}") // add variable path to URI for ID /books/{id}
                .buildAndExpand(savedBook.getId()) // reemplace {id} with the actual ID from saved book
                .toUri(); // convert component URI to java object java.net.URI

        return ResponseEntity.created(location).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book updateBook) {
        var existentBook = bookRepository.findById(id); // find book

        if (existentBook.isEmpty()){
            return ResponseEntity.notFound().build(); // returns NOT_FOUND status if not found instance
        }

        // reemplace attributes
        Book newBook = existentBook.get();
        newBook.setTitle(updateBook.getTitle());
        newBook.setAuthor(updateBook.getAuthor());
        newBook.setCategory(updateBook.getCategory());
        newBook.setDescription(updateBook.getDescription());
        newBook.setPublicationDate(updateBook.getPublicationDate());

        // save new instance
        Book savedBook = bookRepository.save(newBook);
        return ResponseEntity.ok(savedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // returns NOT_FOUND status if not found instance
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

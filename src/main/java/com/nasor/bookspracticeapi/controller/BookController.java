package com.nasor.bookspracticeapi.controller;

import com.nasor.bookspracticeapi.model.Book;
import com.nasor.bookspracticeapi.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Book", description = "Book management operations in API")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // GET all books
    @Operation(summary = "Get all books",
            description = "Returns a list of all books.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))) // Returns list of books
    })
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return  ResponseEntity.ok(bookRepository.findAll());
    }

    @Operation(summary = "Get Book by ID",
            description = "Returns an specified book by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "Book id")
            @PathVariable Long id
    ) {
        // find the book
        var book = bookRepository.findById(id);

        // if value is not present, return NOT_FOUND status and no body
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book.get());
    }

    @Operation(summary = "Create Book",
            description = "Add a new book, ID will be generated automatically")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400",
                    content = @Content)
    })
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

    @Operation(summary = "Update Book",
            description = "Update book information by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "Book id")
            @PathVariable Long id,
            @Valid @RequestBody Book updateBook
    ) {
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

    @Operation(summary = "Delete book",
            description = "Delete an existent book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "Book id")
            @PathVariable Long id
    ) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // returns NOT_FOUND status if not found instance
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

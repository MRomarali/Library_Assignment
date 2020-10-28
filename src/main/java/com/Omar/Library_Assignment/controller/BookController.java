package com.Omar.Library_Assignment.controller;

import com.Omar.Library_Assignment.Repositories.BookRepository;
import com.Omar.Library_Assignment.entities.Book;
import com.Omar.Library_Assignment.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/ALL")
    public ResponseEntity<List<Book>> findAllBooks(@RequestParam(required = false) String title){
        return ResponseEntity.ok(bookService.findAll(title));
    }

    @Secured({"ROLE_ADMIN","ROLE_BORROWER"})
    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook( @PathVariable String id, @RequestBody   Book book){
        bookService.update(id, book);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteBook(@PathVariable @RequestBody String id) {
        try{
            bookRepo.deleteById(id);
        } catch(NumberFormatException ex){

        }
    }

    @Secured({"ROLE_ADMIN", "ROLE_BORROWER"})
    @PutMapping("/borrow/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Optional<Book> borrow(@PathVariable String id){
        return bookRepo.findById(id).map(book -> {
            book.setAvailable(false);
            return bookRepo.save(book);
        });



    }
    @Secured({"ROLE_ADMIN", "ROLE_BORROWER"})
    @PutMapping("/return/{id}")
    public Optional<Book> returnBook(@PathVariable String id){
        return bookRepo.findById(id).map(book -> {
            book.setAvailable(true);
            return bookRepo.save(book);
        });
    }
}
package de.unikoeln.se.bookstore.controller;

import de.unikoeln.se.bookstore.datamodel.Book;
import de.unikoeln.se.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("/bookStore")
@RestController
public class BookController {

    @Autowired
    BookService bookSer;

    @GetMapping
    public ResponseEntity<List<Book>> getAllbooks(){
        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBookt(@RequestBody Book book){
        bookSer.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> removeBookById(@PathVariable int id){
        Book book = bookSer.fetchBook(id).get();
        if(bookSer.deleteBook(id))
            return new ResponseEntity<>(book, HttpStatus.OK);
        else
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "/newest")
    public ResponseEntity<Book> getNewestBook(){
        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();
        Book book = Collections.max(books, Comparator.comparing(Book::getYear));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping(value = "/oldest")
    public ResponseEntity<Book> getOldestBook() {
        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();
        Book book = Collections.min(books, Comparator.comparing(Book::getYear));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}

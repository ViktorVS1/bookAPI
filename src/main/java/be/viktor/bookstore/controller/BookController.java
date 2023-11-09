package be.viktor.bookstore.controller;
import be.viktor.bookstore.api.BooksApi;
import be.viktor.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController implements BooksApi {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/books")
    @Override
    public ResponseEntity<List<Book>> _booksGet() {
        List<Book> books = bookService.getAllBooks();
        if (books != null && !books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/books/{id}")
    @Override
    public ResponseEntity<Book> _booksIdGet(Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/books")
    @Override
    public ResponseEntity<Book> _booksPost(Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    @Override
    public ResponseEntity<Book> _booksIdPut(Integer id, Book book) {
        return null;
    }

    @DeleteMapping("/books/{id}")
    @Override
    public ResponseEntity<Void> _booksIdDelete(Integer id) {
        Optional<Book> optionalBook = bookService.getBookById(id);

        if (optionalBook.isPresent()) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Book deleted successfully
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Book not found
        }
    }






}

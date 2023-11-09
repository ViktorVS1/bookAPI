package be.viktor.bookstore.service;


import be.viktor.bookstore.controller.Book;
import be.viktor.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(String.valueOf(id));
    }

    public boolean deleteBookById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(String.valueOf(id));

        if (optionalBook.isPresent()) {
            bookRepository.deleteById(String.valueOf(id));
            return true; // Book deleted successfully
        } else {
            return false; // Book not found
        }
    }
}

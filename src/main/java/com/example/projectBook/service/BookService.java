package com.example.projectBook.service;

import com.example.projectBook.model.Book;
import com.example.projectBook.model.Filter;
import com.example.projectBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(books::add);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookById(long id) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<List<Book>> getBooksByFilter(Filter filter) {
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(books::add);
        for (Book b:bookRepository.findAll()) {
            if(filter.author!=null && !b.getAuthor().equals(filter.author)) {
                books.remove(b);
            }
            if(filter.bookLanguage!=null && !b.getBookLanguage().equals(filter.bookLanguage)) books.remove(b);
            if(filter.genre!=null && !b.getGenre().equals(filter.genre)) books.remove(b);
            if(filter.minScore!=null && filter.maxScore!=null &&( (b.getScoresSum()/b.getNbScores())<=filter.minScore|| (b.getScoresSum()/b.getNbScores())>=filter.maxScore)) books.remove(b);
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<Book> createBook(Book book) {
        try {
            Book _book = bookRepository.save(book);
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> updateBook(Book book) {
        Optional<Book> bookData = bookRepository.findById(book.getId());

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setTitle(book.getTitle());
            _book.setDescription(book.getDescription());
            _book.setBookLanguage(book.getBookLanguage());
            _book.setAuthor(book.getAuthor());
            _book.setPrice(book.getPrice());
            _book.setReserved(book.getReserved());
            _book.setGenre(book.getGenre());
            _book.setNbScores(book.getNbScores());
            _book.setScoresSum(book.getScoresSum());
            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteOne(long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<HttpStatus> rateBook(long id,Integer score) {
        Optional<Book> bookData = bookRepository.findById(id);
        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setNbScores(_book.getNbScores()+1);
            _book.setScoresSum(_book.getScoresSum()+score);
            bookRepository.save(_book);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

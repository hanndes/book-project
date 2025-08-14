package com.handedereli.bookproject.services;
import com.handedereli.bookproject.controller.dto.request.BookRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse createBook(BookRequest dto) {
        Book book = Book.setAll(dto);
        Book saved = bookRepository.save(book);

        return BookResponse.from(saved);
    }

    @Transactional
    public BookResponse deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
        return BookResponse.from(book);
    }

    @Transactional
    public BookResponse updateBook(Integer id, BookRequest dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Book.setAll(dto);
        return BookResponse.from(book);
    }

    @Transactional(readOnly = true)
    public Book getBookEntityById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
@Transactional(readOnly = true)
    public BookResponse getBookById(Integer bookId) {
        Book book = getBookEntityById(bookId);
        return BookResponse.from(book);
    }
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responseList = new ArrayList<>();

        for (Book book : books) {
            BookResponse dto = BookResponse.from(book);
            responseList.add(dto);
        }

        return responseList;
    }
}

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
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());

        Book saved = bookRepository.save(book);

        return new BookResponse(
                saved.getTitle(),
                saved.getAuthor(),
                saved.getCategory()
        );
    }


    @Transactional
    public BookResponse deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);

        return new BookResponse(
                book.getTitle(),
                book.getAuthor(),
                book.getCategory()
        );
    }


    @Transactional
    public BookResponse updateBook(Integer id, BookRequest dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());

        Book updated = bookRepository.save(book);

        return new BookResponse(
                updated.getTitle(),
                updated.getAuthor(),
                updated.getCategory()
        );
    }


    public Book getBookEntityById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(Integer bookId) {
        Book book = getBookEntityById(bookId);
        return new BookResponse(
                book.getTitle(),
                book.getAuthor(),
                book.getCategory()
        );
    }


    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responseList = new ArrayList<>();
        for (Book book : books) {
            BookResponse dto = new BookResponse(
                    book.getTitle(),
                    book.getAuthor(),
                    book.getCategory()
            );
            responseList.add(dto);
        }
        return responseList;
    }


}

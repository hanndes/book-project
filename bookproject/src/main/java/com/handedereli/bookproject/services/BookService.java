package com.handedereli.bookproject.services;
import com.handedereli.bookproject.controller.dto.request.BookRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import com.handedereli.bookproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public BookResponse createBook(BookRequest dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());

        Book saved = bookRepository.save(book);

        return new BookResponse(
                saved.getTitle(),
                saved.getAuthor()
        );
    }

    public BookResponse deleteBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.deleteById(id);

        return new BookResponse(
                book.getTitle(),
                book.getAuthor()
        );
    }

    public BookResponse updateBook(Integer id, BookRequest dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());
        Book saved = bookRepository.save(book);
        return new BookResponse(
                saved.getTitle(),
                saved.getAuthor()
        );
    }

    public BookResponse getBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return new BookResponse(
                book.getTitle(),
                book.getAuthor()
        );
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponse> responseList = new ArrayList<>();

        for (Book book : books) {
            BookResponse dto = new BookResponse(
                    book.getTitle(),
                    book.getAuthor()
            );
            responseList.add(dto);
        }

        return responseList;
    }


    @Transactional(readOnly = true)
    public List<BookResponse> getBooksByUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<BookResponse> result = new ArrayList<>();
        for (Book b : user.getBooks()) {
            result.add(new BookResponse(b.getTitle(), b.getAuthor()));
        }
        return result;
    }

}

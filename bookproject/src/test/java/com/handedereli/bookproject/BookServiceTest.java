package com.handedereli.bookproject;

import com.handedereli.bookproject.controller.dto.request.BookRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.repositories.BookRepository;
import com.handedereli.bookproject.services.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
 class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    void testCreateBook_success() {
        BookRequest request = new BookRequest("Sefiller", "Victor Hugo", "Roman");

        Book book = new Book();
        book.setId(1);
        book.setTitle("Sefiller");
        book.setAuthor("Victor Hugo");
        book.setCategory("Roman");

        Mockito.when(bookRepositoryMock.save(any(Book.class))).thenReturn(book);

        BookResponse response = bookService.createBook(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Sefiller", response.title());
        Assertions.assertEquals("Victor Hugo", response.author());
        Assertions.assertEquals("Roman", response.category());
    }
    @Test
    void testDeleteBook_success() {
        Book book = new Book();
        book.setId(2);
        book.setTitle("Suç ve Ceza");
        book.setAuthor("Dostoyevski");
        book.setCategory("Roman");

        Mockito.when(bookRepositoryMock.findById(2)).thenReturn(Optional.of(book));

        BookResponse response = bookService.deleteBook(2);

        Assertions.assertEquals("Suç ve Ceza", response.title());
        verify(bookRepositoryMock).delete(book);
    }
}

package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.BookRequestDTO;
import com.handedereli.bookproject.controller.dto.BookResponseDTO;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDTO createBook(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());

        Book saved = bookRepository.save(book);

        return new BookResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor()
        );
    }
    public void deleteBook(Integer id){
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }

        bookRepository.deleteById(id);
    }


    public BookResponseDTO updateBook(Integer id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());
        Book saved = bookRepository.save(book);
        return new BookResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor()
        );
    }

    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResponseDTO> responseList = new ArrayList<>();

        for (Book book : books) {
            BookResponseDTO dto = new BookResponseDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor()
            );
            responseList.add(dto);
        }

        return responseList;
    }





}

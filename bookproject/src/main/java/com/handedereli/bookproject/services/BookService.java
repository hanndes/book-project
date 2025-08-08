package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.BookRequestDTO;
import com.handedereli.bookproject.controller.dto.BookResponseDTO;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDTO createBook(BookRequestDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());

        Book saved = bookRepository.save(book);

        return new BookResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getAuthor()
        );
    }



    public void deleteBook(Integer id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
    }

    public BookResponseDTO updateBook(Integer id, BookRequestDTO dto ){
    return
    }
}

package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.AssignBookToUserRequestDTO;
import com.handedereli.bookproject.controller.dto.UserRequestDTO;
import com.handedereli.bookproject.controller.dto.UserResponseDTO;
import com.handedereli.bookproject.controller.dto.UserWithBooksResponseDTO;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.BookAlreadyAssignedException;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import com.handedereli.bookproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.name());
        user.setGender(request.gender());

        User saved = userRepository.save(user);

        return new UserResponseDTO(saved.getName(), saved.getGender());
    }

    //@Transactional
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        for (Book book : user.getBooks()) {
            book.setUser(null);
        }

        userRepository.delete(user);
    }

@Transactional //Son satıra kadar hata çıkmazsa commit, hata fırlarsa rollback olur – veritabanı tutarsız kalmaz.
    public UserWithBooksResponseDTO assignBookToUser(AssignBookToUserRequestDTO request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new UserNotFoundException(request.userId()));

        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new BookNotFoundException(request.bookId()));

        if (book.getUser() != null) {
            throw new BookAlreadyAssignedException(request.bookId());
        }
        book.setUser(user);
        bookRepository.save(book);

        List<String> bookTitles = new ArrayList<>();

        for (Book books : user.getBooks()) {
            bookTitles.add(books.getTitle());
        }


        return new UserWithBooksResponseDTO(
                user.getName(),
                user.getGender(),
                bookTitles);
    }

    public UserWithBooksResponseDTO getUserBooks(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        List<String> bookTitles = new ArrayList<>();
        for (Book book : user.getBooks()) {
            bookTitles.add(book.getTitle());
        }

        return new UserWithBooksResponseDTO(
                user.getName(),
                user.getGender(),
                bookTitles
        );
    }

}

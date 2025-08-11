package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.request.AssignBookToUserRequest;
import com.handedereli.bookproject.controller.dto.request.UserRequest;
import com.handedereli.bookproject.controller.dto.response.UserResponse;
import com.handedereli.bookproject.controller.dto.response.UserWithBooksResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.BookAlreadyAssignedException;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import com.handedereli.bookproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setGender(request.gender());

        User saved = userRepository.save(user);

        return new UserResponse(saved.getName(), saved.getGender());
    }

    public UserResponse deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        UserResponse dto = new UserResponse(
                user.getName(),
                user.getGender()
        );

        for (Book book : user.getBooks()) {
            book.setUser(null);
        }
        userRepository.delete(user);
        return dto;
    }


    public UserWithBooksResponse assignBookToUser(AssignBookToUserRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new UserNotFoundException(request.userId()));

        Book book = bookRepository.findById(request.bookId()).orElseThrow(() -> new BookNotFoundException(request.bookId()));

        if (book.getUser() != null) {
            throw new BookAlreadyAssignedException(request.bookId());
        }
        book.setUser(user);
        bookRepository.save(book);

        return new UserWithBooksResponse(
                user.getName(),book.getTitle());
    }


}

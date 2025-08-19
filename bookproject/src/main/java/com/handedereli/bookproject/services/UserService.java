package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.request.AssignBookToUserRequest;
import com.handedereli.bookproject.controller.dto.request.UserRequest;
import com.handedereli.bookproject.controller.dto.response.UserResponse;
import com.handedereli.bookproject.controller.dto.response.UserWithBooksResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.BookAlreadyAssignedException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;

    @Transactional
    public UserResponse createUser(UserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setGender(request.gender());

        User saved = userRepository.save(user);

        return new UserResponse(
                saved.getName(),
                saved.getGender()
        );
    }

    @Transactional
    public UserResponse deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return new UserResponse(user.getName(), user.getGender());
    }

    @Transactional
    public UserResponse getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return new UserResponse(user.getName(), user.getGender());
    }

    @Transactional
    public UserWithBooksResponse assignBookToUser(AssignBookToUserRequest request) {
        Integer userId = request.userId();
        Integer bookId = request.bookId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Book book = bookService.getBookEntityById(bookId);

        if (book.getOwner() != null && !book.getOwner().getId().equals(userId)) {
            throw new BookAlreadyAssignedException(bookId, false);
        }
        if (book.getOwner() != null && book.getOwner().getId().equals(userId)) {
            throw new BookAlreadyAssignedException(bookId, true);
        }

        book.setOwner(user);
        user.getOwnedBooks().add(book);

        return new UserWithBooksResponse(user.getName(), book.getTitle());
    }



}

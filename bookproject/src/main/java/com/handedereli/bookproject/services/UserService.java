package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.request.AssignBookToUserRequest;
import com.handedereli.bookproject.controller.dto.request.UserRequest;
import com.handedereli.bookproject.controller.dto.response.BookResponse;
import com.handedereli.bookproject.controller.dto.response.UserResponse;
import com.handedereli.bookproject.controller.dto.response.UserWithBooksResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
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
       User saved = User.newUser(request.name(), request.gender());
        userRepository.save(saved);
        return UserResponse.from(saved);
    }

    @Transactional
    public UserResponse deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        return new UserResponse(user.getName(), user.getGender());
    }
    /*@Transactional
    public UserResponse changeProfile(Integer id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        User.changeProfile(user, request);
        return  new UserResponse(user.getName(), user.getGender());
    }*/

    @Transactional
    public UserWithBooksResponse assignBookToUser(AssignBookToUserRequest request) {
        Integer userId = request.userId();
        Integer bookId = request.bookId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Book book  = bookService.getBookEntityById(bookId);

        user.assignBooktoUser(book);
        return new UserWithBooksResponse(user.getName(), book.getTitle());
    }

}

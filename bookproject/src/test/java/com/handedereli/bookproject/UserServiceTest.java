package com.handedereli.bookproject;

import com.handedereli.bookproject.controller.dto.request.AssignBookToUserRequest;
import com.handedereli.bookproject.controller.dto.response.UserResponse;
import com.handedereli.bookproject.controller.dto.response.UserWithBooksResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.UserRepository;
import com.handedereli.bookproject.services.BookService;
import com.handedereli.bookproject.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private BookService bookServiceMock;

    @InjectMocks
    private UserService userService;  // test edilen gerçek sınıf

    @Test
    void testGetUser_success() {
        // Arrange
        User user = new User();
        user.setId(3);
        user.setName("Hande");
        user.setGender("F");

        Mockito.when(userRepositoryMock.findById(3)).thenReturn(Optional.of(user)); //stubbing

        // Act
        UserResponse response = userService.getUser(3);

        // Assert
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hande", response.name());
        Assertions.assertEquals("F", response.gender());
       }



    @Test
    void testGetUser_notFound() {
        Mockito.when(userRepositoryMock.findById(10)).thenReturn(Optional.empty());

       Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUser(10));
    }

    // DELETE
    @Test
    void testDeleteUser_success() {

        User user = new User();
        user.setId(1);
        user.setName("Ali Veli");
        user.setGender("M");
        Mockito.when(userRepositoryMock.findById(3)).thenReturn(Optional.of(user));

        UserResponse response = userService.deleteUser(3);

        Assertions.assertEquals("Ali Veli", response.name());
        Assertions.assertEquals("M", response.gender());
        verify(userRepositoryMock).delete(user);
    }

    @Test
    void testDeleteUser_notFound() {
        Mockito.when(userRepositoryMock.findById(10)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(10));
       // verify(userRepositoryMock, never()).delete(any(User.class));
    }

    // ASSIGN BOOK
    @Test
    void testAssignBookToUser_success() {
        User user = new User();
        user.setId(3);
        user.setName("Hande");

        Book book = new Book();
        book.setId(3);
        book.setTitle("Victor Hugo");

        AssignBookToUserRequest request = new AssignBookToUserRequest(3, 3);

        Mockito.when(userRepositoryMock.findById(3)).thenReturn(Optional.of(user));
        Mockito.when(bookServiceMock.getBookEntityById(3)).thenReturn(book);

        UserWithBooksResponse response = userService.assignBookToUser(request);

        Assertions.assertEquals("Hande", response.name());
        Assertions.assertEquals("Victor Hugo", response.bookName());
        Assertions.assertEquals(user, book.getOwner());
    }

    @Test
    void testAssignBookToUser_userNotFound() {
        AssignBookToUserRequest request = new AssignBookToUserRequest(15, 4);

        Mockito.when(userRepositoryMock.findById(15)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.assignBookToUser(request));
    }


}



package com.handedereli.bookproject.services;


import com.handedereli.bookproject.controller.dto.request.FavoriteRequest;
import com.handedereli.bookproject.controller.dto.response.FavoriteResponse;
import com.handedereli.bookproject.entities.Book;
import com.handedereli.bookproject.entities.Favorite;
import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.exceptions.AlreadyInFavoritesException;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import com.handedereli.bookproject.exceptions.UserNotFoundException;
import com.handedereli.bookproject.repositories.BookRepository;
import com.handedereli.bookproject.repositories.FavoriteRepository;
import com.handedereli.bookproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public FavoriteResponse addFavorite(FavoriteRequest dto) {

        if (favoriteRepository.existsByUserIdAndBookId(dto.userId(), dto.bookId())) {
            throw new AlreadyInFavoritesException(dto.userId(), dto.bookId());
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new BookNotFoundException(dto.bookId()));

        Favorite fav = new Favorite();
        fav.setUser(user);
        fav.setBook(book);
        fav.setAddedAt(LocalDate.now());

        Favorite saved = favoriteRepository.save(fav);

        return new FavoriteResponse(
                user.getName(),
                book.getTitle(),
                saved.getAddedAt()
        );
    }

    public List<FavoriteResponse> getFavoritesByUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        List<FavoriteResponse> result = new ArrayList<>();

        for (Favorite fav : favorites) {
            FavoriteResponse dto = new FavoriteResponse(
                    fav.getUser().getName(),
                    fav.getBook().getTitle(),
                    fav.getAddedAt()
            );
            result.add(dto);
        }

        return result;
    }
}

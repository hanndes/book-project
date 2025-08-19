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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public FavoriteResponse addFavorite(FavoriteRequest dto) {

        if (favoriteRepository.existsByUserIdAndBookId(dto.userId(), dto.bookId())) {
            throw new AlreadyInFavoritesException(dto.userId(), dto.bookId());
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new UserNotFoundException(dto.userId()));

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() -> new BookNotFoundException(dto.bookId()));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setBook(book);
        favorite.setAddedAt(java.time.LocalDate.now()); // Favorite entity’de vardı

        Favorite saved = favoriteRepository.save(favorite);

        return new FavoriteResponse(
                saved.getUser().getName(),
                saved.getBook().getTitle(),
                saved.getAddedAt()
        );
    }


    @Transactional
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

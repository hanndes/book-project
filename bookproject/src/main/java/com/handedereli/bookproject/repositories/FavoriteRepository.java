package com.handedereli.bookproject.repositories;

import com.handedereli.bookproject.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {

    boolean existsByUserIdAndBookId(Integer userId, Integer bookId);
    List<Favorite> findByUserId(Integer userId);
}

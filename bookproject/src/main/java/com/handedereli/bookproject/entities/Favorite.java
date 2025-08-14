package com.handedereli.bookproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "favorites",
        uniqueConstraints = @UniqueConstraint(name = "uk_user_book", columnNames = {"user_id","book_id"}))
@NoArgsConstructor
@Getter
public class Favorite {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate addedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    public static Favorite of(User user, Book book) {
        Favorite f = new Favorite();
        f.user = user;
        f.book = book;
        f.addedAt = LocalDate.now();
        return f;
    }
}


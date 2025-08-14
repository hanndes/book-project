package com.handedereli.bookproject.entities;
import com.handedereli.bookproject.exceptions.AlreadyInFavoritesException;
import com.handedereli.bookproject.exceptions.BookNotFoundException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String gender;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> ownedBooks = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private final List<Favorite> favorites = new ArrayList<>();

    public static User newUser(String name, String gender) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (gender == null || gender.isBlank()) throw new IllegalArgumentException("gender required");
        User u = new User();
        u.name = name;
        u.gender = gender;
        return u;
    }

    public void changeProfile(String name, String gender) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (gender == null || gender.isBlank()) throw new IllegalArgumentException("gender required");
        this.name = name;
        this.gender = gender;
    }

    public void assignBooktoUser(Book book) { //Integer bookId
        this.getOwnedBooks().add(book);
        book.setOwner(this);
    }


    public void addFavorite(Book book) {
        if (book == null) throw new BookNotFoundException(null);
        boolean exists = favorites.stream().anyMatch(f ->
                (f.getBook().getId() != null && f.getBook().getId().equals(book.getId())) || f.getBook() == book
        );
        if (exists) {
            throw new AlreadyInFavoritesException(this.id, book.getId());
        }
        favorites.add(Favorite.of(this, book));
    }

    public void removeFavoriteByBookId(Integer bookId) {
        if (bookId == null) throw new IllegalArgumentException("bookId required");
        for (Iterator<Favorite> it = favorites.iterator(); it.hasNext();) {
            Favorite f = it.next();
            if (Objects.equals(f.getBook().getId(), bookId)) {
                it.remove(); // orphanRemoval => DB’den silinir
                return;
            }
        }
    }
}

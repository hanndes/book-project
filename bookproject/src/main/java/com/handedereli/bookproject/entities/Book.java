package com.handedereli.bookproject.entities;


import com.handedereli.bookproject.controller.dto.request.BookRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String author;

    @Column(nullable=false)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name ="user_id", foreignKey = @ForeignKey(name = "fk_book_owner"))
    private User owner;

    public static Book setAll(BookRequest dto) {
        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setCategory(dto.category());
        return book;
    }
    public static Book newBook(String title, String author, String category) {
        Book b = new Book();
        b.change(title, author, category);
        return b;
    }

    public void change(String title, String author, String category) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title required");
        if (author == null || author.isBlank()) throw new IllegalArgumentException("author required");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("category required");
        this.title = title.trim();
        this.author = author.trim();
        this.category = category.trim();
    }

    void assignTo(User newOwner) {
        if (this.owner != null && this.owner != newOwner) {
            throw new IllegalStateException("Book already owned by another user");
        }
        this.owner = newOwner; // bidirectional ilişkiyi User tarafı da yönetecek
    }

    void releaseOwnership() {
        this.owner = null;
    }

    boolean ownedByAnother(User expected) {
        return this.owner != null && this.owner != expected;
    }
}

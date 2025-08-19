package com.handedereli.bookproject.entities;

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
}

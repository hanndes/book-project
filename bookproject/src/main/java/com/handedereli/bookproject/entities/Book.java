package com.handedereli.bookproject.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name ="category")
    private String category;

    @ManyToOne
    @JoinColumn(name ="user_id") //Bu yapı book tablosunda user_id adında bir foreign key oluşturur.
    private User user;
}

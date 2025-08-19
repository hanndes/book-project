package com.handedereli.bookproject.entities;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor()
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String gender;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Book> ownedBooks = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

}

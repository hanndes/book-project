package com.handedereli.bookproject.repositories;

import com.handedereli.bookproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select (count(b) > 0) from Book b where b.id = :bookId")
    boolean bookExists(@Param("bookId") Integer bookId);

    @Query("""
           select (count(b) > 0)
           from Book b
           where b.id = :bookId
             and b.owner is not null
             and b.owner <> :user
           """)
    boolean bookAssignedToOther(@Param("bookId") Integer bookId, @Param("user") User user);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Book b set b.owner = :user where b.id = :bookId")
    int assignBookToUser(@Param("bookId") Integer bookId, @Param("user") User user);

    @Query("select b.title from Book b where b.id = :bookId")
    String findBookTitle(@Param("bookId") Integer bookId);
}

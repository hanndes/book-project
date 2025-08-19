package com.handedereli.bookproject.repositories;

import com.handedereli.bookproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}

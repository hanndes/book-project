package com.handedereli.bookproject.services;


import com.handedereli.bookproject.entities.User;
import com.handedereli.bookproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User creatUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }
}

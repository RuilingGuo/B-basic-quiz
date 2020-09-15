package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(int id) {
        return userRepository.findUserById(id);
    }

    public User createUser(User user) {

        return userRepository.createUser(user);
    }
}

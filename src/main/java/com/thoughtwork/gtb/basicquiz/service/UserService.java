package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.repository.UserRepository_manual;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository_manual userRepositoryManual;

    public UserService(UserRepository_manual userRepositoryManual) {
        this.userRepositoryManual = userRepositoryManual;
    }

    public User findUserById(int id) {
        return userRepositoryManual.findUserById(id);
    }

    public User createUser(User user) {

        return userRepositoryManual.createUser(user);
    }
}

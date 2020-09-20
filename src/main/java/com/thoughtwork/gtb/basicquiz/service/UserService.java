package com.thoughtwork.gtb.basicquiz.service;

import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.dto.UserDto;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import com.thoughtwork.gtb.basicquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserById(Long id) {
        Optional<UserDto> optionalUserDto = userRepository.findById(id);
        if(!optionalUserDto.isPresent()){
            throw new UserNotFoundException("user is not existed");
        }
        return optionalUserDto.get();
    }

    public UserDto createUser(User user) {
        return userRepository.save(UserDto.bind(user));
    }
}

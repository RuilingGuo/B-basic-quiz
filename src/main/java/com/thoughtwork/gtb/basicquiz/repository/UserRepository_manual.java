package com.thoughtwork.gtb.basicquiz.repository;

import com.thoughtwork.gtb.basicquiz.domain.User;
import com.thoughtwork.gtb.basicquiz.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository_manual {
    private Map<Integer, User> userList;
    private static Integer USER_INC_NUM = 1;

    public UserRepository_manual() {
        this.userList = new HashMap<>();
        this.initUserList();
    }


    public User findUserById(Integer id) {
        Optional<User> userOptional = Optional.ofNullable(userList.get(id));
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("user not existed");
        }
        return userOptional.get();
    }

    public User createUser(User user) {
        user.setId(USER_INC_NUM++);
        userList.put(user.getId(),user);
        return user;
    }

    private void initUserList(){
        this.userList.put(USER_INC_NUM++,
                User.builder()
                .id(1)
                .name("KAMIL")
                .age(24)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit." +
                        " Repellendus, non, dolorem, cumque distinctio magni quam expedita " +
                        "velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores " +
                        "voluptatem dolorum! Quasi.")
                .build());
    }

    public boolean isUserExistedByUserId(Integer userId) {
        return userList.containsKey(userId);
    }
}

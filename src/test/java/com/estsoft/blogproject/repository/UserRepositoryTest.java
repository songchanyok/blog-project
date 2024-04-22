package com.estsoft.blogproject.repository;

import com.estsoft.blogproject.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void findByEmail() {
        //given
        User user = new User("mockEmail.com", "pw");
        userRepository.save(user);
        //when
        User returnUser = userRepository.findByEmail("mockEmail.com").orElseThrow();

        //then
        assertEquals(user.getEmail(),returnUser.getEmail());
    }

    @Test
    void testfindAll(){
        List<User> users = userRepository.findAll();


        assertEquals(0, users.size());
    }

    @Test
    void testUserSave(){
        User user = new User("mockingbird.com","pw");
        User returnUser = userRepository.save(user);
        User foundUser = userRepository.findByEmail(user.getEmail()).orElseThrow();

        assertEquals(returnUser.getEmail(),foundUser.getEmail());
        assertEquals(returnUser.getPassword(),foundUser.getPassword());
    }
}
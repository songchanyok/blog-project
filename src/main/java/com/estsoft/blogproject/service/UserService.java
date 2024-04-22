package com.estsoft.blogproject.service;

import com.estsoft.blogproject.domain.AddUserRequest;
import com.estsoft.blogproject.domain.User;
import com.estsoft.blogproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public User save(AddUserRequest dto){
        return repository.save(User.builder().email(dto.getEmail())
                .password(encoder.encode(dto.getPassword())).build());
    }
}

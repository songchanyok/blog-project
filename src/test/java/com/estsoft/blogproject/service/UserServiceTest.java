package com.estsoft.blogproject.service;

import com.estsoft.blogproject.domain.AddUserRequest;
import com.estsoft.blogproject.domain.User;
import com.estsoft.blogproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Spy                            //Mock으로 쓰면 encoder.encode()를 사용할 수 없다.
    BCryptPasswordEncoder encoder;

    @InjectMocks
    UserService userService;

    @Test
    void save() {
        //given
        AddUserRequest addUserRequest = new AddUserRequest("1234@test.com","1234");
        String encodedPassword = encoder.encode(addUserRequest.getPassword());

        doReturn(new User(addUserRequest.getEmail(), encodedPassword))
                .when(userRepository).save(any(User.class));
        //when
        User returnUser = userService.save(addUserRequest);


        //then
        Assertions.assertThat(returnUser.getEmail()).isEqualTo(addUserRequest.getEmail());
        Assertions.assertThat(returnUser.getPassword()).isEqualTo(encodedPassword);

        verify(encoder,times(2)).encode(any(String.class));
//        verify(userRepository,times(1)).save(any(User.class));
    }
}
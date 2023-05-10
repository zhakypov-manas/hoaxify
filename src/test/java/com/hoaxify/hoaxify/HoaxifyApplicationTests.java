package com.hoaxify.hoaxify;

import com.hoaxify.hoaxify.model.User;
import com.hoaxify.hoaxify.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

class HoaxifyApplicationTests {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;


    @Test
    public void postUser_WhenUserIsValid_receiveOk(){
        User user = createValidUser();

        ResponseEntity<Object> response = testRestTemplate.postForEntity("/users", user, Object.class);

        System.out.println(response.getStatusCode());
    }

    @Test
    public void postUser_WhenUserIsValid_userSavedToDatabase(){
        User user = createValidUser();

        assertThat(userRepository.count()).isEqualTo(2);
    }

    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage() {
        User user = createValidUser();
        List<User> users = userRepository.findAll();
        assertThat(user.getPassword()).isEqualTo(users.get(1).getPassword());
    }

    private User createValidUser() {
        User user = new User();
        user.setName("test-name");
        user.setLogin("test-login");
        user.setPassword("P4ssword");
        return user;
    }




}

package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.service.impl.UserServiceImpl;
import com.bpdts.app.bpdts.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void retrieveUsersFromParticularCity() {
        UserVO userOne = new UserVO();
        userOne.setEmail("test@gmail.com");
        userOne.setFirst_name("Phanthom");
        userOne.setLast_name("Test");

        ResponseEntity<List<UserVO>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(Collections.singletonList(userOne));

        when(
                restTemplate.exchange(
                        "https://bpdts-test-app.herokuapp.com/city/Bolton/users",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<UserVO>>() {}
                )
        ).thenReturn(responseEntity);

        assertEquals(1, userService.getAllUsersFromCity("Bolton").size());

        verify(restTemplate, times(1)).exchange(
                "https://bpdts-test-app.herokuapp.com/city/Bolton/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserVO>>() {}
        );
    }

    @Test
    public void retrieveAllUsers() {
        UserVO userOne = new UserVO();
        userOne.setEmail("test@gmail.com");
        userOne.setFirst_name("Elton");
        userOne.setLast_name("John");
        ResponseEntity<List<UserVO>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.getBody()).thenReturn(Collections.singletonList(userOne));

        when(
                restTemplate.exchange(
                        "https://bpdts-test-app.herokuapp.com/users",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<UserVO>>() {}
                )
        ).thenReturn(responseEntity);

        assertEquals(1, userService.getAllUsers().size());

        verify(restTemplate, times(1)).exchange(
                "https://bpdts-test-app.herokuapp.com/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserVO>>() {}
        );
    }
}

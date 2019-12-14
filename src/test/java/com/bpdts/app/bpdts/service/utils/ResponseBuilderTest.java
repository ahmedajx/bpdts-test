package com.bpdts.app.bpdts.service.utils;

import com.bpdts.app.bpdts.utils.ResponseBuilder;
import com.bpdts.app.bpdts.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ResponseBuilderTest {

    @InjectMocks
    private ResponseBuilder responseBuilder;

    @Test
    public void responseBuilderSuccess() {
        UserVO user = new UserVO();
        user.setFirst_name("Test");
        user.setFirst_name("Last");

        Set<UserVO> users = new HashSet<>();
        users.add(user);

        ResponseEntity responseEntity = responseBuilder.success(users);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
}

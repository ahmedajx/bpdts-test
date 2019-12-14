package com.bpdts.app.bpdts.service.impl;

import com.bpdts.app.bpdts.service.UserService;
import com.bpdts.app.bpdts.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    private static final String API_USERS_URL = "https://bpdts-test-app.herokuapp.com/users";

    private static final String API_USER_LOCATION_URL = "https://bpdts-test-app.herokuapp.com/city/";

    @Override
    @Cacheable(value="users")
    public List<UserVO> getAllUsers() {
        return restTemplate.exchange(
                API_USERS_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserVO>>() {}).getBody();
    }

    @Override
    @Cacheable(value="users")
    public List<UserVO> getAllUsersFromCity(String city) {
        StringBuilder locationBuilder = new StringBuilder(API_USER_LOCATION_URL);
        locationBuilder.append(city);
        locationBuilder.append("/users");

        return restTemplate.exchange(
                locationBuilder.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserVO>>() {}).getBody();
    }
}

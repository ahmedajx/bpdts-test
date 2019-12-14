package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.vo.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAllUsers();

    List<UserVO> getAllUsersFromCity(String city);
}

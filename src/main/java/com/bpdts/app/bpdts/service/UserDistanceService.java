package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.vo.UserVO;

import java.util.Set;

public interface UserDistanceService {

    Set<UserVO> getAllUserWithCertainDistanceFromCertainLocation();
}

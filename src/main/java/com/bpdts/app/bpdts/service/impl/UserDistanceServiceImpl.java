package com.bpdts.app.bpdts.service.impl;

import com.bpdts.app.bpdts.service.DistanceCalculator;
import com.bpdts.app.bpdts.service.UserDistanceService;
import com.bpdts.app.bpdts.service.UserService;
import com.bpdts.app.bpdts.vo.LocationVO;
import com.bpdts.app.bpdts.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDistanceServiceImpl implements UserDistanceService {

    private final UserService userService;

    private final DistanceCalculator distanceCalculator;

    @Value("${location.name}")
    private String locationName;

    @Override
    public Set<UserVO> getAllUserWithCertainDistanceFromCertainLocation() {
        Set<UserVO> usersWithInCertainLocation = new HashSet<>();

        List<UserVO> allUsers = userService.getAllUsers();

        allUsers.forEach(user -> {
            LocationVO locationVO = new LocationVO();
            locationVO.setLongitude(user.getLongitude());
            locationVO.setLatitude(user.getLatitude());
            // checking whether user is within 50 miles from london if so add it to final list.
            if (distanceCalculator.withinCertainMilesFromLocation(locationVO)){
                usersWithInCertainLocation.add(user);
            }
        });

        // users listed as living in London
        usersWithInCertainLocation.addAll(userService.getAllUsersFromCity(locationName));

        return usersWithInCertainLocation;
    }
}

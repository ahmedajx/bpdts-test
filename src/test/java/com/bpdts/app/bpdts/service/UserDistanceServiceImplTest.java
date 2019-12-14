package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.service.impl.UserDistanceServiceImpl;
import com.bpdts.app.bpdts.vo.LocationVO;
import com.bpdts.app.bpdts.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDistanceServiceImplTest {

    @InjectMocks
    private UserDistanceServiceImpl userDistanceService;

    private static final String LOCATION = "London";

    @Mock
    private DistanceCalculator distanceCalculator;

    @Mock
    private UserService userService;

    @Test
    public void returnZeroUsersAsAllUsersAreOutSideLondon() {

        ReflectionTestUtils.setField(userDistanceService, "locationName", LOCATION);

        UserVO userOne = new UserVO();
        userOne.setEmail("test@gmail.com");
        userOne.setFirst_name("Elton");
        userOne.setLast_name("John");
        userOne.setLatitude(8.5752);
        userOne.setLongitude(115.2903);

        UserVO userTwo = new UserVO();
        userTwo.setEmail("tesdst@gmail.com");
        userTwo.setFirst_name("John");
        userTwo.setLast_name("Smith");
        userTwo.setLatitude(8.1777);
        userTwo.setLongitude(115.1845);

        when(userService.getAllUsers()).thenReturn(Arrays.asList(userOne, userTwo));

        when(distanceCalculator.withinCertainMilesFromLocation(any(LocationVO.class))).thenReturn(false);

        when(userService.getAllUsersFromCity(LOCATION)).thenReturn(Collections.emptyList());

        Set<UserVO> usersFromWithInCertainLocation = userDistanceService.getAllUserWithCertainDistanceFromCertainLocation();

        assertEquals(0, usersFromWithInCertainLocation.size());
        assertFalse(usersFromWithInCertainLocation.contains(userOne));
        assertFalse(usersFromWithInCertainLocation.contains(userTwo));

        verify(userService, times(1)).getAllUsers();

        verify(distanceCalculator, times(2)).withinCertainMilesFromLocation(any(LocationVO.class));

        verify(userService, times(1)).getAllUsersFromCity(LOCATION);
    }

    @Test
    public void returnOneUsersAsTheRestOfUsersAreOutsideLondon() {
        ReflectionTestUtils.setField(userDistanceService, "locationName", LOCATION);

        UserVO userOne = new UserVO();
        userOne.setEmail("test@gmail.com");
        userOne.setFirst_name("Elton");
        userOne.setLast_name("John");
        userOne.setLatitude(8.5752);
        userOne.setLongitude(115.2903);

        UserVO userTwo = new UserVO();
        userTwo.setEmail("tesdst@gmail.com");
        userTwo.setFirst_name("John");
        userTwo.setLast_name("Smith");
        userTwo.setLatitude(51.509865);
        userTwo.setLongitude(-0.118092);

        LocationVO locationOne = new LocationVO();
        locationOne.setLatitude(userOne.getLatitude());
        locationOne.setLongitude(userOne.getLongitude());

        LocationVO locationTwo = new LocationVO();
        locationTwo.setLatitude(userTwo.getLatitude());
        locationTwo.setLongitude(userTwo.getLongitude());

        when(userService.getAllUsers()).thenReturn(Arrays.asList(userOne, userTwo));

        when(distanceCalculator.withinCertainMilesFromLocation(locationOne)).thenReturn(false);

        when(distanceCalculator.withinCertainMilesFromLocation(locationTwo)).thenReturn(true);

        when(userService.getAllUsersFromCity(LOCATION)).thenReturn(Collections.emptyList());

        Set<UserVO> usersFromWithInCertainLocation = userDistanceService.getAllUserWithCertainDistanceFromCertainLocation();

        assertEquals(1, usersFromWithInCertainLocation.size());
        assertTrue( usersFromWithInCertainLocation.contains(userTwo));
        assertFalse(usersFromWithInCertainLocation.contains(userOne));

        verify(userService, times(1)).getAllUsers();

        verify(distanceCalculator, times(1)).withinCertainMilesFromLocation(locationOne);
        verify(distanceCalculator, times(1)).withinCertainMilesFromLocation(locationTwo);

        verify(userService, times(1)).getAllUsersFromCity(LOCATION);
    }

    @Test
    public void returnOneUsersFromLocationEndpointOnly() {
        ReflectionTestUtils.setField(userDistanceService, "locationName", LOCATION);

        UserVO userOne = new UserVO();
        userOne.setEmail("testwe@gmail.com");
        userOne.setFirst_name("Elton");
        userOne.setLast_name("John");
        userOne.setLatitude(8.5752);
        userOne.setLongitude(115.2903);

        UserVO userTwo = new UserVO();
        userTwo.setEmail("test@gmail.com");
        userTwo.setFirst_name("John");
        userTwo.setLast_name("Smith");
        userTwo.setLatitude(51.509865);
        userTwo.setLongitude(-0.118092);

        LocationVO locationOne = new LocationVO();
        locationOne.setLatitude(userOne.getLatitude());
        locationOne.setLongitude(userOne.getLongitude());

        LocationVO locationTwo = new LocationVO();
        locationTwo.setLatitude(userTwo.getLatitude());
        locationTwo.setLongitude(userTwo.getLongitude());

        when(userService.getAllUsers()).thenReturn(Arrays.asList(userOne, userTwo));

        when(distanceCalculator.withinCertainMilesFromLocation(locationOne)).thenReturn(false);

        when(distanceCalculator.withinCertainMilesFromLocation(locationTwo)).thenReturn(false);

        UserVO userThree = new UserVO();
        userThree.setEmail("yser@gmail.com");
        userThree.setFirst_name("test user");
        userThree.setLast_name("one");
        userThree.setLatitude(13.5752);
        userThree.setLongitude(-5.2903);

        when(userService.getAllUsersFromCity(LOCATION)).thenReturn(Collections.singletonList(userThree));

        Set<UserVO> usersFromWithInCertainLocation = userDistanceService.getAllUserWithCertainDistanceFromCertainLocation();

        assertEquals(1, usersFromWithInCertainLocation.size());
        assertFalse(usersFromWithInCertainLocation.contains(userTwo));
        assertFalse(usersFromWithInCertainLocation.contains(userOne));
        assertTrue(usersFromWithInCertainLocation.contains(userThree));

        verify(userService, times(1)).getAllUsers();

        verify(distanceCalculator, times(1)).withinCertainMilesFromLocation(locationOne);
        verify(distanceCalculator, times(1)).withinCertainMilesFromLocation(locationTwo);

        verify(userService, times(1)).getAllUsersFromCity(LOCATION);
    }
}

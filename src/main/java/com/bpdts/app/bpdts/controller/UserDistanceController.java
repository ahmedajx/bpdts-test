package com.bpdts.app.bpdts.controller;

import com.bpdts.app.bpdts.service.UserDistanceService;
import com.bpdts.app.bpdts.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-distance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDistanceController {

    private final UserDistanceService userDistanceService;

    private final ResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity displayUsersFromLocation() {
        return responseBuilder.success(userDistanceService.getAllUserWithCertainDistanceFromCertainLocation());
    }
}

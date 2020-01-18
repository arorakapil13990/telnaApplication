package com.task.telna.controller;

import com.task.telna.entity.User;
import com.task.telna.exception.EmailIdAlreadyExistException;
import com.task.telna.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@ApiOperation(value = "userController", notes = "Operations pertaining to user", produces = "application/json")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    @ApiOperation(value = "Return user id ")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) throws EmailIdAlreadyExistException {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

}

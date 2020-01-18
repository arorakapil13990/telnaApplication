package com.task.telna.controller;

import com.task.telna.entity.Usage;
import com.task.telna.exception.UserNotFoundException;
import com.task.telna.service.UsageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@ApiOperation(value = "userController",
        notes = "Operations pertaining to user's usage",
        produces = "application/json")
@RequestMapping("/usage")
public class UsageController {

    @Autowired
    private UsageService usageService;

    @PostMapping
    @ApiOperation(value = "Usage information is saved successfully .")
    public ResponseEntity<?> saveUsageInformation(@Valid @RequestBody Usage usage) throws UserNotFoundException {
        return new ResponseEntity<>(usageService.saveUsage(usage), HttpStatus.OK);
    }

    @PostMapping("/history")
    @ApiOperation(value = "Return user's usage history.")
    public ResponseEntity<?> getAllUsageForAUser(@Valid @RequestBody Usage usage) throws ParseException, UserNotFoundException {
        return new ResponseEntity<>(usageService.getAllUsageForAUser(usage), HttpStatus.OK);
    }

}

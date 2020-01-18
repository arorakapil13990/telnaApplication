package com.task.telna.service;

import com.task.telna.constants.Constants;
import com.task.telna.entity.Usage;
import com.task.telna.exception.UserNotFoundException;
import com.task.telna.repository.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UsageService {

    @Autowired
    UsageRepository usageRepository;

    @Autowired
    UserService userService;

    public Usage saveUsage(Usage usage) throws UserNotFoundException {
        usage.setUser(userService.findByUserId(usage.getUser().getUserId()));
        return usageRepository.save(usage);
    }

    public List<Usage> getAllUsageForAUser(Usage usage) throws ParseException, UserNotFoundException {
        usage.setUser(userService.findByUserId(usage.getUser().getUserId()));
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY_MM_DD);
        return usageRepository.findByUsageTypeAndUserAndStartDateBetween(usage.getUsageType(), usage.getUser(), usage.getStartDate(), sdf.parse(sdf.format(new Date())));
    }
}

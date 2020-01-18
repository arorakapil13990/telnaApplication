package com.task.telna.service;

import com.task.telna.constants.Constants;
import com.task.telna.entity.User;
import com.task.telna.exception.EmailIdAlreadyExistException;
import com.task.telna.exception.UserNotFoundException;
import com.task.telna.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public long saveUser(User user) throws EmailIdAlreadyExistException {
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new EmailIdAlreadyExistException();
    }
    return userRepository.save(user).getUserId();
  }

  public User findByUserId(Long id) throws UserNotFoundException {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }
}

package com.example.demo.controller;

import com.example.demo.converter.UserDtoConverter;
import com.example.demo.dal.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoConverter converter;


    @GetMapping("/get")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream().map(u -> converter.convert(u)).toList();
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable("id") Integer userId) throws BadRequestException {
        final Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            return converter.convert(userOptional.get());
        } else {
            throw new BadRequestException("User with given id does not exist");
        }
    }

    @PostMapping("/post")
    public UserDto changeUser(@RequestBody UserDto userDto) throws BadRequestException {
        final Optional<User> updatedUSer = userService.updateUser(converter.convertBack(userDto));
        if (updatedUSer.isEmpty()) {
            throw new BadRequestException("User with given id does not exist");
        }
        return converter.convert(updatedUSer.get());
    }

    @PutMapping("/put")
    public UserDto createUser(@RequestBody UserDto userDto) throws BadRequestException {
        final Optional<User> createdUser = userService.createUser(converter.convertBack(userDto));
        if (createdUser.isEmpty()) {
            throw new BadRequestException("User with given id does not exist");
        }
        return converter.convert(createdUser.get());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") Integer userId) throws BadRequestException {
        final Optional<User> deletedUser = userService.deleteUserById(userId);
        if (deletedUser.isEmpty()) {
            throw new BadRequestException("User with given id does not exist");
        }
    }
}

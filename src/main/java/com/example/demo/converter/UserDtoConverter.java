package com.example.demo.converter;

import com.example.demo.dal.model.User;
import com.example.demo.model.UserDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
public class UserDtoConverter {

    public UserDto convert(User model) {
        final UserDto result = new UserDto();
        result.setId(model.getId());
        result.setUserName(model.getUserName());
        return result;
    }

    public User convertBack(UserDto dto){
        final User result = new User();
        result.setId(dto.getId());
        result.setUserName(dto.getUserName());
        return result;
    }
}

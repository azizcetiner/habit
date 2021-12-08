package com.acet.habit.controller;

import com.acet.habit.model.request.UserDetailsRequestModel;
import com.acet.habit.model.response.OperationStatusModel;
import com.acet.habit.model.response.ResponseOperationName;
import com.acet.habit.model.response.ResponseOperationStatus;
import com.acet.habit.model.response.UserRest;
import com.acet.habit.service.UserService;
import com.acet.habit.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {
        UserDto foundUser = userService.getUserById(id);
        return modelMapper.map(foundUser, UserRest.class);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(@RequestParam(value="page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<UserRest> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page, limit);

        for(UserDto userDto : users){
            returnValue.add(modelMapper.map(userDto, UserRest.class));
        }

        return returnValue;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        return modelMapper.map(createdUser, UserRest.class);
    }

    @PutMapping("/{userId}")
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String userId) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return modelMapper.map(updatedUser, UserRest.class);
    }

    @DeleteMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnvalue = new OperationStatusModel();
        returnvalue.setOperationName(ResponseOperationName.DELETE.name());
        userService.deleteUser(id);
        returnvalue.setOperationResult(ResponseOperationStatus.SUCCESS.name());

        return returnvalue;
    }

}

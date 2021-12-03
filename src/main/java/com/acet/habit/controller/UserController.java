package com.acet.habit.controller;


import com.acet.habit.model.request.UserDetailsRequestModel;
import com.acet.habit.model.response.OperationStatusModel;
import com.acet.habit.model.response.ResponseOperationName;
import com.acet.habit.model.response.ResponseOperationStatus;
import com.acet.habit.model.response.UserRest;
import com.acet.habit.service.UserService;
import com.acet.habit.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {

        UserRest returnValue = new UserRest();
        UserDto foundUser = userService.getUserById(id);
        BeanUtils.copyProperties(foundUser, returnValue);
        return returnValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<UserRest> getUsers(@RequestParam(value="page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<UserRest> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page, limit);

        for(UserDto userDto : users){
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }

        return returnValue;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping("/{userId}")
    public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String userId) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updatedUser = userService.updateUser(userDto, userId);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
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

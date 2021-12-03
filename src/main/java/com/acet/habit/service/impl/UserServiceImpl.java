package com.acet.habit.service.impl;

import com.acet.habit.entity.UserEntity;
import com.acet.habit.exceptions.UserServiceException;
import com.acet.habit.model.response.ErrorMessages;
import com.acet.habit.repository.UserRepository;
import com.acet.habit.service.UserService;
import com.acet.habit.shared.Utils;
import com.acet.habit.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException();

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setUserId(utils.generateId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto updateUser(UserDto user, String userId ) {

        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findUserByUserId(userId);

        // user not found exception
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        if(user.getEmail() != null) userEntity.setEmail(user.getEmail());
        if(user.getUsername() != null) userEntity.setUsername(user.getUsername());
        if(user.getTasks() != null) userEntity.setTasks(user.getTasks());

        UserEntity updatedUserDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserDetails, returnValue);

        return returnValue;

    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findUserByUserId(userId);
        if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity);
    }

    @Override
    public UserDto getUserById(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity foundUser = userRepository.findUserByUserId(userId);
        // exception
        if (foundUser == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        BeanUtils.copyProperties(foundUser, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);



        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        if(page>0) page= page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for(UserEntity userEntity : users ) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayDeque<>());
    }
}

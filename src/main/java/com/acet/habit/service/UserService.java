package com.acet.habit.service;


import com.acet.habit.entity.UserEntity;
import com.acet.habit.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

   UserDto createUser(UserDto user);
   UserDto updateUser(UserDto user, String userId);
   void deleteUser(String userId);
   UserDto getUserById(String id);
   UserDto getUser(String email);
   List<UserDto> getUsers(int page, int limit);
}

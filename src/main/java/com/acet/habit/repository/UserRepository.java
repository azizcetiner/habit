package com.acet.habit.repository;

import com.acet.habit.entity.UserEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByEmail(String email);
    UserEntity findUserByUserId(String userId);
}

package com.yeshp.cacheapi.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeshp.cacheapi.cache.ICacheManager;
import com.yeshp.cacheapi.pojos.User;
import com.yeshp.cacheapi.repositories.UserRepository;
import com.yeshp.cacheapi.services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ICacheManager cacheManager;

    public User createUser(User user) throws Exception {
        User savedUser = this.userRepository.save(user);
        this.cacheManager.add(savedUser.getUserId(), new ObjectMapper().writeValueAsString(savedUser));
        log.info("User Created : {}", savedUser);
        return savedUser;
    }

    public Map<String, Object> getUsers() throws Exception {
        return this.cacheManager.getAll();
    }

    public User getUser(String userId) throws Exception {
        User user = null;
        if(this.cacheManager.get(userId) != null) {
            user = new ObjectMapper().readValue(this.cacheManager.get(userId), User.class);
            log.info("User from cache : {}", user);
        }
        else {
            user = this.userRepository.findUserByUserId(userId);
            this.cacheManager.add(user.getUserId(), new ObjectMapper().writeValueAsString(user));
            log.info("User added to cache from DB : {}", user);
        }
        return user;
    }
}

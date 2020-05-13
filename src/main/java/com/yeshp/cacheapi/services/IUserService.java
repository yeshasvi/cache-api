package com.yeshp.cacheapi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yeshp.cacheapi.pojos.User;

import java.util.Map;

public interface IUserService {
    public User createUser(User user) throws Exception;
    public User getUser(String userId) throws JsonProcessingException, Exception;
    public Map<String, Object> getUsers() throws JsonProcessingException, Exception;
}

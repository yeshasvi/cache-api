package com.yeshp.cacheapi.services;

import com.yeshp.cacheapi.pojos.Cache;

import java.util.Map;

public interface ICacheService {
    public Cache add(Map<String, Object> payload) throws Exception;
    Map<String, Object> getAll() throws Exception;

    Object get(String id) throws Exception;
}

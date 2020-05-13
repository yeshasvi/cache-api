package com.yeshp.cacheapi.cache;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface ICacheManager {
    public void add(String key, String value);
    public String get(String key);
    public Map<String, Object> getAll() throws JsonProcessingException, Exception;
}

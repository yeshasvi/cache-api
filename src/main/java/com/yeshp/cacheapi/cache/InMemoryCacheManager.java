package com.yeshp.cacheapi.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeshp.cacheapi.pojos.Cache;
import com.yeshp.cacheapi.pojos.User;
import com.yeshp.cacheapi.repositories.CacheRepository;
import com.yeshp.cacheapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryCacheManager implements ICacheManager {

    @Autowired
    private CacheRepository cacheRepository;
    @Autowired
    private UserRepository userRepository;

    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public void add(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public Map<String, Object> getAll() throws Exception {

        buildCache();
        Map<String, Object> allCache = new HashMap<>();
        for(String id : cache.keySet()) {
            allCache.put(id, new ObjectMapper().readValue(cache.get(id), Object.class));
        }
        return allCache;
    }

    @PostConstruct
    private void buildCache() throws Exception {
        List<Cache> payloads = cacheRepository.findAll();
        if (!payloads.isEmpty()) {
            for (Cache cache : payloads) {
                this.cache.put(cache.getPayloadId(), cache.getPayload());
            }
        }
        buildUserCache();
    }

    private void buildUserCache() throws Exception {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            for (User user : users) {
                this.cache.put(user.getUserId(), new ObjectMapper().writeValueAsString(user));
            }
        }
    }
}

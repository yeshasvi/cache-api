package com.yeshp.cacheapi.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeshp.cacheapi.cache.ICacheManager;
import com.yeshp.cacheapi.pojos.Cache;
import com.yeshp.cacheapi.repositories.CacheRepository;
import com.yeshp.cacheapi.services.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class CacheServiceImpl implements ICacheService {
    @Autowired
    private CacheRepository cacheRepository;
    @Autowired
    private ICacheManager cacheManager;

    @Override
    public Cache add(Map<String, Object> payload) throws Exception {
        Cache cache = new Cache();
        cache.setPayload(new ObjectMapper().writeValueAsString(payload));
        Cache newCache = cacheRepository.save(cache);
        this.cacheManager.add(cache.getPayloadId(), new ObjectMapper().writeValueAsString(payload));
        log.info("Payload added to DB and Cache : {}",payload);
        return cache;
    }

    @Override
    public Map<String, Object> getAll() throws Exception {
        return this.cacheManager.getAll();
    }

    @Override
    public Object get(String id) throws Exception {
        if(this.cacheManager.get(id) != null) {
            log.info("Payload from Cache : {}", this.cacheManager.get(id));
            return new ObjectMapper().readValue(this.cacheManager.get(id), Object.class);
        } else {
            Cache cache = cacheRepository.findUserByPayloadId(id);
            this.cacheManager.add(cache.getPayloadId(), new ObjectMapper().writeValueAsString(cache.getPayload()));
            log.info("Payload added to Cache from DB : {}", cache.getPayload());
            return new ObjectMapper().readValue(this.cacheManager.get(id), Object.class);
        }
    }
}

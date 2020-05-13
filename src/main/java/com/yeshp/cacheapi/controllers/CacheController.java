package com.yeshp.cacheapi.controllers;

import com.yeshp.cacheapi.pojos.Cache;
import com.yeshp.cacheapi.services.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
public class CacheController {

    @Autowired
    ICacheService cacheService;

    @PostMapping("/cache-sync")
    public Cache addToCache(@RequestBody Map<String, Object> payload)
            throws Exception {
        return cacheService.add(payload);
    }

    @GetMapping("/cache-sync")
    public Map<String, Object> getCache() throws Exception {
        return cacheService.getAll();
    }

    @GetMapping("/cache-sync/{id}")
    public Object getPayload(@PathVariable String id ) throws Exception {
        return cacheService.get(id);
    }
}

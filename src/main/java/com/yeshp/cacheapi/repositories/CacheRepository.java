package com.yeshp.cacheapi.repositories;

import com.yeshp.cacheapi.pojos.Cache;
import com.yeshp.cacheapi.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CacheRepository extends JpaRepository<Cache, Long> {
    @Query("SELECT c FROM Cache c WHERE c.payloadId = :payloadId")
    Cache findUserByPayloadId(@Param("payloadId") String payloadId);
}

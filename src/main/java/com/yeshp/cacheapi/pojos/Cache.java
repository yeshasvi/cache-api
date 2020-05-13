package com.yeshp.cacheapi.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Cache {
    @Id
    @GeneratedValue
    private Long id;
    private String payloadId = UUID.randomUUID().toString();
    @NotEmpty
    private String payload;

}

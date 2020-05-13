package com.yeshp.cacheapi.pojos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userId = UUID.randomUUID().toString();
    @NotEmpty
    private String username;
    @NotEmpty
    private String dept;

    public User(String username, String dept) {
        this.username = username;
        this.dept = dept;
    }
}

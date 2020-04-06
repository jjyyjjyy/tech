package com.example.domain;

import lombok.Data;

import java.time.Instant;

/**
 * @author jy
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String companyName;

    private Integer status;

    private Integer role;

    private Instant createdAt;

    private Instant updatedAt;
}

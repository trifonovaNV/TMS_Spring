package com.tms.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date created;
    private Date updated;
    private boolean active;


}

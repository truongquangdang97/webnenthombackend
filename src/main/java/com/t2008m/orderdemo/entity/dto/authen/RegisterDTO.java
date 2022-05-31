package com.t2008m.orderdemo.entity.dto.authen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
}
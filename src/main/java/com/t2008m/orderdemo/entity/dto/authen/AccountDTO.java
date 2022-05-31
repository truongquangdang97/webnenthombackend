package com.t2008m.orderdemo.entity.dto.authen;

import com.t2008m.orderdemo.entity.authen.Account;
import lombok.Data;

import java.util.Date;
@Data
public class AccountDTO {
    private long id;
    private String username;
    private String role;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.role = account.getRole().getName();
        this.status = account.getStatus();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }


}

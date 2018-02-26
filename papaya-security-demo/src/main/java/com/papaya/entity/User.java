package com.papaya.entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;

public class User {

    public interface userSimpleView{}

    public interface userDetailView extends  userSimpleView{}

    private String id;

    private String username;

    private String password;

    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(userSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(userDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

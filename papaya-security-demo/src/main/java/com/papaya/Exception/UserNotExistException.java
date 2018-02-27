package com.papaya.Exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String id){
        super("user not exists");
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

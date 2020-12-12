package com.example.plot.management;

import com.example.plot.jpa.User;

import java.io.Serializable;

public class LoginRegister extends User implements Serializable{

    private String rePassword;

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}

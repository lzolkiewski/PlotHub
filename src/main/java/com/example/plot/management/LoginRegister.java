package com.example.plot.management;

import java.io.Serializable;

import com.example.plot.database.User;

public class LoginRegister extends User implements Serializable{

    private String rePassword;
    private String reEmail;


    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getReEmail() {
        return reEmail;
    }

    public void setReEmail(String reEmail) {
        this.reEmail = reEmail;
    }
}

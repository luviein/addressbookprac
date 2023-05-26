package com.example.addressbook.model;

import java.io.Serializable;
import java.util.Random;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Info implements Serializable {
    @NotNull(message="Name cannot be empty")
    @Size(min=5, max=15, message="Name should be between 5-15 characters")
    private String userName;

    @NotNull(message="Password cannot be empty")
    @Size(min=8, max= 20, message ="Password should be between 8-20 characters")
    private String password;

    @Email(message="Please enter valid email")
    @NotNull(message="Email cannot be empty")
    private String email;

    private String ID;


    public Info() {
        this.ID = generateID();
    }

    public Info(String userName, String password, String email, String ID) {
       
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.ID = ID;
    }

    private String generateID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < 11) {
            sb.append(Math.abs(random.nextInt() % 10)); //to give 0 - 9 remainder, use absolute to change negative to positive number
        }
        return sb.toString().substring(0, 10);

    }

    // 4 / 2 = 2. 0
    // 5 / 2 = 2. 1

    // 6 / 3 = 2, 0
    // 7 / 3 = 2, 1
    // 8 / 3 = 2, 2
    // 9 / 3 = 3, 0

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

 

    @Override
    public String toString() {
        return "Info [userName=" + userName + ", password=" + password + ", email=" + email + ", ID=" + ID
                + "]";
    }




    
}

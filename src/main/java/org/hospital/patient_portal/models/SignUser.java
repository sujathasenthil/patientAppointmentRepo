package org.hospital.patient_portal.models;

import javax.validation.constraints.NotBlank;

public class SignUser {
    @NotBlank(message="please enter ur name")
    private String name;

    @NotBlank(message="Please enter the password")
    private String password;

    public SignUser() {

    }

    public SignUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void checkValidation(){

    }
}

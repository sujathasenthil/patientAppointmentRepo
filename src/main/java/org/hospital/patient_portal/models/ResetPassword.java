package org.hospital.patient_portal.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ResetPassword {
//    @Email
    private String name;

    @NotEmpty
    @Size(min=5,max=10)
    private String password;

    @NotNull(message = "Passwords do not match")
    private String confirm;

    @NotEmpty
    private String sec_question;

    public ResetPassword() {
    }

    public ResetPassword(String name, String password, String confirm,
                   String sec_question) {
        this.name = name;
        this.password = password;
        this.confirm = confirm;
        this.sec_question = sec_question;
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
        checkPassword();
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
        checkPassword();
    }

    public String getSec_question() {
        return sec_question;
    }

    public void setSec_question(String sec_question) {
        this.sec_question = sec_question;
    }

    private void checkPassword(){
        if (confirm != null && password != null && !password.equals( confirm)){
            confirm = null;
        }
    }
}

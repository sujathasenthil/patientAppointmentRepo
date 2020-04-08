package org.hospital.patient_portal.models;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.type.EnumType;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Patient{

//extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;

    @NotBlank(message="Please provide your name")
    @Size(min = 3, max=50, message="Name must be between 3 and 50 characters long")
    private String name;

    private String lastName;

    @NotNull
    @Size(min=5,max=10)
    private String password;

    @NotNull(message = "Passwords do not match")
    @Transient
    private String confirm;

//    @Enumerated(value= EnumType<Gender>)
//    private String gender;

    private Gender gender;

    private String maritalStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;

//    @NotNull
//    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}",message="phone number must be (123)456-7890 format")
    private String phone;

    @NotBlank(message="Email is required")
    @Email(message = "Invalid Email. Try Again!!")
    private String email;

    private String sec_question;

    @javax.validation.constraints.NotNull
    @NotBlank(message="Address should not be empty or null!")
    @Size(min = 2, max= 100)
    private String address;

    @NotNull
    @NotBlank(message="City should not be empty or null!")
    @Size(min = 2, max= 30)
    private String city;

//    @NumberFormat(style = NumberFormat.Style.NUMBER)
//    @Min(10000)
//    @Max(99999)
    private int zipCode;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="patient_id")
//    private final List<ScheduleAppt> scheduleAppts =new ArrayList<>();
    @OneToMany(mappedBy = "patients")
    private List<ScheduleAppt> scheduleAppts;

//    @OneToMany(targetEntity = ScheduleAppt.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
//    private Set<ScheduleAppt> scheduleAppts;
//
    public Patient() {
    }

    public Patient(String name,String lastName, String password, String confirm, Gender gender, String maritalStatus, LocalDate dateOfBirth, String phone, String email, String sec_question, String address, String city, int zipCode,List<ScheduleAppt> scheduleAppts) {
        this.name = name;
        this.lastName=lastName;
        this.password = password;
        this.confirm = confirm;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.sec_question = sec_question;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
       this.scheduleAppts = scheduleAppts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSec_question() {
        return sec_question;
    }

    public void setSec_question(String sec_question) {
        this.sec_question = sec_question;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
//
//    public List<ScheduleAppt> getScheduleAppts() {
//        return scheduleAppts;
//    }

    public List<ScheduleAppt> getScheduleAppts() {
        return scheduleAppts;
    }

    public void setScheduleAppts(List<ScheduleAppt> scheduleAppts) {
        this.scheduleAppts = scheduleAppts;
    }

    private void checkPassword(){
        if (confirm != null && password != null && !password.equals( confirm)){
            confirm = null;
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

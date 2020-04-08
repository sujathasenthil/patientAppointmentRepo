//package org.hospital.patient_portal.models;
//
//import org.hibernate.annotations.Type;
//import org.hibernate.validator.constraints.Range;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.NumberFormat;
//
//import javax.persistence.Entity;
//import javax.validation.constraints.*;
//import java.time.LocalDate;
//import java.util.Date;
//
//@Entity
//public class PatientDetails extends AbstractEntity {
//
////    @Range(min = 1)
//    private int age;
//
//    @NotBlank(message="Gender is required")
//    @NotNull
//    private String gender;
//
////    @PastOrPresent(message="enter valid date")
//  //
////  @DateTimeFormat(pattern = "dd/mm/yyyy")
////@DateTimeFormat(pattern = "dd/mm/yyyy")
////@Temporal(TemporalType.DATE)
// // @Temporal(TemporalType.DATE)
//    //@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
////
//       private String maritalStatus;
//
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    private LocalDate dateOfBirth;
//
//    @Range(min=1111111111)
//    private long phoneNumber;
//
//    @NotBlank(message="Email is required")
//    @Email(message = "Invalid Email. Try Again!!")
//    private String contactEmail;
//
//    @NotNull
//    @NotBlank(message="Address should not be empty or null!")
//    @Size(min = 2, max= 100)
//    private String address;
//
//    @NotNull
//    @NotBlank(message="City should not be empty or null!")
//    @Size(min = 2, max= 30)
//    private String city;
//
//    @NumberFormat(style = NumberFormat.Style.NUMBER)
//    @Min(10000)
//    @Max(99999)
//    private int zipCode;
//
//    public PatientDetails() {
//    }
//
//    public PatientDetails(int age, String gender, LocalDate dateOfBirth, String maritalStatus, long phoneNumber, String contactEmail, String address, String city, int zipCode) {
//        this.age = age;
//        this.gender = gender;
//        this.dateOfBirth = dateOfBirth;
//        this.maritalStatus = maritalStatus;
//        this.phoneNumber = phoneNumber;
//        this.contactEmail = contactEmail;
//        this.address = address;
//        this.city = city;
//        this.zipCode = zipCode;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public LocalDate getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(LocalDate dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getMaritalStatus() {
//        return maritalStatus;
//    }
//
//    public void setMaritalStatus(String maritalStatus) {
//        this.maritalStatus = maritalStatus;
//    }
//
//    public long getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(long phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getContactEmail() {
//        return contactEmail;
//    }
//
//    public void setContactEmail(String contactEmail) {
//        this.contactEmail = contactEmail;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public int getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(int zipCode) {
//        this.zipCode = zipCode;
//    }
//
//}

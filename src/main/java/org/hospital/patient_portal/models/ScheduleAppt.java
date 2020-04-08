package org.hospital.patient_portal.models;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ScheduleAppt{

// extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private int id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate apptDate;

    @Basic
    private String apptTime;

    @ManyToOne(targetEntity = Patient.class)
    private Patient patients;

    //private final List<Patient> patients = new ArrayList<>();

    public ScheduleAppt() {
    }

    public ScheduleAppt(LocalDate apptDate, String apptTime, Patient patient) {
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.patients=patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatients() {
        return patients;
    }

    public void setPatients(Patient patients) {
        this.patients = patients;
    }

    public LocalDate getApptDate() {
        return apptDate;
    }

    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }

}

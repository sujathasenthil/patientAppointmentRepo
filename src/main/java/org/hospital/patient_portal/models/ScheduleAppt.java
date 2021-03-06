package org.hospital.patient_portal.models;

import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "schedule_appt",schema="hospital_portal")
public class ScheduleAppt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    //@NotNull
    @Future(message = "Enter future date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="appt_date")
    private LocalDate apptDate;

    @Basic
    @Column(name = "appt_time")
    private String apptTime;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patients_id")
    private Patient patients;

    public ScheduleAppt() {
    }

    public ScheduleAppt(LocalDate apptDate, String apptTime, Patient patient) {
        this.apptDate = apptDate;
        this.apptTime = apptTime;
        this.patients = patient;
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

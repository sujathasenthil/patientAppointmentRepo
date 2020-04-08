package org.hospital.patient_portal.models;

import java.util.ArrayList;

public class PatientData {
    static ArrayList<Patient> patients= new ArrayList<>();
    public static ArrayList<Patient> getAll(){
        return patients;
    }
    public static void create(Patient newPatient) {
        patients.add(newPatient);
    }
    public static Patient getById(int id) {

        Patient thePatient = null;

        for (Patient patient : patients) {
            if (patient.getId() == id) {
                thePatient = patient;
            }
        }

        return thePatient;
    }
}

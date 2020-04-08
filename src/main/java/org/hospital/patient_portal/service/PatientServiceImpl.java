//package org.hospital.patient_portal.service;
//
//import org.hospital.patient_portal.data.PatientRepository;
//import org.hospital.patient_portal.models.Patient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PatientServiceImpl {
//    private PatientRepository patientRepository;
//
//    @Autowired
//    public PatientServiceImpl(PatientRepository patientRepository){
//        this.patientRepository=patientRepository;
//    }
//
//    public Patient findByName(String name) {
//        return patientRepository.findByName(name);
//    }
//
//    public Patient findByPhone(String phone) {
//        return patientRepository.findByPhone(phone);
//    }
//
//    public Patient findByEmail(String email) {
//        return patientRepository.findByEmail(email);
//    }
//
//    public void save(Patient patient) {
//        patientRepository.save(patient);
//    }
//
//    public Patient getById(Integer id){
//        return patientRepository.getById(id);
//    }
//}

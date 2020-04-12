package org.hospital.patient_portal.controllers;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.hospital.patient_portal.data.AppointmentRepository;
//import org.hospital.patient_portal.data.LogRepository;
import org.hospital.patient_portal.data.PatientRepository;
import org.hospital.patient_portal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @RequestMapping(value = "")
    public String displayAllPatients(Model model) {
        model.addAttribute("patients", PatientData.getAll());
        return "patients/index";
    }

    @RequestMapping(value = "signUp", method = RequestMethod.GET)
    public String displayCreatePatientForm(Model model) {
        model.addAttribute("title", "Create Patient");
        model.addAttribute(new Patient());
        model.addAttribute("genders", Gender.values());
        return "patients/signUp";
    }

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public String processCreatePatientForm(@ModelAttribute @Valid Patient newPatient, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign Up");
            return "patients/signUp";
        }
        Patient existingPatient = patientRepository.findByName(newPatient.getName());
        if (existingPatient != null) {
            return "redirect:/patients/signup";
        }
        patientRepository.save(newPatient);
        session.setAttribute("username", newPatient.getName());
        session.setAttribute("sUserId", newPatient.getId());
        model.addAttribute("patients",existingPatient);
        return "patients/index";
    }

    @RequestMapping(value = "makeAppt", method = RequestMethod.GET)
    public String displayPatientApptForm(Model model, HttpSession session) {
        model.addAttribute("title", "Schedule Appointment");
        model.addAttribute(new ScheduleAppt());
        return "patients/makeAppt";
    }
    @RequestMapping(value = "makeAppt", method = RequestMethod.POST)
    public String processPatientApptForm(@RequestParam("pname") String name,@RequestParam(value="apptDate",required = false) String apptDate, ScheduleAppt newScheduleAppt, BindingResult bindingResult, Errors errors, Model model, HttpSession session ) throws Exception {
        System.out.println("apptDatetime" + newScheduleAppt.getApptDate());
        System.out.println("REquestparam" + apptDate);
        System.out.println("offdate" + LocalDateTime.parse(apptDate.replace(" ", "T")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String replacedT = apptDate.replace(" ", "T");
        LocalDateTime dateTime = LocalDateTime.parse(replacedT, formatter);

        if (bindingResult.hasErrors()) {
            return "patients/makeAppt";
        } else {
            //if (!((appointmentRepository.HasDate(apptDate)) &&(appointmentRepository.HasTime(apptTime))))  {
            //
            if (!(appointmentRepository.findAll().contains(dateTime))) {
                Patient newPatient = patientRepository.findByName(name);
                model.addAttribute("patients", newPatient);
                // newScheduleAppt.setApptTime(newScheduleAppt.getApptTime());
                newScheduleAppt.setApptDate(dateTime);
                newScheduleAppt.setPatients(newPatient);
                appointmentRepository.save(newScheduleAppt);
                return "patients/index";
            } else {
                model.addAttribute("errorMsg", "Try scheduling different timing, chosen time slot is booked already! ");
                return "patients/makeAppt";
            }
        }
    }
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(Model model) {
        model.addAttribute("title","Delete Appointment");
        model.addAttribute("patients", PatientData.getAll());
        List<ScheduleAppt> allAppts=appointmentRepository.findAll();

        model.addAttribute("allAppts",allAppts);
        return "patients/delete";
    }

        @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    public String deleteUser(@RequestParam(required = false) int[] apptIds, Model model) {
//       //     model.addAttribute("patients", PatientData.getAll());
//            model.addAttribute("patients", PatientData.getAll());
//            List<ScheduleAppt> allAppts=appointmentRepository.findAll();
            if (apptIds!= null){
                for(int id : apptIds){
                    appointmentRepository.deleteById(id);
                }}
        //    return("redirect:");
        return "patient/index";

    }
//    @GetMapping("delete")
//    public String displayDeletePatient(Model model){
//        model.addAttribute("title","Delete Patient");
//        model.addAttribute("patients",patientRepository.findAll());
//        return "patients/delete";
//    }
//
//    @PostMapping("delete")
//    public String displayprocessDeletePatientForm(@RequestParam(required = false) int[] patientIds){
//        if (patientIds!= null){
//            for(int id : patientIds){
//                patientRepository.deleteById(id);
//            }}
//        return("redirect:");
//    }
//


    @RequestMapping(value = "recovery", method = RequestMethod.GET)
    public String displayRecoverPassword(Model model) {
        model.addAttribute("title", "Reset Password");
        model.addAttribute(new ResetPassword());
        return "patients/recovery";
    }

    @RequestMapping(value = "recovery", method = RequestMethod.POST)
    public String processRecoverPassword(@ModelAttribute @Valid ResetPassword resetPw, Errors errors,
                                         @RequestParam String password,
                                         @RequestParam String confirm,
                                         Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Reset Password");
            return "patients/recovery";
        }
        Patient existUser;
        existUser = patientRepository.findByName(resetPw.getName());
        if (existUser == null) {
            return "redirect:/patients/recovery?q=Invalid+Email";
        }
        if (existUser.getSec_question().equals(resetPw.getSec_question())) {
            existUser.setPassword(password);
            existUser.setConfirm(confirm);
            patientRepository.save(existUser);
            session.setAttribute("username", existUser.getName());
            session.setAttribute("sUserId", existUser.getId());
            return "patients/index";
        } else {
            return "redirect:/patients/recovery?Q=Invalid+Security+answer";
        }
    }

    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String processLogoutForm(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("sUserId");
        return "patients/signout";
    }
}

//    @GetMapping("detail")
//    public String displayPatientDetails(@RequestParam Integer patientId, Model model) {
//
//        Optional<Patient> result = patientRepository.findById(patientId);
//
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Patient ID: " + patientId);
//        } else {
//            Patient patient = result.get();
//            model.addAttribute("title", patient.getName() + " Details");
//            model.addAttribute("patient", patient);
////            model.addAttribute("tags",event.getTags());
//        }
//
//        return "patients/detail";
//    }




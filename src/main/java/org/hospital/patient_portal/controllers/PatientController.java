package org.hospital.patient_portal.controllers;

import org.hospital.patient_portal.data.AppointmentRepository;
import org.hospital.patient_portal.data.PatientRepository;
import org.hospital.patient_portal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.*;

@Controller
@SessionAttributes("sUserId")
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
        return "patients/index";
    }

    @RequestMapping(value = "makeAppt", method = RequestMethod.GET)
    public String displayPatientApptForm(Model model, HttpSession session) {
        model.addAttribute("title", "Schedule Appointment");
        model.addAttribute(new ScheduleAppt());
        return "patients/makeAppt";
    }
//Date&Time
    @RequestMapping(value = "makeAppt", method = RequestMethod.POST)
    public String processPatientApptForm(@RequestParam("pname") String name, ScheduleAppt newScheduleAppt, BindingResult bindingResult, Errors errors, Model model, HttpSession session ) throws Exception {
        if (errors.hasErrors()) {
            return "patients/makeAppt";
        } else {
            if(newScheduleAppt.getApptDate().isBefore(LocalDate.now()))
            {
                model.addAttribute("errorMsg","Enter Future Date");
                return "patients/makeAppt";
            }
            if(appointmentRepository.checkIfApptExist(newScheduleAppt.getApptDate(),newScheduleAppt.getApptTime() ) >0 )
            {
                System.out.println("Dont proceed");
                System.out.println("Appointment slot not available, choose different timing");
                model.addAttribute("errorMsg", "Appointment slot not available, choose different timing");
                return "patients/makeAppt";
            }
            else{
                Patient newPatient = patientRepository.findByName(name);
                model.addAttribute("patients", newPatient);
                newScheduleAppt.setApptDate(newScheduleAppt.getApptDate());
                newScheduleAppt.setApptTime(newScheduleAppt.getApptTime());
                newScheduleAppt.setPatients(newPatient);
                appointmentRepository.save(newScheduleAppt);
                return "patients/index";
            }
        }
    }

    @RequestMapping(value = "delete/{apptId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable int apptId, Model model) {
          //ScheduleAppt scheduleAppt=appointmentRepository.findById(apptId).get();
        ScheduleAppt scheduleAppt=appointmentRepository.findById(apptId).get();
        int patient_id=(Integer) model.getAttribute("sUserId");
          appointmentRepository.delete(scheduleAppt);
          model.addAttribute("appts",appointmentRepository.findRecByPatientId(patient_id));
          return "patients/view";

    }

    @RequestMapping(value="view",method = RequestMethod.GET)
    public String processEditForm(Model model){
       // Iterable<ScheduleAppt> appts;
        int patient_id=(Integer) model.getAttribute("sUserId");
        System.out.println("patId"+patient_id);
        model.addAttribute("appts", appointmentRepository.findRecByPatientId(patient_id));
        return "patients/view";
    }

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

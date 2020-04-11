package org.hospital.patient_portal.controllers;

import org.hospital.patient_portal.data.AppointmentRepository;
import org.hospital.patient_portal.data.PatientRepository;
import org.hospital.patient_portal.models.Patient;
import org.hospital.patient_portal.models.SignUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private PatientRepository patientRepository;


    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String home() {
        return "index";
    }

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

    @RequestMapping(value = "contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Sign in form");
        model.addAttribute("signUser", new SignUser());
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid SignUser signUser, Errors errors, Model model, HttpSession session) {

        if (errors.hasErrors()) {
            return "login";
        }
        Patient searchPatient;
        searchPatient = patientRepository.findByName(signUser.getName());

        if (searchPatient != null && signUser.getPassword().equals(searchPatient.getPassword())) {
            session.setAttribute("username", searchPatient.getName());
            session.setAttribute("sUserId", searchPatient.getId());
            model.addAttribute("patients", searchPatient);
            return "patients/index";
        }
        model.addAttribute("message", "Invalid Credentials");
        return "login";
    }
}

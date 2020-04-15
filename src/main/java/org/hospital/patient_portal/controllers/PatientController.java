package org.hospital.patient_portal.controllers;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.hospital.patient_portal.data.AppointmentRepository;
//import org.hospital.patient_portal.data.LogRepository;
import org.hospital.patient_portal.data.PatientRepository;
import org.hospital.patient_portal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;
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

import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

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
        model.addAttribute("patId",newPatient.getId());
        model.addAttribute("patients",existingPatient);
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
               // System.out.println("past date");
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
    public String deleteUser(@PathVariable int sUserId, @PathVariable int apptId, Model model) {
          ScheduleAppt scheduleAppt=appointmentRepository.findById(apptId).get();
          appointmentRepository.delete(scheduleAppt);
          model.addAttribute("appts",appointmentRepository.findAll());
          return "patients/view";

    }
    @RequestMapping(value="view",method = RequestMethod.GET)
    public String processEditForm(Model model, @RequestParam("patId") int patientId, HttpServletRequest req){
        System.out.println("pid"+patientId);
        //if (appointmentRepository apptId == sUserId){
       //int patId=req.getAttribute(sUserId);
 //       model.addAttribute("patId",sUserId);
        //if(appointmentRepository.findById(req.getAttribute(sUserId))!= null) {
            model.addAttribute("appts", appointmentRepository.findRecByPatientId(patientId));
            return "patients/view";
       // }
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
//        @RequestMapping(value = "makeAppt", method = RequestMethod.POST)
//    public String processPatientApptForm(@RequestParam("pname") String name,@RequestParam(value="apptDate",required = false) String apptDate, ScheduleAppt newScheduleAppt, BindingResult bindingResult, Errors errors, Model model, HttpSession session ) throws Exception {
//        System.out.println("apptDatetime" + newScheduleAppt.getApptDate());
//        System.out.println("REquestparam" + apptDate);
//        System.out.println("offdate" + LocalDateTime.parse(apptDate.replace("T ", " ")));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String replacedT = apptDate.replace("T", " ");
//        System.out.println( "replacedT"+replacedT);
//        LocalDateTime dateTime = LocalDateTime.parse(replacedT, formatter);
//        System.out.println("dateTime"+dateTime);
//        if (bindingResult.hasErrors()) {
//            return "patients/makeAppt";
//        } else {
//            //if (!((appointmentRepository.HasDate(apptDate)) &&(appointmentRepository.HasTime(apptTime))))  {
//            //
//         //   if (!(appointmentRepository.findAll().contains(dateTime))) {
//                Patient newPatient = patientRepository.findByName(name);
//                model.addAttribute("patients", newPatient);
//                // newScheduleAppt.setApptTime(newScheduleAppt.getApptTime());
//                newScheduleAppt.setApptDate(dateTime);
//                newScheduleAppt.setPatients(newPatient);
//                appointmentRepository.save(newScheduleAppt);
//                return "patients/index";
////            } else {
////                model.addAttribute("errorMsg", "Try scheduling different timing, chosen time slot is booked already! ");
////                return "patients/makeAppt";
////            }
//        }
//    }

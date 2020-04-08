package org.hospital.patient_portal.data;

import org.hospital.patient_portal.models.ScheduleAppt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<ScheduleAppt,Integer> {
    //public List<ScheduleAppt> findByDateAndTime(LocalDate Apptdate, String Appttime);
//    public Boolean HasDate(LocalDate Apptdate);
//    public Boolean HasTime(String Appttime);
    public List<ScheduleAppt> findAll();
   // public Optional<ScheduleAppt> findByName(String name);
}

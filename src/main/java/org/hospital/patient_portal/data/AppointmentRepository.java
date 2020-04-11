package org.hospital.patient_portal.data;

import org.hospital.patient_portal.models.ScheduleAppt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
   // @Query("SELECT p, c FROM Patient p join p.info c WHERE p.id =:id AND p.rev = c.rev")
 //   public List<ScheduleAppt> findById(Integer id);
    //  public List<ScheduleAppt> findByPatientId(Integer id);
   // public Optional<ScheduleAppt> findByName(String name);
}

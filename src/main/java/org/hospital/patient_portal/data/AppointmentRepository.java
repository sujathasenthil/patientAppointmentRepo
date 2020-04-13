package org.hospital.patient_portal.data;

import org.hospital.patient_portal.models.ScheduleAppt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.smartcardio.Card;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends CrudRepository<ScheduleAppt,Integer> {
    public List<ScheduleAppt> findAll();
   // @Query("select ScheduleAppt where ScheduleAppt.apptDate=checkDate")
    //public List<ScheduleAppt> findByDate(LocalDate apptDate);
    //public List<ScheduleAppt> findByTime(String apptTime);
    //  public List<ScheduleAppt> findByPatientId(Integer id);
   // public Optional<ScheduleAppt> findByName(String name);
}

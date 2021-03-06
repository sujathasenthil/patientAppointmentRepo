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
 //   public List<ScheduleAppt> findById(int patient_Id);
   @Query(value = "select count(*) from schedule_appt where appt_date=:apptDate && appt_time=:apptTime",nativeQuery = true)
    public int checkIfApptExist(LocalDate apptDate, String apptTime);

   @Query(value="select * from schedule_appt where patients_id= :patient_id", nativeQuery =true)
   public List<ScheduleAppt> findRecByPatientId(int patient_id);
   }

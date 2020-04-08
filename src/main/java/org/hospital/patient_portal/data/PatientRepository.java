package org.hospital.patient_portal.data;

import org.hospital.patient_portal.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.event.PaintEvent;

@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient,Integer> {
    Patient findByName(String name);
    Patient findByPhone(String phone);
    Patient findByEmail(String email);
    Patient getById(Integer id);
}

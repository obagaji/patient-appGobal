package com.appGobal.patient_appGobal.repo;


import com.appGobal.patient_appGobal.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PatientsRepo extends JpaRepository<Patients, Integer>
{
   //Optional< String> findByFirstName(String firstname);
   Optional<Patients>findByFirstName(String firstname);
}

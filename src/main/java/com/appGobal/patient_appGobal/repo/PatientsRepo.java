package com.appGobal.patient_appGobal.repo;


import com.appGobal.patient_appGobal.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepo extends JpaRepository<Patients, Integer>
{
}

package com.appGobal.patient_appGobal.repo;


import com.appGobal.patient_appGobal.entity.Patients;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface PatientsRepo extends CrudRepository<Patients, Integer>
{
}

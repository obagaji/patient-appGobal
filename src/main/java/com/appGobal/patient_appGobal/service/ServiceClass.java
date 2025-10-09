package com.appGobal.patient_appGobal.service;

import com.appGobal.patient_appGobal.entity.Patients;
import com.appGobal.patient_appGobal.repo.NextOfKinRepo;

import com.appGobal.patient_appGobal.repo.PatientsRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ServiceClass {

    @Autowired
    PatientsRepo patientsRepo;

    @Autowired
    NextOfKinRepo nextOfKinRepo;

    public List<Patients> AllgetPatients()
    {
        return (List<Patients>) patientsRepo.findAll();
    }
    public Patients getPatientById(Integer id)
    {
        return patientsRepo.findById(id).orElseThrow();
    }
    @Transactional
    public void delatePatientById(Integer id)
    {
        nextOfKinRepo.deleteById(id);
         patientsRepo.deleteById(id);
    }

    public Patients updateInfo(Patients patients)
    {
        Patients patients1 = new Patients();
       Optional< Patients> pa  = Optional.ofNullable(patientsRepo.findById(patients.getId()).orElseThrow());
       if(!pa.isEmpty())
       {
           patients1 = pa.get();

       }
       patients1.setDateOfBirth(patients.getDateOfBirth());
       patients1.setEmailAddress(patients.getEmailAddress());
        patients1.setGender(patients.getGender());
        patients1.setFirstName(patients.getFirstName());
        patients1.setHomeAddress(patients.getHomeAddress());
        patients1.setLastName(patients.getHomeAddress());
        patients1.setMiddleName(patients.getMiddleName());
        patients1.setPhoneNo(patients.getPhoneNo());
        patients1.setNextOfKin(patients.getNextOfKin());

        return patientsRepo.save(patients);

    }

}

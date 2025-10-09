package com.appGobal.patient_appGobal.controller;

import com.appGobal.patient_appGobal.entity.Patients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.appGobal.patient_appGobal.service.*;

@Controller
@RequestMapping("/api/v1")
public class AppController {


    @Autowired
    ServiceClass serviceClass;

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patients>getAllPatients(@PathVariable Integer id)
    {
        Patients patients = serviceClass.getPatientById( id);
        return  ResponseEntity.ok(patients);
    }

}

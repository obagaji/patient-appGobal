package com.appGobal.patient_appGobal.controller;

import com.appGobal.patient_appGobal.entity.Patients;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.appGobal.patient_appGobal.service.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.datatype.Duration;
import java.net.URI;
import java.time.Instant;
import java.util.List;

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
    @GetMapping("/all/patients")
    public ResponseEntity<List<Patients>> getAllPatients()
    {
        List<Patients> all = serviceClass.allGetPatients();
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("all/patients").build().toUri();
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uri);
        header.setDate(Instant.now());
        header.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity.ok().headers(header).body(all);
    }
    @PostMapping("/patient/add")
    public ResponseEntity<Patients> savePatients(@RequestBody Patients patients)
    {
        Patients pat = serviceClass.savePatients(patients) ;
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(pat);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setLocation(uri);
        return ResponseEntity.status(201).body(pat);
    }
    @PutMapping("/update/patient")
    public ResponseEntity<Patients> updatePatients(@RequestBody Patients pa)
    {
      Patients upPatients =  serviceClass.updateInfo(pa);
      URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/id").build().toUri();
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(uri);
      return ResponseEntity.created(uri).headers(headers).body(upPatients);

    }

}

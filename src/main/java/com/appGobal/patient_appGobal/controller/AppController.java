package com.appGobal.patient_appGobal.controller;

import com.appGobal.patient_appGobal.entity.Patients;
import com.appGobal.patient_appGobal.entity.PatientsLogin;
import com.appGobal.patient_appGobal.security.MyUserDetailService;
import com.appGobal.patient_appGobal.security.PatientJwtUtil;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @Autowired
    AuthenticationManager manager;
    @Autowired
    PatientJwtUtil patientJwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String>getAuthenticatedUser(@RequestBody PatientsLogin login)
    {
        Authentication authentication = manager.
                authenticate(new UsernamePasswordAuthenticationToken(login.username(),login.password()));
        String token = "";
        if (authentication.isAuthenticated())
        {
            token = patientJwtUtil.generateToken(new MyUserDetailService().loadUserByUsername(login.username()));
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearar "+ token);
        return ResponseEntity.ok().headers(headers).body(token);
    }

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
    @DeleteMapping("/patient/delete/{id}")
    public ResponseEntity<Void>deletePatients(@PathVariable("id") Integer id)
    {
        serviceClass.delatePatientById(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uri);
        return ResponseEntity.status(200).headers(header).allow(HttpMethod.DELETE).build();
    }
    @DeleteMapping("/patient/delete")
    public ResponseEntity<Void>deletePatients(@RequestBody Patients id)
    {
        serviceClass.deletePatients(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        HttpHeaders header = new HttpHeaders();
        header.setLocation(uri);
        return ResponseEntity.status(200).headers(header).allow(HttpMethod.DELETE).build();
    }

}

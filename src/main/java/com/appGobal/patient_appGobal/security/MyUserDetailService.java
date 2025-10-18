package com.appGobal.patient_appGobal.security;

import com.appGobal.patient_appGobal.entity.Patients;
import com.appGobal.patient_appGobal.repo.PatientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    PatientsRepo patientsRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patients name = patientsRepo.findByFirstName(username).orElseThrow();
        return new PatientsDetail();
    }
}

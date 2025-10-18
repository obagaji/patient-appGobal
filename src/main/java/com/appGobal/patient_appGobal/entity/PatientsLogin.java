package com.appGobal.patient_appGobal.entity;

import org.springframework.stereotype.Component;

@Component
public record PatientsLogin(String username, String password) {
}

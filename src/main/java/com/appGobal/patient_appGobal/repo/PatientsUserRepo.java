package com.appGobal.patient_appGobal.repo;

import com.appGobal.patient_appGobal.entity.PatientsUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsUserRepo extends JpaRepository<PatientsUser, String> {
}

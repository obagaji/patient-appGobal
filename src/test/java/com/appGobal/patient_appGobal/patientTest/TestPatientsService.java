package com.appGobal.patient_appGobal.patientTest;

import com.appGobal.patient_appGobal.entity.NextOfKin;
import com.appGobal.patient_appGobal.entity.Patients;
import com.appGobal.patient_appGobal.service.ServiceClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPatientsService {

    @Autowired
    ServiceClass serviceClass;

    @Test
    void testSavePatients()
    {
        NextOfKin next = NextOfKin.builder()
                .nextOfKinId(2)
                .firstNameNof("seth")
                .lastNameNof("Daniel")
                .midlleNameNof("Odagboyi")
                .build();
        Patients patients = Patients.builder()
                .id(10)
                .dateOfBirth("1990-12-20")
                .emailAddress("email")
                .firstName("Musa")
                .lastName("daniel")
                .gender("male")
                .homeAddress("ojo")
                .phoneNo("123456")
                .middleName("")
                .nextOfKin(next)
                .build();
                serviceClass.savePatients(patients);

    }
}

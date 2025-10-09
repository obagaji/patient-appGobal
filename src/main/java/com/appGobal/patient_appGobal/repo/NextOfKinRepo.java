package com.appGobal.patient_appGobal.repo;

import com.appGobal.patient_appGobal.entity.NextOfKin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NextOfKinRepo extends CrudRepository<NextOfKin,Integer> {
}

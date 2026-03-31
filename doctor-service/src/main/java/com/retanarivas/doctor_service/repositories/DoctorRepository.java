package com.retanarivas.doctor_service.repositories;

import com.retanarivas.doctor_service.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

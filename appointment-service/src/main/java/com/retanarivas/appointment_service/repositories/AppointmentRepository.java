package com.retanarivas.appointment_service.repositories;

import com.retanarivas.appointment_service.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findAppointmentsByDoctorId(Long doctorId);

    List<Appointment> findAppointmentsByPatientId(Long patientId);

}

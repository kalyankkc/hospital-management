package com.mindtree.hospitalmanagmentsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.hospitalmanagmentsystem.entity.Patient;
@Repository
public interface RepositoryPatient extends JpaRepository<Patient,Integer> {

	Optional<Patient> getPatientByPatientName(String patientName);

}

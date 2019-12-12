package com.mindtree.hospitalmanagmentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.hospitalmanagmentsystem.entity.Doctor;
@Repository
public interface RepositoryDoctor extends JpaRepository<Doctor, Integer> {

}

package com.keshab.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keshab.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Serializable>{

}

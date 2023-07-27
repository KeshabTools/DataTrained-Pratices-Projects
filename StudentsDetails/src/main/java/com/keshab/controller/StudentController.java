package com.keshab.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keshab.entity.StudentEntity;
import com.keshab.repo.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

	
	

	    @Autowired
	    private StudentRepository studentRepository;

	    @PostMapping("/create")
	    public ResponseEntity<?> createStudent(@RequestBody StudentEntity student) {
	        // Perform validations
	        List<String> errors = validateStudent(student);
	        if (!errors.isEmpty()) {
	            return ResponseEntity.badRequest().body(errors);
	        }

	        // Calculate total and average marks
	        int totalMarks = calculateTotalMarks(student);
	        double averageMarks = totalMarks / 3.0;

	        // Determine result
	        String result = (student.getMarks1() >= 35 && student.getMarks2() >= 35 && student.getMarks3() >= 35) ? "Pass" : "Fail";

	        // Set calculated values
	        student.setTotal(totalMarks);
	        student.setAverage(averageMarks);
	        student.setResult(result);

	        // Save the student in the database
	        studentRepository.save(student);

	        return ResponseEntity.ok(student);
	    }

	    private List<String> validateStudent(StudentEntity student) {
	        List<String> errors = new ArrayList<>();

	        // Validations
	        if (student.getFirstName().length() < 3 || student.getLastName().length() < 3) {
	            errors.add("First and last names must have a minimum of 3 characters.");
	        }

	        LocalDate currentDate = LocalDate.now();
	        LocalDate dob = student.getDob();
	        if (dob == null || dob.isAfter(currentDate.minusYears(15)) || dob.isEqual(currentDate) || dob.isBefore(currentDate.minusYears(20))) {
	            errors.add("DOB must be provided and should indicate an age greater than 15 and less than or equal to 20 years.");
	        }

	        if (!Arrays.asList("A", "B", "C").contains(student.getSection())) {
	            errors.add("Valid values for section are A, B, and C.");
	        }

	        if (!Arrays.asList("M", "F").contains(student.getGender())) {
	            errors.add("Valid values for gender are M and F.");
	        }

	        return errors;
	    }

	    private int calculateTotalMarks(StudentEntity student) {
	        int marks1 = (student.getMarks1() >= 0 && student.getMarks1() <= 100) ? student.getMarks1() : 0;
	        int marks2 = (student.getMarks2() >= 0 && student.getMarks2() <= 100) ? student.getMarks2() : 0;
	        int marks3 = (student.getMarks3() >= 0 && student.getMarks3() <= 100) ? student.getMarks3() : 0;
	        return marks1 + marks2 + marks3;
	    }
	}



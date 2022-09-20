package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="Doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String doctorName;
		
	private String doctorSpecilist;
		
	private int doctorAge;
	
	private String doctorGender;
	
	private int noOfPatientAddened;
	

	

	
	
}
package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.entity.Doctor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorVO {

private Doctor doctor;
	private List<Patient> patient;	
	
}
package com.example.demo.implementation;

import java.util.List;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Patient;

public interface PatientImpl {
	
	public List<Patient> getAllPatients();
	
	public Patient getPatientById(int id);
	
	public Patient addPatient(Patient patient);
	
	public Patient updatePatient(Patient Patient);
	
	public AddResponse deletePatient(int id);
}

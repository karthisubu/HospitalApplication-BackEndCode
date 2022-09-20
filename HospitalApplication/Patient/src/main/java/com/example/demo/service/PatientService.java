package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Patient;
import com.example.demo.implementation.PatientImpl;
import com.example.demo.repository.PatientRepository;


@Service
@Component
public  class PatientService implements PatientImpl {

	@Autowired
	PatientRepository patientRepository;
	
	@Override
	public List<Patient> getAllPatients()
	{
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(int id)
	{
		return patientRepository.findById(id).get();
	}
	
	@Override
	public Patient addPatient(Patient patient) 
	{
		int id = patientRepository.findAll().size() + 1;
		patient.setId(id);
		patientRepository.save(patient);
		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
	
		patientRepository.save(patient);
		return patient;
	}

	@Override
	public AddResponse deletePatient(int id) {
		
		patientRepository.deleteById(id);
		AddResponse res = new AddResponse();
		res.setId(id);
		res.setMsg("Patient Deleted...");
		return res;
	}

}
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;

@CrossOrigin(origins="*", allowedHeaders = "*")@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientService patService;
	
	@GetMapping("/")
	public ResponseEntity<List<Patient>> getAllPatients()
	{
		List<Patient> patients = patService.getAllPatients();
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientByid(@PathVariable(value="id") int id)
	{
		Patient patient = patService.getPatientById(id);
		return new ResponseEntity<Patient>(patient, HttpStatus.OK);	}
	
	@PostMapping("/")
	public ResponseEntity <Patient> addPatient(@RequestBody Patient patient)
	{
		Patient p= patService.addPatient(patient);
		return  new ResponseEntity<Patient>(p,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value="id") int id, @RequestBody Patient patient)
	{
		try {
			Patient oldPat = patService.getPatientById(id);
			oldPat.setPatientName(patient.getPatientName());
			oldPat.setPatientVisitDoctor(patient.getPatientVisitDoctor());
			oldPat.setPatientDateOfVist(patient.getPatientDateOfVist());
			oldPat.setPatientAge(patient.getPatientAge());
			oldPat.setId(patient.getId());
			patient = patService.updatePatient(oldPat);
			return new ResponseEntity<Patient>(patient, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Patient>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/{id}")
	public AddResponse deletePatient(@PathVariable(value="id") int id)
	{
		AddResponse res = patService.deletePatient(id);
		return res;
	}
}
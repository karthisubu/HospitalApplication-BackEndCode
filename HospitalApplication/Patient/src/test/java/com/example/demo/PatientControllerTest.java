package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.PatientController;
import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {PatientControllerTest.class})
public class PatientControllerTest {

	@Mock
	PatientService patientService;
	
	@InjectMocks
	PatientController patientController;
	
	List<Patient> mypatient;
	Patient patient;
	
	@Test
	@Order(1)
	public void test_getAllPatients() 
	{	
		mypatient = new ArrayList<Patient>();
		mypatient.add(new Patient(1, "Karthi", "Kavin","12-12-2021",23));
		mypatient.add(new Patient(2,"Hari", "Ram","15-03-2022",28));
		mypatient.add(new Patient(3,"Gokul", "Suba","10-03-2021",35));
		
		when(patientService.getAllPatients()).thenReturn(mypatient);
		ResponseEntity<List<Patient>> res=patientController.getAllPatients();
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(3,res.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getPatientbyid() 
	{
		patient=new Patient( 1,"Karthi", "Kavin","12-12-2021",23);
		int id=1;
		when(patientService.getPatientById(id)).thenReturn(patient);
		ResponseEntity<Patient> res=patientController.getPatientByid(id);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(id,res.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void test_addPatient()
	{
		patient=new Patient(1,"Karthi", "Kavin","12-12-2021",23);
		when(patientService.addPatient(patient)).thenReturn(patient);
		ResponseEntity<Patient> res=patientController.addPatient(patient);
		
		assertEquals(HttpStatus.CREATED,res.getStatusCode());
		assertEquals(patient,res.getBody());
	}
	
	@Test
	@Order(4)
	public void test_updatePatient()
	{
		patient=new Patient(1,"Kavin", "Rani","12-12-2021",23);
		int id=1;
		when(patientService.getPatientById(id)).thenReturn(patient);
		when(patientService.updatePatient(patient)).thenReturn(patient);
		
		ResponseEntity<Patient> res=patientController.updatePatient(id,patient);	
		assertEquals(HttpStatus.OK,res.getStatusCode());		
		assertEquals("Kavin",res.getBody().getPatientName());
		assertEquals("Rani",res.getBody().getPatientVisitDoctor());
		assertEquals("12-12-2021",res.getBody().getPatientDateOfVist());
		assertEquals(23,res.getBody().getPatientAge());
		assertEquals(1,res.getBody().getId());
	}
	
	@Test
	@Order(5)
	public void test_deletePatient()
	{
		patient=new Patient(1,"Kavin", "Rani","12-12-2021",23);
		int id=1;
		when(patientService.getPatientById(id)).thenReturn(patient);	
		
	}
}

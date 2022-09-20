package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.AddResponse;
import com.example.demo.controller.DoctorController;
import com.example.demo.entity.Doctor;
import com.example.demo.service.DoctorService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {DoctorControllerTest.class})
public class DoctorControllerTest {

	@Mock
	DoctorService doctorService;
	
	@InjectMocks
	DoctorController doctorController;
	
	List<Doctor> mydoctors;
	Doctor doctor;
	
	@Test
	@Order(1)
	public void test_getAllDoctors() 
	{	
		mydoctors = new ArrayList<Doctor>();
		mydoctors.add(new Doctor( 1,"Karthi", "dentalogist", 30, "Male",3));
		mydoctors.add(new Doctor(2,"Hari", "dentalogist",  33, "Male",3));
		mydoctors.add(new Doctor(3,"Gokul", "dentalogist",  63, "Male",3));
		
		when(doctorService.getAllDoctors()).thenReturn(mydoctors);
		ResponseEntity<List<Doctor>> res=doctorController.getAllDoctors();
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(3,res.getBody().size());
	}
	
	@Test
	@Order(2)
	public void test_getDoctorbyid() 
	{
		doctor=new Doctor(1, "Karthi", "dentalogist", 30, "Male",3);
		int id=1;
		when(doctorService.getDoctorById(id)).thenReturn(doctor);
		ResponseEntity<Doctor> res=doctorController.getDoctorByid(id);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(id,res.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void test_addDoctor()
	{
		doctor=new Doctor( 1,"Karthi", "dentalogist", 30, "Male",3);
		when(doctorService.addDoctor(doctor)).thenReturn(doctor);
		ResponseEntity<Doctor> res=doctorController.addDoctor(doctor);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(doctor,res.getBody());
	}
	
	@Test
	@Order(4)
	public void test_updateDoctor()
	{
		doctor=new Doctor(1,"Kavin", "dentalogist", 30, "Male",3);
		int id=1;
		when(doctorService.getDoctorById(id)).thenReturn(doctor);
		when(doctorService.updateDoctor(doctor)).thenReturn(doctor);
		
		ResponseEntity<Doctor> res=doctorController.updateDoctor(id,doctor);	
		assertEquals(HttpStatus.OK,res.getStatusCode());		
		assertEquals("Kavin",res.getBody().getDoctorName());
		assertEquals("dentalogist",res.getBody().getDoctorSpecilist());
		assertEquals(30,res.getBody().getDoctorAge());
		assertEquals("Male",res.getBody().getDoctorGender());
		assertEquals(1,res.getBody().getId());		
	}
	
	@Test
	@Order(5)
	public void test_deleteDoctor()
	{
		doctor=new Doctor(1,"Kavin", "dentalogist", 30, "Male",3);
		int id=1;
		when(doctorService.getDoctorById(id)).thenReturn(doctor);	
		AddResponse res =doctorController.deleteDoctor(id);	
		
	}
		
}


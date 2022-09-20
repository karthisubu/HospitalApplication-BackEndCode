package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.controller.PatientController;
import com.example.demo.service.PatientService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {PatientApplicationTests.class})
class PatientApplicationTests {

@Mock
	private PatientService patientService;
	
	@InjectMocks
	private PatientController patientController; 
	@Test
	void contextLoads() {
	}

}

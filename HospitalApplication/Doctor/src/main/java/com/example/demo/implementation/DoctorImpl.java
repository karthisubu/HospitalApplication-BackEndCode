package com.example.demo.implementation;

import java.util.List;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Doctor;

import com.example.demo.vo.DoctorVO;

public interface DoctorImpl {
	
	public List<Doctor> getAllDoctors();
	
	public Doctor getDoctorById(int id);
	
	public Doctor addDoctor(Doctor doctor);
	
	public Doctor updateDoctor(Doctor Doctor);
	
	public List<DoctorVO> PatientforParticulardoctor(int id); 
	
	public List<DoctorVO> PatientforParticularResildoctor(int id);
	
	public AddResponse deleteDoctor(int id);
}

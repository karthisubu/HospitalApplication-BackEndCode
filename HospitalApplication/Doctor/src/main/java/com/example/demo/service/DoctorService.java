package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.AddResponse;
import com.example.demo.entity.Doctor;
import com.example.demo.implementation.DoctorImpl;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.vo.DoctorVO;
import com.example.demo.vo.Patient;


@Service
@Component
public class DoctorService implements DoctorImpl {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	RestTemplate restTemp; 
	
	@Override
	public List<Doctor> getAllDoctors() 
	{
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(int id)
	{	
		return doctorRepository.findById(id).get();
	}

	@Override
	public Doctor addDoctor(Doctor doctor)
	{	
		int id = doctorRepository.findAll().size() + 1;
		doctor.setId(id);
		doctorRepository.save(doctor);
		return doctor;
	}
	
	@Override
	public Doctor updateDoctor(Doctor doctor) 
	{
		doctorRepository.save(doctor);
		return doctor;
	}

	@Override
	public List<DoctorVO> PatientforParticulardoctor(int id) 
	{
			List<DoctorVO> fulllist = this.getAllDoctors().stream().filter(p -> p.getId()==id).map(p -> {
			Doctor colg = p; 
			List<Patient> slist = Arrays.asList(
					restTemp.getForEntity("http://PATIENT-SERVICE/patient/doctor/" + id, Patient[].class).getBody());
			return new DoctorVO(colg, slist);
		}).collect(Collectors.toList());
		return fulllist;
	}

	@Override
	public List<DoctorVO> PatientforParticularResildoctor(int id)
	{
		List<DoctorVO> fulllist = this.getAllDoctors().stream().filter(p -> p.getId()==id).map(p -> {
			Doctor colg = p; 
			List<Patient> slist = null;
			return new DoctorVO(colg, slist);
		}).collect(Collectors.toList());
		return fulllist;	
	}

	@Override
	public AddResponse deleteDoctor(int id) 
	{
		doctorRepository.deleteById(id);
		AddResponse res = new AddResponse();
		res.setId(id);
		res.setMsg("Doctor Deleted...");
		return res;
	}

}
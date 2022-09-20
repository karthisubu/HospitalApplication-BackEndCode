import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { Doctor } from './doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private baseURL = "http://localhost:9000/doctor/";

  constructor(private httpClient: HttpClient) { }
  getDoctorsList(): Observable<Doctor[]>{
    return this.httpClient.get<Doctor[]>(`${this.baseURL}`);
  }

  addDoctor(doctor: Doctor): Observable<Doctor>{
    return this.httpClient.post<Doctor>(`${this.baseURL}`, doctor);
  }

  getDoctorByID(id: number): Observable<Doctor>{
    return this.httpClient.get<Doctor>(`${this.baseURL}/${id}`);
  }

  updateDoctor(id: number, doctor: Doctor): Observable<Doctor>{
    return this.httpClient.put<Doctor>(`${this.baseURL}/${id}`, doctor);
  }

  deleteEmployee(id: number): Observable<string>{
    return this.httpClient.delete<string>(`${this.baseURL}/${id}`);
  }

}
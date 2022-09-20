import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { Patient } from './patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private baseURL = "http://localhost:9000/patient/";

  constructor(private httpClient: HttpClient) { }
  getPatientsList(): Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(`${this.baseURL}`);
  }

  addPatient(patient: Patient): Observable<Patient>{
    return this.httpClient.post<Patient>(`${this.baseURL}`, patient);
  }

  getPatientByID(id: number): Observable<Patient>{
    return this.httpClient.get<Patient>(`${this.baseURL}/${id}`);
  }

  updatePatient(id: number, patient: Patient): Observable<Patient>{
    return this.httpClient.put<Patient>(`${this.baseURL}/${id}`, patient);
  }

  deleteEmployee(id: number): Observable<string>{
    return this.httpClient.delete<string>(`${this.baseURL}/${id}`);
  }

}
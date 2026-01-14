
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Intervention} from "../models/Intervention";
import {AlerteDTO} from "../models/AlerteDTO";
import { environment } from 'src/environments/environment';


@Injectable({ providedIn: 'root' })
export class InterventionService {

  private baseUrl = `${environment.apiUrl}/api/v1/maintenance/interventions`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Intervention[]>(this.baseUrl);
  }

  getByStatut(statut: string) {
    return this.http.get<Intervention[]>(`${this.baseUrl}/statut/${statut}`);
  }

  updateStatut(id: number, statut: string) {
    return this.http.put<Intervention>(
      `${this.baseUrl}/${id}/statut`,
      null,
      { params: { statut } }
    );
  }

  getAlertes() {
    return this.http.get<AlerteDTO[]>(`${this.baseUrl}/alertes`);
  }

  getAlerteById(id: number) {
    return this.http.get<AlerteDTO>(`${this.baseUrl}/alertes/${id}`);
  }

}

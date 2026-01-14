import {HttpClient} from "@angular/common/http";
import {Technicien} from "../models/Technicien";
import {Injectable} from "@angular/core";
import { environment } from "src/environments/environment";

@Injectable({ providedIn: 'root' })
export class TechnicienService {

  private baseUrl = `${environment.apiUrl}/api/v1/maintenance/techniciens`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Technicien[]>(this.baseUrl);
  }

  getDisponibles() {
    return this.http.get<Technicien[]>(`${this.baseUrl}/disponibles`);
  }

  create(technicien: Technicien) {
    return this.http.post<Technicien>(this.baseUrl, technicien);
  }
}

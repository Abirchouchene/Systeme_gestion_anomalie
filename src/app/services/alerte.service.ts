import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Alerte} from "../models/Alerte";
import { environment } from "src/environments/environment";


@Injectable({ providedIn: 'root' })
export class AlerteService {

  private baseUrl = `${environment.apiUrl}/api/v1/surveillance/alertes`;

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<Alerte[]>(this.baseUrl);
  }

  getById(id: number) {
    return this.http.get<Alerte>(`${this.baseUrl}/${id}`);
  }

  getByGravite(gravite: string) {
    return this.http.get<Alerte[]>(`${this.baseUrl}/gravite/${gravite}`);
  }

  getRecentes(heures = 24) {
    return this.http.get<Alerte[]>(`${this.baseUrl}/recentes`, {
      params: { heures }
    });
  }

  create(alerte: Alerte) {
    return this.http.post<Alerte>(this.baseUrl, alerte);
  }
}

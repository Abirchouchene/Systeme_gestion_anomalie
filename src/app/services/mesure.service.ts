import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {MesureAnalyse} from "../models/MesureAnalyse";
import { environment } from "src/environments/environment";


@Injectable({ providedIn: 'root' })
export class MesureService {

  private baseUrl = `${environment.apiUrl}/api/v1/surveillance/mesures`;

  constructor(private http: HttpClient) {}

  create(mesure: MesureAnalyse) {
    return this.http.post<MesureAnalyse>(this.baseUrl, mesure);
  }

  getBySource(sourceId: string) {
    return this.http.get<MesureAnalyse[]>(`${this.baseUrl}/source/${sourceId}`);
  }

  getByIndicateur(indicateur: string) {
    return this.http.get<MesureAnalyse[]>(`${this.baseUrl}/indicateur/${indicateur}`);
  }
}

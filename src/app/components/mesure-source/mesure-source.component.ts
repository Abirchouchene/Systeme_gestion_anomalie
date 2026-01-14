import { Component } from '@angular/core';
import {MesureAnalyse} from "../../models/MesureAnalyse";
import {MesureService} from "../../services/mesure.service";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-mesure-source',
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './mesure-source.component.html',
  styleUrl: './mesure-source.component.scss'
})
export class MesureSourceComponent {
  sourceId = '';
  mesures: MesureAnalyse[] = [];
  loading: boolean = false;
  recherchEffectuee: boolean = false;

  constructor(private service: MesureService) {}

  charger(): void {
    if (this.sourceId) {
      this.loading = true;
      this.service.getBySource(this.sourceId).subscribe({
        next: (res) => {
          this.mesures = res;
          this.loading = false;
          this.recherchEffectuee = true;
        },
        error: (err) => {
          console.error('Erreur lors du chargement:', err);
          this.loading = false;
          this.recherchEffectuee = true;
        }
      });
    }
  }
}

import { Component } from '@angular/core';
import {Intervention} from "../../models/Intervention";
import {InterventionService} from "../../services/intervention.service";
import {FormsModule} from "@angular/forms";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-intervention-status',
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './intervention-status.component.html',
  styleUrl: './intervention-status.component.scss'
})
export class InterventionStatusComponent {
  statut = '';
  interventions: Intervention[] = [];
  loading: boolean = false;
  recherchEffectuee: boolean = false;

  constructor(private service: InterventionService) {}

  rechercher(): void {
    if (this.statut) {
      this.loading = true;
      this.service.getByStatut(this.statut).subscribe({
        next: (res) => {
          this.interventions = res;
          this.loading = false;
          this.recherchEffectuee = true;
        },
        error: (err) => {
          console.error('Erreur lors de la recherche:', err);
          this.loading = false;
          this.recherchEffectuee = true;
        }
      });
    }
  }
}

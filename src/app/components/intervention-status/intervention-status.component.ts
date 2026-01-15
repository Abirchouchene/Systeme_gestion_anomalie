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
      this.recherchEffectuee = false;

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
          this.interventions = [];
        }
      });
    }
  }

  getStatutBadgeClass(statut: string): string {
    switch(statut) {
      case 'EN_ATTENTE': return 'bg-warning text-dark';
      case 'EN_COURS': return 'bg-info';
      case 'TERMINEE': return 'bg-success';
      case 'ANNULEE': return 'bg-secondary';
      default: return 'bg-secondary';
    }
  }

  getStatutIcon(statut: string): string {
    switch(statut) {
      case 'EN_ATTENTE': return 'bi-clock';
      case 'EN_COURS': return 'bi-gear-fill';
      case 'TERMINEE': return 'bi-check-circle';
      case 'ANNULEE': return 'bi-x-circle';
      default: return 'bi-question-circle';
    }
  }
}

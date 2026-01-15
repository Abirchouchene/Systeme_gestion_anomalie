import {Component, OnInit} from '@angular/core';
import {Intervention} from "../../models/Intervention";
import {InterventionService} from "../../services/intervention.service";
import {DatePipe} from "@angular/common";
import { CommonModule } from '@angular/common';
import { Technicien } from 'src/app/models/Technicien';
import { TechnicienService } from 'src/app/services/technicien.service';
import { FormsModule } from '@angular/forms';  // ✅ AJOUTER CECI

@Component({
  selector: 'app-intervention-list',
  imports: [
    DatePipe,
    CommonModule,FormsModule
    
  ],
  templateUrl: './intervention-list.component.html',
  styleUrl: './intervention-list.component.scss'
})
export class InterventionListComponent implements OnInit {
  interventions: Intervention[] = [];
  techniciens: Technicien[] = [];
  loading: boolean = false;
  error: string = '';

  // Filtres et stats
  filtreStatut: string = '';

  constructor(
    private interventionService: InterventionService,
    private technicienService: TechnicienService
  ) {}

  ngOnInit(): void {
    this.chargerDonnees();
  }

  chargerDonnees(): void {
    this.loading = true;
    this.error = '';

    // Charger les interventions
    this.interventionService.getAll().subscribe({
      next: (interventions) => {
        this.interventions = interventions;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des interventions:', err);
        this.error = 'Impossible de charger les interventions';
        this.loading = false;
      }
    });

    // Charger les techniciens
    this.technicienService.getAll().subscribe({
      next: (techniciens) => {
        this.techniciens = techniciens;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des techniciens:', err);
      }
    });
  }

  getNomTechnicien(technicienId: number): string {
    const technicien = this.techniciens.find(t => t.id === technicienId);
    return technicien ? technicien.nom : `Technicien #${technicienId}`;
  }

  changerStatut(intervention: Intervention, nouveauStatut: string): void {
    if (intervention.id) {
      this.interventionService.updateStatut(intervention.id, nouveauStatut).subscribe({
        next: (updated) => {
          const index = this.interventions.findIndex(i => i.id === updated.id);
          if (index !== -1) {
            this.interventions[index] = updated;
          }
          console.log('✅ Statut mis à jour avec succès');

          // Recharger les techniciens pour mettre à jour leur disponibilité
          this.technicienService.getAll().subscribe({
            next: (techniciens) => {
              this.techniciens = techniciens;
            }
          });
        },
        error: (err) => {
          console.error('❌ Erreur lors de la mise à jour du statut:', err);
          alert('Erreur lors de la mise à jour du statut');
        }
      });
    }
  }

  // Méthodes pour les statistiques
  getInterventionsByStatut(statut: string): number {
    return this.interventions.filter(i => i.statut === statut).length;
  }

  getInterventionsFiltrees(): Intervention[] {
    if (!this.filtreStatut) {
      return this.interventions;
    }
    return this.interventions.filter(i => i.statut === this.filtreStatut);
  }

  // Méthodes pour les classes CSS
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

  // Méthode pour rafraîchir les données
  rafraichir(): void {
    this.chargerDonnees();
  }
}

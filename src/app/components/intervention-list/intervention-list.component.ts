import {Component, OnInit} from '@angular/core';
import {Intervention} from "../../models/Intervention";
import {InterventionService} from "../../services/intervention.service";
import {DatePipe} from "@angular/common";
import { CommonModule } from '@angular/common';
import { Technicien } from 'src/app/models/Technicien';
import { TechnicienService } from 'src/app/services/technicien.service';

@Component({
  selector: 'app-intervention-list',
  imports: [
    DatePipe,
    CommonModule
  ],
  templateUrl: './intervention-list.component.html',
  styleUrl: './intervention-list.component.scss'
})
export class InterventionListComponent implements OnInit {
  interventions: Intervention[] = [];
  techniciens: Technicien[] = [];
  loading: boolean = false;
  error: string = '';

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

  // ✅ Signature corrigée - accepte 'number' seulement (pas undefined)
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
}
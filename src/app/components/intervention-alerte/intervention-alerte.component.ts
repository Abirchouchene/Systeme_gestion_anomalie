import { Component ,OnInit} from '@angular/core';
import {AlerteDTO} from "../../models/AlerteDTO";
import {InterventionService} from "../../services/intervention.service";
import {DatePipe, NgClass} from "@angular/common";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-intervention-alerte',
  imports: [
    NgClass,
    DatePipe,
    CommonModule
  ],
  templateUrl: './intervention-alerte.component.html',
  styleUrl: './intervention-alerte.component.scss'
})
export class InterventionAlertesComponent implements OnInit {
  alertes: AlerteDTO[] = [];
  selectedAlerte?: AlerteDTO;
  loading: boolean = false;
  error: string = '';

  constructor(private interventionService: InterventionService) {}

  ngOnInit(): void {
    this.chargerAlertes();
  }

  chargerAlertes(): void {
    this.loading = true;
    this.error = '';

    this.interventionService.getAlertes().subscribe({
      next: (data) => {
        this.alertes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des alertes:', err);
        this.error = 'Impossible de récupérer les alertes depuis Surveillance';
        this.loading = false;
      }
    });
  }

  voirDetails(id: number): void {
    this.interventionService.getAlerteById(id).subscribe({
      next: (res) => {
        this.selectedAlerte = res;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des détails:', err);
      }
    });
  }

  fermerDetails(): void {
    this.selectedAlerte = undefined;
  }
}

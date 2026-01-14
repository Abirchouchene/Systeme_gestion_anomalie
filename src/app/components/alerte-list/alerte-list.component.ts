import { Component, OnInit } from '@angular/core';
import {Alerte} from "../../models/Alerte";
import {AlerteService} from "../../services/alerte.service";
import {DatePipe, NgClass, CommonModule} from "@angular/common";


@Component({
  selector: 'app-alerte-list',
  imports: [
    DatePipe,
    NgClass,
    CommonModule
  ],
  templateUrl: './alerte-list.component.html',
  styleUrl: './alerte-list.component.scss'
})
export class AlerteListComponent implements OnInit {

  alertes: Alerte[] = [];
  alertesFiltrees: Alerte[] = [];
  filtreActif: string = 'TOUTES';
  loading: boolean = false;
  error: string = '';

  constructor(private alerteService: AlerteService) {}

  ngOnInit(): void {
    this.chargerAlertes();
  }

  chargerAlertes(): void {
    this.loading = true;
    this.error = '';

    this.alerteService.getAll().subscribe({
      next: (res) => {
        this.alertes = res;
        this.alertesFiltrees = res;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des alertes:', err);
        this.error = 'Impossible de charger les alertes';
        this.loading = false;
      }
    });
  }

  filtrerParGravite(gravite: string): void {
    this.filtreActif = gravite;

    if (gravite === 'TOUTES') {
      this.alertesFiltrees = this.alertes;
    } else {
      this.alerteService.getByGravite(gravite).subscribe({
        next: (res) => {
          this.alertesFiltrees = res;
        },
        error: (err) => {
          console.error('Erreur lors du filtrage:', err);
        }
      });
    }
  }

  chargerRecentes(heures: number = 24): void {
    this.alerteService.getRecentes(heures).subscribe({
      next: (res) => {
        this.alertes = res;
        this.alertesFiltrees = res;
      },
      error: (err) => {
        console.error('Erreur:', err);
      }
    });
  }

}

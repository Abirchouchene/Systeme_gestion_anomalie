import { Component ,OnInit } from '@angular/core';
import {Technicien} from "../../models/Technicien";
import {TechnicienService} from "../../services/technicien.service";
import { CommonModule ,DatePipe} from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Intervention } from 'src/app/models/Intervention';
import { InterventionService } from 'src/app/services/intervention.service';

@Component({
  selector: 'app-technicien-list',
  imports: [CommonModule ,FormsModule ,DatePipe],
  templateUrl: './technicien-list.component.html',
  styleUrl: './technicien-list.component.scss'
})
export class TechnicienListComponent implements OnInit {
  techniciens: Technicien[] = [];
  loading: boolean = false;
  error: string = '';
  showAddForm: boolean = false;

  nouveauTechnicien: Technicien = {
    nom: '',
    specialite: '',
    disponibilite: true
  };

  constructor(private technicienService: TechnicienService) {}

  ngOnInit(): void {
    this.chargerTechniciens();
  }

  chargerTechniciens(): void {
    this.loading = true;
    this.error = '';

    this.technicienService.getAll().subscribe({
      next: (data) => {
        this.techniciens = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des techniciens:', err);
        this.error = 'Impossible de charger les techniciens';
        this.loading = false;
      }
    });
  }

  // ✅ Méthode pour obtenir les initiales
  getInitiales(nom: string): string {
    return nom
      .split(' ')
      .map(n => n[0])
      .join('')
      .toUpperCase();
  }

  ajouterTechnicien(): void {
    if (this.nouveauTechnicien.nom && this.nouveauTechnicien.specialite) {
      this.technicienService.create(this.nouveauTechnicien).subscribe({
        next: (technicien) => {
          this.techniciens.push(technicien);
          this.resetForm();
          this.showAddForm = false;
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du technicien:', err);
          alert('Erreur lors de l\'ajout du technicien');
        }
      });
    }
  }

  resetForm(): void {
    this.nouveauTechnicien = {
      nom: '',
      specialite: '',
      disponibilite: true
    };
  }

  toggleForm(): void {
    this.showAddForm = !this.showAddForm;
    if (!this.showAddForm) {
      this.resetForm();
    }
  }

  onDisponibiliteChange(event: any): void {
    this.nouveauTechnicien.disponibilite = event.target.value === 'true';
  }
}

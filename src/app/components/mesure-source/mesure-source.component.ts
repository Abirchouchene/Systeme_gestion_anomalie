import { Component, OnInit } from '@angular/core';
import {MesureAnalyse} from "../../models/MesureAnalyse";
import {MesureService} from "../../services/mesure.service";
import {FormBuilder, FormGroup, FormsModule, Validators,ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
declare var bootstrap: any;

@Component({
  selector: 'app-mesure-source',
  imports: [
    FormsModule,
    CommonModule,ReactiveFormsModule
  ],
  templateUrl: './mesure-source.component.html',
  styleUrl: './mesure-source.component.scss'
})

export class MesureSourceComponent implements OnInit {
  sourceId = '';
  mesures: MesureAnalyse[] = [];
  loading = false;
  recherchEffectuee = false;

  // Formulaire d'ajout
  mesureForm: FormGroup;
  submitting = false;
  afficherModal = false;

  constructor(
    private service: MesureService,
    private fb: FormBuilder
  ) {
    this.mesureForm = this.fb.group({
      sourceId: ['', Validators.required],
      indicateur: ['', Validators.required],
      valeur: ['', [Validators.required, Validators.min(0)]],
      date: [new Date().toISOString().slice(0, 16), Validators.required]
    });
  }

  ngOnInit(): void {}

  charger(): void {
    if (!this.sourceId.trim()) {
      return;
    }

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
        this.mesures = [];
      }
    });
  }

  ouvrirModal(): void {
    this.mesureForm.patchValue({
      sourceId: this.sourceId
    });

    const modalElement = document.getElementById('addMesureModal');
    if (modalElement) {
      const modal = new (window as any).bootstrap.Modal(modalElement);
      modal.show();
    }
  }


  fermerModal(): void {
    this.mesureForm.reset({
      date: new Date().toISOString().slice(0, 16)
    });
  }

  ajouterMesure(): void {
    if (this.mesureForm.invalid) {
      Object.keys(this.mesureForm.controls).forEach(key => {
        this.mesureForm.get(key)?.markAsTouched();
      });
      return;
    }

    this.submitting = true;
    const mesure: MesureAnalyse = this.mesureForm.value;

    this.service.create(mesure).subscribe({
      next: (res) => {
        this.mesures.unshift(res);
        this.submitting = false;
        this.fermerModal();
        this.afficherNotification('Mesure ajoutée avec succès!', 'success');
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout:', err);
        this.submitting = false;
        this.afficherNotification('Erreur lors de l\'ajout de la mesure', 'error');
      }
    });
  }

  afficherNotification(message: string, type: 'success' | 'error'): void {
    // Vous pouvez utiliser une bibliothèque comme ngx-toastr
    alert(message);
  }

  getIconeIndicateur(indicateur: string): string {
    const icons: { [key: string]: string } = {
      'température': 'fa-thermometer-half',
      'humidité': 'fa-tint',
      'pression': 'fa-compress',
      'vitesse': 'fa-tachometer-alt',
      'niveau': 'fa-layer-group',
      'débit': 'fa-water'
    };

    const key = indicateur.toLowerCase();
    for (const [k, icon] of Object.entries(icons)) {
      if (key.includes(k)) return icon;
    }
    return 'fa-chart-bar';
  }

  getCouleurIndicateur(indicateur: string): string {
    const hash = indicateur.split('').reduce((acc, char) => {
      return char.charCodeAt(0) + ((acc << 5) - acc);
    }, 0);

    const colors = [
      'primary', 'success', 'info', 'warning',
      'danger', 'secondary', 'dark'
    ];

    return colors[Math.abs(hash) % colors.length];
  }
}

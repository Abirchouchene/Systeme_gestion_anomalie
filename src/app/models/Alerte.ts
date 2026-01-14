export interface Alerte {
  id?: number;
  type: string;
  message: string;
  niveauGravite: string;
  dateDetection: string; // LocalDateTime → string ISO
}

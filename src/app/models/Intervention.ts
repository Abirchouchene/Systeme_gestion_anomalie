export interface Intervention {
  id?: number;
  alerteId: number;
  technicienId: number;
  datePlanifiee: string; // LocalDate
  statut: string;

}

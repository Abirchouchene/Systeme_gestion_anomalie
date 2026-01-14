export interface AlerteDTO {
  id?: number;
  type: string;
  message: string;
  niveauGravite: string;
  dateDetection: string; // LocalDateTime → ISO string
}

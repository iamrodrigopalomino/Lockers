package com.rodri.lockers.backendlockersproject.dto;

import com.rodri.lockers.backendlockersproject.entity.LockerSize;

public class LockerCreateDTO {

  private String numero;
  private String edificio;
  private String piso;
  private LockerSize tipo;

  public String getNumero() { return numero; }
  public void setNumero(String numero) { this.numero = numero; }

  public String getEdificio() { return edificio; }
  public void setEdificio(String edificio) { this.edificio = edificio; }

  public String getPiso() { return piso; }
  public void setPiso(String piso) { this.piso = piso; }

  public LockerSize getTipo() { return tipo; }
  public void setTipo(LockerSize tipo) { this.tipo = tipo; }
}

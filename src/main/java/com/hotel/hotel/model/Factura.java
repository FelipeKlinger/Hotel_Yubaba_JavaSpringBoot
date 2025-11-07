package com.hotel.hotel.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "factures")
public class Factura {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)   
private Long id;


@Column(nullable = false)  
private LocalDate data_emisio;


@Column(nullable = false)
private String metode_pagament;


@Column(nullable = false)
private double base_imposable;


@Column(nullable = false)
private double iva;


@Column(nullable = false)
private double total;

@OneToOne(mappedBy = "factura")
private Reserva reserva;


public Factura() {
}


public Factura(Long id, LocalDate data_emisio, String metode_pagament, double base_imposable, double iva,
     double total, Reserva reserva) {
   this.id = id;
   this.data_emisio = data_emisio;
   this.metode_pagament = metode_pagament;
   this.base_imposable = base_imposable;
   this.iva = iva;
   this.total = total;
  this.reserva = reserva;
}


public long getId() {
   return id;
}


public void setId(long id) {
   this.id = id;
}


public LocalDate getData_emisio() {
   return data_emisio;
}


public void setData_emisio(LocalDate data_emisio) {
   this.data_emisio = data_emisio;
}


public String getMetode_pagament() {
   return metode_pagament;
}


public void setMetode_pagament(String metode_pagament) {
   this.metode_pagament = metode_pagament;
}


public double getBase_imposable() {
   return base_imposable;
}


public void setBase_imposable(double base_imposable) {
   this.base_imposable = base_imposable;
}


public double getIva() {
   return iva;
}


public void setIva(double iva) {
   this.iva = iva;
}


public double getTotal() {
   return total;
}


public void setTotal(double total) {
   this.total = total;
}


public Reserva getReserva() {
   return reserva;
}


public void setReserva(Reserva reserva) {
   this.reserva = reserva;
}




}

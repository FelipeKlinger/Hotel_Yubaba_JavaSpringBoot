package com.hotel.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "valoracions")
public class Valoracions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int puntuacio;

    @Column(length = 1000)
    private String comentari;

    private String email;

    private boolean llegit = false;

    private LocalDate data_creacio;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;

    public Valoracions() {
    }

    public Valoracions(Long id, int puntuacio, String comentari, String email, boolean llegit, LocalDate data_creacio,
            Reserva reserva) {
        this.id = id;
        this.puntuacio = puntuacio;
        this.comentari = comentari;
        this.email = email;
        this.llegit = llegit;
        this.data_creacio = data_creacio;
        this.reserva = reserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public String getComentari() {
        return comentari;
    }

    public void setComentari(String comentari) {
        this.comentari = comentari;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLlegit() {
        return llegit;
    }

    public void setLlegit(boolean llegit) {
        this.llegit = llegit;
    }

    public LocalDate getData_creacio() {
        return data_creacio;
    }

    public void setData_creacio(LocalDate data_creacio) {
        this.data_creacio = data_creacio;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

}

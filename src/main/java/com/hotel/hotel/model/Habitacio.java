package com.hotel.hotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "habitacions")
public class Habitacio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int numero;

    private int capacitat;
    private String descripcio;

    @Enumerated(EnumType.STRING)
    private TipusHabitacio tipus;

    @Enumerated(EnumType.STRING)
    private EstatHabitacio estat;

    private double preunitmp; // Mitja Pensió
    private double preunitad; // Allotjament i esmorzar

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    public Habitacio() {
    }

    public Habitacio(Long id, int numero, int capacitat, String descripcio, TipusHabitacio tipus, EstatHabitacio estat,
            double preunitad, double preunitmp, Hotel hotel) {
        this.id = id;
        this.numero = numero;
        this.capacitat = capacitat;
        this.descripcio = descripcio;
        this.tipus = tipus;
        this.estat = estat;
        this.preunitmp = preunitmp;
        this.preunitad = preunitad;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public TipusHabitacio getTipus() {
        return tipus;
    }

    public void setTipus(TipusHabitacio tipus) {
        this.tipus = tipus;
    }

    public EstatHabitacio getEstat() {
        return estat;
    }

    public void setEstat(EstatHabitacio estat) {
        this.estat = estat;
    }

    public double getPreuNitMP() {
        return preunitmp;
    }

    public void setPreuNitMP(double preunitmp) {
        this.preunitmp = preunitmp;
    }

    public double getPreuNitAD() {
        return preunitad;
    }

    public void setPreuNitAD(double preunitad) {
        this.preunitad = preunitad;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

}

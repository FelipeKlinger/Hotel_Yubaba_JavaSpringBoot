package com.hotel.hotel.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "empleats")
public class Empleat extends Persona{

    private String llocFeina;
    private LocalDate data_contractacio;
    private double salariBrut;
    @Enumerated(EnumType.STRING)
    private EstatLaboral estat;

    public Empleat() {
        
    }
    
    @ManyToMany
    private List<Tasca> tasques;

 
    public Empleat(Long id, String nom, String cognom, String adreça, String document_identitat,
            LocalDate data_naixement, String telefon, String email, Usuari usuari, String llocFeina,
            LocalDate data_contractacio, double salariBrut, EstatLaboral estat, List<Tasca> tasques) {
        super(id, nom, cognom, adreça, document_identitat, data_naixement, telefon, email, usuari);
        this.llocFeina = llocFeina;
        this.data_contractacio = data_contractacio;
        this.salariBrut = salariBrut;
        this.estat = estat;
        this.tasques = tasques;
    }

    public String getLlocFeina() {
        return llocFeina;
    }

    public void setLlocFeina(String llocFeina) {
        this.llocFeina = llocFeina;
    }

    public LocalDate getData_contractacio() {
        return data_contractacio;
    }

    public void setData_contractacio(LocalDate data_contractacio) {
        this.data_contractacio = data_contractacio;
    }

    public double getSalariBrut() {
        return salariBrut;
    }

    public void setSalariBrut(double salariBrut) {
        this.salariBrut = salariBrut;
    }

    public EstatLaboral getEstat() {
        return estat;
    }

    public void setEstat(EstatLaboral estat) {
        this.estat = estat;
    }

    public List<Tasca> getTasques() {
        return tasques;
    }


    public void setTasques(List<Tasca> tasques) {
        this.tasques = tasques;
    }
    
    
}

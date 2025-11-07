package com.hotel.hotel.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client extends Persona {
    
    private LocalDate data_registre;

    @Enumerated(EnumType.STRING)
    private CategoriaClient tipus;
    
    private int targeta_credit;


    public Client() {
        
    }

    public Client(Long id, String nom, String cognom, String adreça, String document_identitat,
            LocalDate data_naixement, String telefon, String email, Usuari usuari, LocalDate data_registre,
            CategoriaClient tipus, int targeta_credit) {
        super(id, nom, cognom, adreça, document_identitat, data_naixement, telefon, email, usuari);
        this.data_registre = data_registre;
        this.tipus = tipus;
        this.targeta_credit = targeta_credit;
    
    }

    public LocalDate getData_registre() {
        return data_registre;
    }

    public void setData_registre(LocalDate data_registre) {
        this.data_registre = data_registre;
    }

    public CategoriaClient getTipus() {
        return tipus;
    }

    public void setTipus(CategoriaClient tipus) {
        this.tipus = tipus;
    }

    public int getTargeta_credit() {
        return targeta_credit;
    }

    public void setTargeta_credit(int targeta_credit) {
        this.targeta_credit = targeta_credit;
    }






    
}
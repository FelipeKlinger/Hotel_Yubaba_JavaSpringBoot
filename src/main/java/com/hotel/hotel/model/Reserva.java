package com.hotel.hotel.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserves")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date data_reserva;

    @Column(nullable = false)
    private Date data_inici;

    @Column(nullable = false)
    private Date data_fi;

    @Enumerated(EnumType.STRING)
    private TipusReserva tipus_reserva;

    private int tipus_iva;

    private double preu_total_reserva;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "habitacio_id", nullable = false)
    private Habitacio habitacio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id", nullable = false)
    private Factura factura;

    public Reserva() {
    }

    public Reserva(Long id, Date data_reserva, Date data_inici, Date data_fi, TipusReserva tipus_reserva, int tipus_iva,
            double preu_total_reserva, Client client, Habitacio habitacio, Factura factura) {
        this.id = id;
        this.data_reserva = data_reserva;
        this.data_inici = data_inici;
        this.data_fi = data_fi;
        this.tipus_reserva = tipus_reserva;
        this.tipus_iva = tipus_iva;
        this.preu_total_reserva = preu_total_reserva;
        this.client = client;
        this.habitacio = habitacio;
        this.factura = factura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData_reserva() {
        return data_reserva;
    }

    public void setData_reserva(Date data_reserva) {
        this.data_reserva = data_reserva;
    }

    public Date getData_inici() {
        return data_inici;
    }

    public void setData_inici(Date data_inici) {
        this.data_inici = data_inici;
    }

    public Date getData_fi() {
        return data_fi;
    }

    public void setData_fi(Date data_fi) {
        this.data_fi = data_fi;
    }

    public TipusReserva getTipus_reserva() {
        return tipus_reserva;
    }

    public void setTipus_reserva(TipusReserva tipus_reserva) {
        this.tipus_reserva = tipus_reserva;
    }

    public int getTipus_iva() {
        return tipus_iva;
    }

    public void setTipus_iva(int tipus_iva) {
        this.tipus_iva = tipus_iva;
    }

    public double getPreu_total_reserva() {
        return preu_total_reserva;
    }

    public void setPreu_total_reserva(double preu_total_reserva) {
        this.preu_total_reserva = preu_total_reserva;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Habitacio getHabitacio() {
        return habitacio;
    }

    public void setHabitacio(Habitacio habitacio) {
        this.habitacio = habitacio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}

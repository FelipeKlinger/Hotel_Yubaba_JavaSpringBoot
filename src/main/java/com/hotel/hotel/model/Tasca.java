package com.hotel.hotel.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "tasques")
public class Tasca {
    
@Id
@GeneratedValue(strategy = GenerationType.AUTO)  
private Long id;


private String descripcio;



@Column (nullable = false)
private Date data_creacio;

@Column (nullable = false)
private Date data_ejecucio;

@Enumerated(EnumType.STRING)
private EstatTasca estat;



@ManyToMany(mappedBy = "tasques")
private List<Empleat> empleats;


public Tasca() {
}

public Tasca(Long id, String descripcio, Date data_creacio, Date data_ejecucio, EstatTasca estat, List<Empleat> empleats) {
    this.id = id;
    this.descripcio = descripcio;
    this.data_creacio = data_creacio;
    this.data_ejecucio = data_ejecucio;
    this.estat = estat;
    this.empleats = empleats;
}


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getDescripcio() {
    return descripcio;
}

public void setDescripcio(String descripcio) {
    this.descripcio = descripcio;
}

public Date getData_creacio() {
    return data_creacio;
}

public void setData_creacio(Date data_creacio) {
    this.data_creacio = data_creacio;
}

public Date getData_ejecucio() {
    return data_ejecucio;
}

public void setData_ejecucio(Date data_ejecucio) {
    this.data_ejecucio = data_ejecucio;
}

public EstatTasca getEstat() {
    return estat;
}

public void setEstat(EstatTasca estat) {
    this.estat = estat;
}

public List<Empleat> getEmpleats() {
    return empleats;
}

public void setEmpleats(List<Empleat> empleats) {
    this.empleats = empleats;
}






}

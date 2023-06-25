package com.eupc.appsalud.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class CentroSalud  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String tipo;
    private int calificacionInfraestructura;
    private int calificacionServicios;
    private boolean ambulancias;

    public CentroSalud() {
    }

    public CentroSalud(Long codigo, String tipo, int calificacionInfraestructura, int calificacionServicios, boolean ambulancias) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.calificacionInfraestructura = calificacionInfraestructura;
        this.calificacionServicios = calificacionServicios;
        this.ambulancias = ambulancias;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCalificacionInfraestructura() {
        return calificacionInfraestructura;
    }

    public void setCalificacionInfraestructura(int calificacionInfraestructura) {
        this.calificacionInfraestructura = calificacionInfraestructura;
    }

    public int getCalificacionServicios() {
        return calificacionServicios;
    }

    public void setCalificacionServicios(int calificacionServicios) {
        this.calificacionServicios = calificacionServicios;
    }

    public boolean isAmbulancias() {
        return ambulancias;
    }

    public void setAmbulancias(boolean ambulancias) {
        this.ambulancias = ambulancias;
    }

    @Override
    public String toString() {
        return "CentroSalud{" +
                "codigo=" + codigo +
                ", tipo='" + tipo + '\'' +
                ", calificacionInfraestructura=" + calificacionInfraestructura +
                ", calificacionServicios=" + calificacionServicios +
                ", ambulancias=" + ambulancias +
                '}';
    }
}

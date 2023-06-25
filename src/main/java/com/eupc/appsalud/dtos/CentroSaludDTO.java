package com.eupc.appsalud.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CentroSaludDTO {

    private Long codigo;
    private String tipo;
    private int calificacionInfraestructura;
    private int calificacionServicios;
    private boolean ambulancias;
    private double calificacionFinal;


}

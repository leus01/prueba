package com.eupc.appsalud.repositorio;

import com.eupc.appsalud.dtos.CentroSaludDTO;
import com.eupc.appsalud.entidades.CentroSalud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorioCentroSalud extends JpaRepository<CentroSalud, Long> {
    List<CentroSalud> findCentroSaludsByTipo(String tipo);
}

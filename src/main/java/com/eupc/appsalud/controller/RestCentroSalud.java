package com.eupc.appsalud.controller;

import com.eupc.appsalud.entidades.CentroSalud;
import com.eupc.appsalud.dtos.CentroSaludDTO;
import com.eupc.appsalud.negocio.NegocioCentroSalud;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestCentroSalud {
    @Autowired
    private NegocioCentroSalud negocioCentroSalud;
    @PostMapping("/centroSalud")//1.-
    public CentroSaludDTO registrar(@RequestBody CentroSaludDTO centroSaludDTO){
        CentroSalud centroSalud;
        try {
            centroSalud = convertToEntity(centroSaludDTO);
            centroSalud = negocioCentroSalud.registrar(centroSalud);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible registrarlo");
        }
        return convertToDto(centroSalud);
    }
    @GetMapping("/centroSalud")//2.-
    public ResponseEntity<List<CentroSaludDTO>> obtenerReporte(){
        List<CentroSaludDTO> centroSaludDTOS;
        try {
            centroSaludDTOS = convertToLisDto(negocioCentroSalud.obtenerReporte());
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
        }
        return new ResponseEntity<>(centroSaludDTOS, HttpStatus.OK);
    }
    @GetMapping("/centroSaludResultado") //3.-
    public List<CentroSaludDTO> obtenerReporteResultados(){
        List<CentroSaludDTO> centroSaludDTOS;
        try {
            centroSaludDTOS = negocioCentroSalud.obtenerReporteResultados();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
        }
        return centroSaludDTOS;
    }

    @PutMapping("/centroSalud/{codigo}")
    public ResponseEntity<CentroSaludDTO> actualizar(@PathVariable(value = "codigo") Long codigo,
                                                     @RequestBody CentroSaludDTO centroSaludDTO){
        CentroSalud centroSalud;
        CentroSalud centroSaludActualizado;
        try {
            centroSalud = convertToEntity(centroSaludDTO);
            centroSaludActualizado = negocioCentroSalud.actualizar(codigo, centroSalud);
            centroSaludDTO = convertToDto(centroSaludActualizado);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible actualizar el centro de salud");
        }
        return  new ResponseEntity<CentroSaludDTO>(centroSaludDTO,HttpStatus.OK);
    }

    @GetMapping("/centroSaludTipo/{tipo}") // 4.-
    public ResponseEntity<List<CentroSaludDTO>> obtenerListadoCentrosTipo (@PathVariable(value = "tipo") String tipo){
        List<CentroSalud> centrosSalud;
        List<CentroSaludDTO> centroSaludDTOS;
        try {
            centrosSalud = negocioCentroSalud.obtenerReporte(tipo);
            centroSaludDTOS = convertToLisDto(centrosSalud);
        }catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible obtener listado");
        }
        return new ResponseEntity<>(centroSaludDTOS,HttpStatus.OK);

    }

    @GetMapping ("/centroSalud/{xCodigo}")
    public ResponseEntity<String> obtenerEvaluacionCentro (@PathVariable(value = "xCodigo") Long xCodigo)
            throws Exception
    {
        String evaluado;
        try {
            evaluado = negocioCentroSalud.obtenerCentroEvaluado(xCodigo);
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No fue posible encontrar su registro");
        }
        return new ResponseEntity<String>(evaluado, HttpStatus.OK);
    }

    /*--------------------*/
    private CentroSaludDTO convertToDto(CentroSalud centroSalud) {
        ModelMapper modelMapper = new ModelMapper();
        CentroSaludDTO centroSaludDTO = modelMapper.map(centroSalud, CentroSaludDTO.class);
        return centroSaludDTO;
    }

    private CentroSalud convertToEntity(CentroSaludDTO centroSaludDTO) {
        ModelMapper modelMapper = new ModelMapper();
        CentroSalud post = modelMapper.map(centroSaludDTO, CentroSalud.class);
        return post;
    }

    private List<CentroSaludDTO> convertToLisDto(List<CentroSalud> list){
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

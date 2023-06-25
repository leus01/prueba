package com.eupc.appsalud;

import com.eupc.appsalud.entidades.CentroSalud;
import com.eupc.appsalud.dtos.CentroSaludDTO;
import com.eupc.appsalud.negocio.NegocioCentroSalud;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppSaludApplicationTests {

    @Autowired
    private NegocioCentroSalud negocioCentroSalud;

    @Test
    void contextLoads() {
    }

    @Test
    public void probarRegistrar(){
        CentroSalud centroSalud = new CentroSalud();
        centroSalud.setAmbulancias(true);
        centroSalud.setTipo("Hospital");
        centroSalud.setCalificacionInfraestructura(89);
        centroSalud.setCalificacionServicios(90);
        CentroSalud salud = negocioCentroSalud.registrar(centroSalud);
        Assert.assertNotNull(salud);
    }
    @Test
    public void probarCalificacion(){
        double r;
        r = negocioCentroSalud.calcularCalificacion(1L);
        Assert.assertEquals(89.1, r, 0.01);
    }
    @Test
    public void probarResultadoFinal(){
        Assert.assertEquals("Aprobó",
                            negocioCentroSalud.calcularResultadoFinal(1L));
    }
    @Test
    public void probarReporte(){
        List<CentroSalud> centros;
        centros = negocioCentroSalud.obtenerReporte();
        for(CentroSalud centroSalud: centros){
            System.out.println(centroSalud.toString());
        }
        Assert.assertNotNull(centros);
    }
    @Test
    public void probarReporteTotal(){
        List<CentroSaludDTO> resultados ;
        resultados = negocioCentroSalud.obtenerReporteResultados();
        for(CentroSaludDTO p:resultados){
            System.out.println(p.toString());
        }
        Assert.assertNotNull(resultados);
    }
}

package trabajoPractico;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PronosticoTest {

	  
    @Test
    public void testPuntos() {
        Persona persona1 = new Persona("juan",0);
        Persona persona2 = new Persona("Brenda",0);
        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        Equipo equipo3 = new Equipo();
        Equipo equipo4 = new Equipo();
        equipo1.setNombre("Argentina");
        equipo2.setNombre("Arabia Saudita");
        equipo3.setNombre("Catar");
        equipo4.setNombre("ecuador");
        Partido partido1 = new Partido(equipo1, equipo2, 2, 0);
       Partido partido2 = new Partido(equipo3, equipo4, 1, 1);
       Pronostico pronostico1 = new Pronostico(partido1,equipo1,ResultadoEnum.GANADOR,persona1);
       Pronostico pronostico2 = new Pronostico(partido2,equipo3,ResultadoEnum.EMPATE,persona1);
       Pronostico pronostico3 = new Pronostico(partido1,equipo1,ResultadoEnum.GANADOR,persona2);
       Pronostico pronostico4 = new Pronostico(partido2,equipo3,ResultadoEnum.EMPATE,persona2);
      
      List <Pronostico> pronosticos1 = new ArrayList<>();
    pronosticos1.add(pronostico1);
    pronosticos1.add(pronostico2);
    
    List <Pronostico> pronosticos2 = new ArrayList<>();
    pronosticos2.add(pronostico3);
    pronosticos2.add(pronostico4);
    
    Pronostico pronostico = new Pronostico();
    Pronostico prono = new Pronostico(); 
    pronostico.puntos(pronosticos1);
    int puntos1 = persona1.getPuntos();
    prono.puntos(pronosticos2);
    int puntos2 = persona2.getPuntos();

    // Verificar que el resultado sea el mismo
    assertEquals(puntos1, puntos2);
}
    
}
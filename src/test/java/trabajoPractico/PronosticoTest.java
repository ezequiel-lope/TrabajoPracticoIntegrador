package trabajoPractico;

import static org.junit.Assert.*;

import org.junit.Test;

public class PronosticoTest {

	  
    @Test
    public void testPuntos() {
        Persona persona = new Persona("juan",0);
        Equipo equipo1 = new Equipo();
        equipo1.setNombre("Argentina");
        Equipo equipo2 = new Equipo();
        equipo2.setNombre("Arabia Saudita");
        
        Partido partido = new Partido(equipo1, equipo2, 2, 0);
        
        partido.resultado();
     
        int aciertosEsperados = 1;
        
        Pronostico pronostico = new Pronostico(partido,equipo1,ResultadoEnum.GANADOR,persona);
        int aciertosObtenidos = pronostico.puntos(persona,"juan");
        
        assertEquals(aciertosEsperados, aciertosObtenidos);
    }
    
}
package trabajoPractico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
 

public class PronosticoApp {

	public static void main(String[] args) {
		
		List<String> archivoPartido = leerArchivo("src\\main\\java\\trabajoPractico\\resultados.csv");
		List<String> archivoPronostico = leerArchivo("src\\main\\java\\trabajoPractico\\pronostico.csv");
		List<Partido> partidos = cargarPartidos(archivoPartido);
		List<Pronostico> pronosticos = cargarPronosticos(archivoPronostico,partidos);
		Ronda ronda = new Ronda();
		ronda.setNumeroRonda(1);
		ronda.setPartidos(partidos);
		Persona persona1 =  pronosticos.get(0).getPersona();
		Persona persona2 =  pronosticos.get(4).getPersona();
		
		int totalAciertos=0;
	
		for (int i = 0; i < pronosticos.size()-4; i++) {
		    Pronostico pronostic = pronosticos.get(i);
		  totalAciertos = pronostic.puntos(persona1,persona1.getNombre());
		  
		  
		}
		
		System.out.println(persona1.getNombre()+ " " +persona1.getPuntos() + " punto/s" + " con " + totalAciertos + " aciertos" );
			
		
		
	   for (int i = pronosticos.size() - 1; i >= 4; i--) {
	    Pronostico pronostic = pronosticos.get(i);
	    totalAciertos = pronostic.puntos(persona2,persona2.getNombre());
	   
	   }

	    System.out.println(persona2.getNombre() + " " + persona2.getPuntos() + " punto/s" + " con " + totalAciertos + " aciertos");
		
}
		
		
	
 private static List<String> leerArchivo (String rutaArchivo) {
	        List<String> lineas = new ArrayList<>();
	       
	        try {
	            lineas = Files.readAllLines(Paths.get(rutaArchivo));
	        } catch (IOException e) {
	             }
	        return lineas;
	    }
	
 
 private static List<Partido> cargarPartidos(List<String> lineas) {
	 lineas.remove(0);
     List<Partido> partidos = new ArrayList<>();
     for (String linea : lineas) {
         String equipoN1 = linea.split(";")[1];
         int golesEquipo1 = Integer.parseInt(linea.split(";")[2]);
         int golesEquipo2 = Integer.parseInt(linea.split(";")[3]);
         String equipoN2 = linea.split(";")[4];
         
         
         Equipo equipo1 = new Equipo(equipoN1);
         Equipo equipo2 = new Equipo(equipoN2);
         Partido partido = new Partido(equipo1, equipo2,golesEquipo1,golesEquipo2);
         partidos.add(partido);
     }
     return partidos;
 }
 
 
 private static List<Pronostico> cargarPronosticos(List<String> lineas, List<Partido> partidos) {
     lineas.remove(0);
     List<Pronostico> pronosticos = new ArrayList<>();
     
     int indicePartido = 0;
     
    
     for (String linea : lineas) {
    	 
         String nombrePersona = linea.split(";")[0];    
         String gana = linea.split(";")[2];
         String empata = linea.split(";")[3];
         String pierde = linea.split(";")[4];
         
                 
         ResultadoEnum resultado = null;
         if (gana.endsWith("x")) {
             resultado = ResultadoEnum.GANADOR;
         } else if (empata.endsWith("x")) {
             resultado = ResultadoEnum.EMPATE;
         } else if (pierde.endsWith("x")) {
             resultado = ResultadoEnum.PERDEDOR;
         }
         
         Pronostico pronostico = new Pronostico();
         Persona persona = new Persona ();
         Partido partido = partidos.get(indicePartido);
         
         persona.setNombre(nombrePersona);
         pronostico.setResultado(resultado);
         pronostico.setPersona(persona);
         pronostico.setPartido(partido);
         pronostico.setEquipo(partidos.get(indicePartido).getEquipo1());
         
         pronosticos.add(pronostico);
         
         indicePartido++;
         if(indicePartido==4) {
    		 
    		 indicePartido=0;
    	 }
     }
     return pronosticos;
 }
 

 
}





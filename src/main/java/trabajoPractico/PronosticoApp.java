package trabajoPractico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


 

public class PronosticoApp {

	public static void main(String[] args) throws SQLException {
		
		 Connection conn = getConnection();
		 
		 ResultSet rs = executeQuery(conn, "SELECT p.id, "
		 		+ "	   e1.nombre AS equipo1, "
		 		+ "       pa.golesEquipo1,"
		 		+ "       pa.golesEquipo2,"
		 		+ "       e2.nombre AS equipo2,"
		 		+ "       ep.nombre AS equipoPronostico,"
		 		+ "       r.resultado , "
		 		+ "       pe.nombre\r\n"
		 		+ "FROM pronostico p\r\n"
		 		+ "INNER JOIN persona pe ON p.persona_id = pe.id\r\n"
		 		+ "INNER JOIN partido pa ON p.partido_id = pa.id\r\n"
		 		+ "INNER JOIN equipo e1 ON pa.equipo1 = e1.id\r\n"
		 		+ "INNER JOIN equipo e2 ON pa.equipo2 = e2.id\r\n"
		 		+ "INNER JOIN equipo ep ON pa.equipo1 = ep.id\r\n"
		 		+ "INNER JOIN resultado r ON p.resultado = r.id\r\n"
		 		+ "\r\n"
		 		+ "ORDER BY p.id;");
         while (rs.next()) {
         System.out.println(rs.getInt(1) + " " + rs.getString("equipo1") + " "+ rs.getInt(3) + " "+ rs.getInt(4)+ " " 
         + rs.getString("equipo2")  + " " + rs.getString("equipoPronostico") 
         + " " + rs.getString("resultado")+ " " + rs.getString("nombre"));
         }
         
		conn.close();
		
		
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
 
 // Método estático que establece la conexión con la base de datos
 public static Connection getConnection() throws SQLException {
     String url = "jdbc:mysql://localhost:3306/pronosticos";
     String user = "root";
     String password = "34290902";
     return DriverManager.getConnection(url, user, password);
 }
	  
 
 // Método estático que ejecuta una consulta y devuelve un ResultSet
 public static ResultSet executeQuery(Connection conn, String query) throws SQLException {
     Statement stmt = conn.createStatement();
     return stmt.executeQuery(query);
 }
 
}





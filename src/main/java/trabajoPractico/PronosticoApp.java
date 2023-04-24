package trabajoPractico;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


 

public class PronosticoApp {

	public static void main(String[] args) throws SQLException {
		
		List<String> configuracion = leerArchivo("src\\main\\java\\trabajoPractico\\configuracion.csv");
		 Connection conn = getConnection(configuracion);
		 
		 ResultSet rs = executeQuery(conn );
      
		List<String> archivoPartido = leerArchivo("src\\main\\java\\trabajoPractico\\resultados.csv");
		List<Partido> partidos = cargarPartidos(archivoPartido);
		List<Pronostico> pronosticos = cargarPronosticos(rs);
		List<Ronda> rondas = cargarRondas(partidos);
	    List<Pronostico> pronostico1 = pronosticos.subList(0,18);
	    List<Pronostico> pronostico2 = pronosticos.subList(18,36);
	    List<Pronostico> pronostico3 = pronosticos.subList(36,54);
	    
	    Pronostico pronostico = new Pronostico();
		pronostico.puntos(pronostico1);
		pronostico.puntos(pronostico2);
		pronostico.puntos(pronostico3);
		
		System.out.println();
	    conn.close();
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
	        String[] datosPartido = linea.split(";");

	        // Validar que la línea tenga 5 elementos
	        if (datosPartido.length != 5) {
	            System.out.println("Error: la línea " + linea + " no contiene 5 elementos.");
	            continue;  // Saltar a la siguiente iteración del ciclo
	        }
            String rondas = datosPartido[0];
	        String equipoN1 = datosPartido[1];
	        String golesE1 = datosPartido[2];
	        String golesE2 = datosPartido[3];
	        String equipoN2 = datosPartido[4];

	        // Validar que los goles de ambos equipos puedan ser parseados a enteros
	        int golesEquipo1, golesEquipo2,ronda;
	        
	        try {
	            golesEquipo1 = Integer.parseInt(golesE1);
	            golesEquipo2 = Integer.parseInt(golesE2);
	            ronda = Integer.parseInt(rondas);
	        } catch (NumberFormatException e) {
	            System.out.println("Error: los goles del partido " + linea + " no son enteros válidos.");
	            continue;  // Saltar a la siguiente iteración del ciclo
	        }

	        Equipo equipo1 = new Equipo(equipoN1);
	        Equipo equipo2 = new Equipo(equipoN2);
	        Partido partido = new Partido(equipo1, equipo2, golesEquipo1, golesEquipo2);
	        partidos.add(partido);
	    }
	    return partidos;
	}
 
 
 private static List<Pronostico> cargarPronosticos(ResultSet rs) throws SQLException {
     
     List<Pronostico> pronosticos = new ArrayList<>();
     while(rs.next()){
     
         String equipo1 = rs.getString("equipo1");
         String equipo2 = rs.getString("equipo2");
         int golesEquipo1 = rs.getInt(3);
         int golesEquipo2 = rs.getInt(4);
         ResultadoEnum resultado = ResultadoEnum.valueOf(rs.getString("resultado"));
         String nombrePersona = rs.getString("nombre");   
         
         Pronostico pronostico = new Pronostico();
         Equipo Equipo1 = new Equipo(equipo1);
         Equipo Equipo2 = new Equipo(equipo2);
         Partido partido = new Partido ();
         partido.setEquipo1(Equipo1);
         partido.setEquipo2(Equipo2);
         partido.setGolesEquipo1(golesEquipo1);
         partido.setGolesEquipo2(golesEquipo2);
         Persona persona = new Persona();
         persona.setNombre(nombrePersona);
         
         pronostico.setResultado(resultado);
         pronostico.setPersona(persona);
         pronostico.setPartido(partido);
         pronostico.setEquipo(Equipo1);
         
         pronosticos.add(pronostico);
         
     }
     return pronosticos;
 }
 
 // Método estático que establece la conexión con la base de datos
 private static Connection getConnection(List <String> config) throws SQLException {
	 config.remove(0);
Connection conexion = null;
	 
	 for (String linea : config) {
   String url = linea.split(";")[0];
    String user = linea.split(";")[1];
    String password = linea.split(";")[2];
 conexion= DriverManager.getConnection(url, user, password);
	 }
	return conexion;
 }
	  
 
 // Método estático que ejecuta una consulta y devuelve un ResultSet
 private static ResultSet executeQuery(Connection conn) throws SQLException {
     Statement stmt = conn.createStatement();
     return stmt.executeQuery("SELECT p.id, "
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
 }
 

 private static List<Ronda> cargarRondas(List<Partido> partidos) {
	    List<Ronda> rondas = new ArrayList<>();
	    Ronda ronda1 = new Ronda();
	    Ronda ronda2 = new Ronda();
	    Ronda ronda3 = new Ronda();
	    int contadorPartidos = 0; // contador de partidos agregados a las rondas

	    for (Partido partido : partidos) {
	        if (contadorPartidos < 6) {
	            ronda1.setNumeroRonda(1);
	            ronda1.setPartidos(partidos);
	        } else if (contadorPartidos < 12) {
	            ronda2.setNumeroRonda(2);
	            ronda2.setPartidos(partidos);
	        } else {
	            ronda3.setNumeroRonda(3);
	            ronda3.setPartidos(partidos);
	        }
	        contadorPartidos++;
	    }

	    rondas.add(ronda1);
	    rondas.add(ronda2);
	    rondas.add(ronda3);
	    return rondas;
	}
 
}





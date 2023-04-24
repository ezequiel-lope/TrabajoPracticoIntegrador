package trabajoPractico;

import java.util.List;

public class Pronostico {
	
private	Partido partido;
private	Equipo equipo;
private	ResultadoEnum resultado;
private Persona persona;


	
	

	
	public Pronostico() {
	super();
}

	


	public Pronostico(Partido partido, Equipo equipo, ResultadoEnum resultado, Persona persona) {
		super();
		this.partido = partido;
		this.equipo = equipo;
		this.resultado = resultado;
		this.persona = persona;
	}

	



	public Partido getPartido() {
		return partido;
	}




	public void setPartido(Partido partido) {
		this.partido = partido;
	}




	public Equipo getEquipo() {
		return equipo;
	}




	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}




	public ResultadoEnum getResultado() {
		return resultado;
	}




	public void setResultado(ResultadoEnum resultado) {
		this.resultado = resultado;
	}




	public Persona getPersona() {
		return persona;
	}




	public void setPersona(Persona persona) {
		this.persona = persona;
	}




	//METODO PARA SABER CUANTOS PUNTOS OBTUVO LA PERSONA
	public void puntos(List<Pronostico> pronosticos) {
		
		
		Persona persona = new Persona();
		int aciertos = 0;
	    int puntos = persona.getPuntos();
        int indice =0;
        
	    for (Pronostico pronostico : pronosticos) {
	    	
	        Partido partido = pronostico.getPartido();
	        ResultadoEnum resultadoPronostico = pronostico.getResultado();
	        persona = pronostico.getPersona();
            ResultadoEnum resultado = partido.resultado();

            if (resultado.equals(resultadoPronostico)) {
                puntos += 1;
                aciertos++;
                indice++; // se suma el acierto consecutivo
                
                
            if (indice == 6) { // si se completó una ronda
                    puntos += 3; // se suman los puntos extras
                    indice = 0; // se reinicia el contador
                }
                
            } 
            
            else {
                indice = 0; // si se pierde un acierto consecutivo, se reinicia el contador
            }
            
            if (aciertos == 18){
            	
            	puntos += 5;
            }
          
            persona.setPuntos(puntos);
        }

	   
	    
	   System.out.println(persona.getNombre() + " obtuvo " + puntos + " puntos, con " + aciertos + " aciertos");
	    }
	    
	

	@Override
	public String toString() {
		return "Pronostico [partido=" + partido + ", equipo=" + equipo + ", resultado=" + resultado + ", persona="
				+ persona + "]";
	}



	

}

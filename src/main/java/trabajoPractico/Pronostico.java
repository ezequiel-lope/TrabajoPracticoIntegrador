package trabajoPractico;

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
	public int puntos(Persona persona, String nombre) {
		int aciertos=0;
		persona.setNombre(nombre);
	    int puntos = persona.getPuntos();

	    if (partido.resultado() == resultado) {
	        puntos += 1; 
	        
	   }
	    persona.setPuntos(puntos);
	    aciertos = aciertos + persona.getPuntos();
		return aciertos;
	}		

	@Override
	public String toString() {
		return "Pronostico [partido=" + partido + ", equipo=" + equipo + ", resultado=" + resultado + ", persona="
				+ persona + "]";
	}



	

}

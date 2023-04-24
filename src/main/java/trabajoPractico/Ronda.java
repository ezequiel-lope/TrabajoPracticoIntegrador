package trabajoPractico;

import java.util.List;

public class Ronda {
	
private	int numeroRonda;
private	List<Partido> partidos;

	public Ronda() {
	}
	

	public Ronda(int numeroRonda, List<Partido> partidos) {
		this.numeroRonda = numeroRonda;
		this.partidos = partidos;
	}



	public int getNumeroRonda() {
		return numeroRonda;
	}

	public void setNumeroRonda(int numeroRonda) {
		this.numeroRonda = numeroRonda;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partido) {
		this.partidos = partido;
	}
	
	
	public void calcularPuntos(Partido partido) {
	    int puntos = 0;
	    if (partido.getGolesEquipo1() > partido.getGolesEquipo2()) {
	        puntos+=3;
	    } else if (partido.getGolesEquipo1() < partido.getGolesEquipo2()) {
	        puntos=0;
	    } else {
	        puntos=1;
	    }
	    
	    System.out.println(partido.getEquipo1().getNombre()+ " obtuvo "+puntos+ " puntos en esta ronda");
	    //return puntos;
	}


	@Override
	public String toString() {
		return "Ronda [numeroRonda=" + numeroRonda + ", partidos=" + partidos + "]";
	}
	
	

}

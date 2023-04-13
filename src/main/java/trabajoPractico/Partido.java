package trabajoPractico;

public class Partido {
private	Equipo equipo1;
private	Equipo equipo2;
private	int golesEquipo1;
private	int golesEquipo2;



	public Partido() {
	}

	public Partido(Equipo equipos1, Equipo equipos2, int golesEquipo1, int golesEquipo2) {
		this.equipo1 = equipos1;
		this.equipo2 = equipos2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo) {
		this.equipo1 = equipo;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}
	
	//METODO PARA DETERMINAR EL RESULTADO DEL PARTIDO
	public ResultadoEnum resultado() {
	
		ResultadoEnum resultado = null;
		
		if(golesEquipo1>golesEquipo2) {
		
            resultado=ResultadoEnum.GANADOR;
		}
			
		else if(golesEquipo1<golesEquipo2) {
			
			resultado=ResultadoEnum.PERDEDOR;
		}
			
		
		else if(golesEquipo1==golesEquipo2) {
			
	
			resultado=ResultadoEnum.EMPATE;
			
		}
		
		
		return resultado;
			
			}

	@Override
	public String toString() {
		return "Partido [equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", golesEquipo1=" + golesEquipo1
				+ ", golesEquipo2=" + golesEquipo2 + "]";
	}
		
	
	
	}





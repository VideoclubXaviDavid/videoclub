package Clases;

public class InfoAlmacenamientoPelicula {
	private double tamaño;
	private String discoDuro, calidad;
	
	public InfoAlmacenamientoPelicula(double tamaño, String discoDuro, String calidad) {
		super();
		this.tamaño = tamaño;
		this.discoDuro = discoDuro;
		this.calidad = calidad;
	}

	public double getTamaño() {
		return tamaño;
	}

	public void setTamaño(double tamaño) {
		this.tamaño = tamaño;
	}

	public String getDiscoDuro() {
		return discoDuro;
	}

	public void setDiscoDuro(String discoDuro) {
		this.discoDuro = discoDuro;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	
	
}

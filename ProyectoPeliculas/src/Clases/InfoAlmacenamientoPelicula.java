package Clases;

public class InfoAlmacenamientoPelicula {
	private double tama�o;
	private String discoDuro, calidad;
	
	public InfoAlmacenamientoPelicula(double tama�o, String discoDuro, String calidad) {
		super();
		this.tama�o = tama�o;
		this.discoDuro = discoDuro;
		this.calidad = calidad;
	}

	public double getTama�o() {
		return tama�o;
	}

	public void setTama�o(double tama�o) {
		this.tama�o = tama�o;
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

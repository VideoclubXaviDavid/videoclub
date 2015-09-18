package Clases;

import java.awt.Image;
import java.util.List;

public class Pelicula {
	private int codigoPelicula;
	private String nombre, descripcion;
	private List<Actor> actores;
	private Image imagen;
	private int año;
	private double valoracion;
	private InfoAlmacenamientoPelicula infoAlmacenamientoPelicula;

	public Pelicula(int codigoPelicula, String nombre, String descripcion,
			List<Actor> actores, Image imagen, int año, double valoracion,
			InfoAlmacenamientoPelicula infoAlmacenamientoPelicula) {
		super();
		this.codigoPelicula = codigoPelicula;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.actores = actores;
		this.imagen = imagen;
		this.año = año;
		this.valoracion = valoracion;
		this.infoAlmacenamientoPelicula = infoAlmacenamientoPelicula;
	}

	public int getCodigoPelicula() {
		return codigoPelicula;
	}
	
	public void setCodigoPelicula(int codigoPelicula) {
		this.codigoPelicula = codigoPelicula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Actor> getActores() {
		return actores;
	}
	
	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	
	public Image getImagen() {
		return imagen;
	}
	
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	public int getAño() {
		return año;
	}
	
	public void setAño(int año) {
		this.año = año;
	}
	
	public double getValoracion() {
		return valoracion;
	}
	
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	
	public InfoAlmacenamientoPelicula getInfoAlmacenamientoPelicula() {
		return infoAlmacenamientoPelicula;
	}
	
	public void setInfoAlmacenamientoPelicula(
			InfoAlmacenamientoPelicula infoAlmacenamientoPelicula) {
		this.infoAlmacenamientoPelicula = infoAlmacenamientoPelicula;
	}
}

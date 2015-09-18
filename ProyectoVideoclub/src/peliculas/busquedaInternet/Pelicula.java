package peliculas.busquedaInternet;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pelicula {
	
	private String tituloPelicula;
	private String descripcionPelicula;
	private int añoPelicula;
	private String [] nombreDirectores;
	private String [] actores;
	private String urlPortada;
	private String urlTrailer;
	private double puntuacion;
	
	public Pelicula() {
		this.tituloPelicula = null;
		this.descripcionPelicula = null;
		this.añoPelicula = -1;
		this.nombreDirectores = null;
		this.actores = null;
		this.urlPortada = null;
		this.urlTrailer = null;
		this.puntuacion = -1.0;
	}
	
	public Pelicula( String tituloPelicula, String descripcionPelicula, int añoPelicula, 
					String [] nombreDirectores, String [] actores, String urlPortada, 
					String urlTrailer, double puntuacion ) {
		this.tituloPelicula = tituloPelicula;
		this.descripcionPelicula = descripcionPelicula;
		this.añoPelicula = añoPelicula;
		this.nombreDirectores = nombreDirectores;
		this.actores = actores;
		this.urlPortada = urlPortada;
		this.urlTrailer = urlTrailer;
		this.puntuacion = puntuacion;
	}
	
	public void muestraPortada() {
		
		Image image = null;
		try {
		    URL url = new URL( this.urlPortada );
		    URLConnection urlc = url.openConnection();
			urlc.addRequestProperty( "user-agent" , "Firefox" );
			URL url2 = urlc.getURL();
			
		    image = ImageIO.read(url2);
		} catch (IOException e) {
		}
		
		if( image != null ) {
			// Use a label to display the image
			JFrame frame = new JFrame();

			JLabel lblimage = new JLabel(new ImageIcon(image));
			
			JPanel mainPanel = new JPanel(new BorderLayout());
			mainPanel.add(lblimage);
			// add more components here
			frame.add(mainPanel);
			
			frame.getContentPane().add(lblimage, BorderLayout.CENTER);
			frame.setSize(300, 400);
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			frame.setVisible(true);
		}
		
	}

	
	@Override
	public String toString() {
		
		String str = "";
		str = "Título película: " + tituloPelicula + "\n"
			+ "Descripción: " + descripcionPelicula + "\n"
			+ "Año: " + añoPelicula + "\n"
			+ "Directores: " + Arrays.toString( nombreDirectores ) + "\n"
			+ "URL portada: " + urlPortada + "\n"
			+ "URL trailer: " + urlTrailer + "\n"
			+ "Puntuación: " + puntuacion + "\n"
			+ "Actores: " + Arrays.toString( actores ) ;
		
		muestraPortada();
		
		return str;
		
	}
	
	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public String getDescripcionPelicula() {
		return descripcionPelicula;
	}

	public void setDescripcionPelicula(String descripcionPelicula) {
		this.descripcionPelicula = descripcionPelicula;
	}

	public int getAñoPelicula() {
		return añoPelicula;
	}

	public void setAñoPelicula(int añoPelicula) {
		this.añoPelicula = añoPelicula;
	}

	public String[] getNombreDirectores() {
		return nombreDirectores;
	}

	public void setNombreDirectores(String[] nombreDirectores) {
		this.nombreDirectores = nombreDirectores;
	}

	public String [] getActores() {
		return actores;
	}

	public void setActores(String [] actores) {
		this.actores = actores;
	}

	public String getUrlPortada() {
		return urlPortada;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada;
	}

	public String getUrlTrailer() {
		return urlTrailer;
	}

	public void setUrlTrailer(String urlTrailer) {
		this.urlTrailer = urlTrailer;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}

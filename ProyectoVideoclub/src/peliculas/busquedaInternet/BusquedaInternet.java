package peliculas.busquedaInternet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class BusquedaInternet {
	
	private static final String paginaBusquedaDatosGenerales = "http://www.myapifilms.com/imdb";
	private static final String paginaBusquedaDescripcion = "http://www.myapifilms.com/tmdb/searchMovie";
	private String paginaDatosGenerales;
	private String paginaDescripcion;
	private String paramsDatosGenerales = "&format=XML&actors=S&trailer=1";
	private String paramsDescripcion = "&format=XML&language=es";
	private String tituloPelicula;
	private String xmlDatosGenerales;
	private String xmlDescripcion;
	
	public BusquedaInternet( String tituloPelicula ) {
		this.tituloPelicula = tituloPelicula;
		String nuevoTitulo = generaTituloBusqueda( tituloPelicula );
		generaPaginaDescripcion( nuevoTitulo );
	}
	
	public Pelicula buscaPelicula() {
		
		String [] nombresPeliculas = ExtractorDatosXML.extraerEtiquetaRepetida( "results class=\"array\"" , 
																	"e", "title type=\"string\"", this.xmlDescripcion );

		String titulo = eligePelicula( nombresPeliculas );

		
		return buscaDatosPelicula( titulo );
		
	}
	
	private String eligePelicula( String [] nombresPeliculas ) {
		
		String titulo = null;
		
		for( int i = 0; i < nombresPeliculas.length; i++ ) {
			System.out.println( i + "- " + nombresPeliculas[i] );
		}

		System.out.println( "-------------------------------" );
		
		Scanner entrada = new Scanner( System.in );
		System.out.print( "Elige una película: " );
		titulo = nombresPeliculas[ Integer.parseInt( entrada.next() ) ];
		
		return titulo;
	}
	
	private Pelicula buscaDatosPelicula( String titulo ) {
		Pelicula pelicula = new Pelicula();
		
		generaPaginas( titulo );
		
		int año = Integer.parseInt( ExtractorDatosXML.extraerEtiquetaUnica( "year" , this.xmlDatosGenerales ) );
		String descripcion = ExtractorDatosXML.extraerEtiquetaUnica( "overview type=\"string\"" ,  this.xmlDescripcion );
		String urlPortada = ExtractorDatosXML.extraerEtiquetaUnica( "urlPoster" ,  this.xmlDatosGenerales );
		String urlTrailer = ExtractorDatosXML.extraerEtiquetaUnica( "videoURL" , this.xmlDatosGenerales );
		double puntuacion = Double.parseDouble( ExtractorDatosXML.extraerEtiquetaUnica( "rating" , this.xmlDatosGenerales ) );
		String [] nombresDirectores = ExtractorDatosXML.extraerEtiquetaRepetida( "directors" , "director", 
																				"name",  this.xmlDatosGenerales );
		String [] nombresActores = ExtractorDatosXML.extraerEtiquetaRepetida( "actors", "actor", "actorName", this.xmlDatosGenerales );
		
		pelicula.setTituloPelicula( titulo );
		pelicula.setAñoPelicula( año );
		pelicula.setDescripcionPelicula( descripcion );
		pelicula.setUrlPortada( urlPortada );
		pelicula.setUrlTrailer( urlTrailer );
		pelicula.setPuntuacion( puntuacion );
		pelicula.setNombreDirectores( nombresDirectores );
		pelicula.setActores( nombresActores );
		
		return pelicula;
	}
	
	private String getXML( String pagina ) {
		
		BufferedReader in = null;
		String res = "";
		
		try {
			URL url = new URL( pagina );
			
			URLConnection urlc = url.openConnection();
			urlc.addRequestProperty( "user-agent" , "Firefox" );
			
			in = new BufferedReader( new InputStreamReader( urlc.getInputStream() ) );
			String inputLine;
			
			
			while( ( inputLine = in.readLine() ) != null ) {
				res += inputLine;
			}
			
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			}
			catch( IOException e ) {
				e.printStackTrace();
			}
		}
		
		return res;
		
	}
	
	private void generaPaginas( String titulo ) {
		String nuevoTitulo = generaTituloBusqueda( titulo );
		generaPaginaDatosGenerales( nuevoTitulo );
		generaPaginaDescripcion( nuevoTitulo );
	}
	
	private String generaTituloBusqueda( String titulo ) {
		String [] partesTitulo = titulo.split( " " );
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < partesTitulo.length; i++ ) {
			sb.append( partesTitulo[i] );
			if( i < partesTitulo.length - 1 ) {
				sb.append( "+" );
			}
		}
		String nuevoTitulo = sb.toString();
		return nuevoTitulo;
	}
	
	private void generaPaginaDatosGenerales( String titulo ) {
		this.paginaDatosGenerales = paginaBusquedaDatosGenerales + "?title=" + titulo + paramsDatosGenerales;
		this.xmlDatosGenerales = getXML( this.paginaDatosGenerales );
	}
	
	private void generaPaginaDescripcion( String titulo ) {
		this.paginaDescripcion = paginaBusquedaDescripcion +  "?movieName=" + titulo + paramsDescripcion;
		this.xmlDescripcion = getXML( this.paginaDescripcion );
	}
	
	
	public String getPaginaDatosGenerales() {
		return this.paginaDatosGenerales;
	}
	
	public String getPaginaDescripcion() {
		return this.paginaDescripcion;
	}
	
	public String getTituloPelicula() {
		return this.tituloPelicula;
	}
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner( System.in );
		System.out.print( "Introduce el título de la película: " );
		String tituloPelicula = entrada.nextLine();
		
		BusquedaInternet bi = new BusquedaInternet( tituloPelicula );
		Pelicula pelicula = bi.buscaPelicula();
		
		System.out.println( pelicula );
		
		
	}
	
	

}

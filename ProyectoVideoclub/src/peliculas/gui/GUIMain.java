package peliculas.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIMain {
	
	public static void GUI() {
		JFrame ventana = new JFrame( "Ventana" );
		Container contenedor = ventana.getContentPane();
		contenedor.add( new JButton( "Hola" ) );
		ventana.setVisible( true );
		ventana.pack();
	}
	
	public static void main(String[] args) {
		
//		SwingUtilities.invokeLater( new Runnable() {
//
//			@Override
//			public void run() {
//				//GUI();
//			}
//			
//		});
		
//		
		
		Scanner entrada = new Scanner( System.in );
		System.out.print( "Introduce el titulo de la película: " );
		String titulo = entrada.nextLine();
				
		String paginaDatosGenerales = "http://www.myapifilms.com/imdb?title=" + titulo + "&format=XML&actors=S&trailer=1";
		String paginaDescripcion = "http://www.myapifilms.com/tmdb/searchMovie?movieName=" + titulo + "&format=XML&language=es";
		
		String datosGeneralesXml = search( paginaDatosGenerales );
		String descripcionXml = search( paginaDescripcion );
		
		System.out.println( datosGeneralesXml );
		System.out.println( "-----------------------" );
		System.out.println( descripcionXml );
		System.out.println( "....................." );
		extraeDatosGenerales( datosGeneralesXml );
		extraeDescripcion( descripcionXml );
		
	}
	
	public static String search( String page ) {
		
		BufferedReader in = null;
		String res = "";
		
		try {
			URL url = new URL( page );
			
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
	
	public static String[] extraeDatosGenerales( String xmlFormatString ) {
		
		String repRE = "^.*<directors>";
		String newString = xmlFormatString.replaceAll( repRE , "" );
		repRE = "</directors>.*$";
		newString = newString.replaceAll( repRE , "" );
		System.out.println( newString );
		
		System.out.println( "Directores: " );
		String [] directores = newString.split( "</director>" );
		String [] nombreDirectores = new String[ directores.length ];
		int i = 0;
		for( String director: directores ) {
			repRE = "^.*<name>";
			String nombre = directores[i];
			nombre = nombre.replaceAll( repRE, "" );
			repRE = "</name>.*$";
			nombre = nombre.replaceAll( repRE , "" );
			nombreDirectores[i] = nombre;
			System.out.println( nombreDirectores[i] );
			i++;
		}
		
		
		repRE = "^.*<year>";
		String año = xmlFormatString.replaceAll( repRE, "" );
		repRE = "</year>.*$";
		año = año.replaceAll( repRE, "" );
		
		System.out.println( "Año: " + año );
		
		repRE = "^.*<urlPoster>";
		String urlFoto = xmlFormatString.replaceAll( repRE, "" );
		repRE = "</urlPoster>.*$";
		urlFoto = urlFoto.replaceAll( repRE, "" );
		
		System.out.println( "URL portada: " + urlFoto );
		
		repRE = "^.*<videoURL>";
		String urlVideo = xmlFormatString.replaceAll( repRE, "" );
		repRE = "</videoURL>.*$";
		urlVideo = urlVideo.replaceAll( repRE, "" );
		
		System.out.println( "URL trailer: " + urlVideo );
		
		repRE = "^.*<rating>";
		String rating = xmlFormatString.replaceAll( repRE, "" );
		repRE = "</rating>.*$";
		rating = rating.replaceAll( repRE, "" );
		
		System.out.println( "Puntuación: " + rating );
		
		Image image = null;
		try {
		    URL url = new URL( urlFoto );
		    URLConnection urlc = url.openConnection();
			urlc.addRequestProperty( "user-agent" , "Firefox" );
			URL url2 = urlc.getURL();
			
		    image = ImageIO.read(url2);
		} catch (IOException e) {
		}

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
				
		return null;
	}
	
	public static String[] extraeDescripcion( String xmlFormatString ) {
		
		String repRE = "^.*<overview type=\"string\">";
		String newString = xmlFormatString.replaceAll( repRE , "" );
		repRE = "</overview>.*$";
		newString = newString.replaceAll( repRE , "" );
		System.out.println( "Descripción: " + newString );
		
		repRE = "^.*<results class=\"array\">";
		String s = xmlFormatString.replaceAll( repRE , "" );
		repRE = "</results>.*$";
		s = s.replaceAll( repRE , "" );
		
		System.out.println( "Peliculas: " );
		String [] peliculas = s.split( "</e>" );
		String [] nombrePeliculas = new String[ peliculas.length ];
		int i = 0;
		for( String pelicula: peliculas ) {
			repRE = "^.*<title type=\"string\">";
			String nombre = peliculas[i];
			nombre = nombre.replaceAll( repRE, "" );
			repRE = "</title>.*$";
			nombre = nombre.replaceAll( repRE , "" );
			nombrePeliculas[i] = nombre;
			System.out.println( nombrePeliculas[i] );
			i++;
		}
		
		return null;
	}

}

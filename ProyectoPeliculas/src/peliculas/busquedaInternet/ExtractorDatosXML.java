package peliculas.busquedaInternet;

public class ExtractorDatosXML {
	
	public static String extraerEtiquetaUnica( String etiqueta, String xml ) {
		
		String repRE = "^.*<" + etiqueta + ">";
		String newString = xml.replaceAll( repRE , "" );
		String [] valoresEtiqueta = etiqueta.split( " " );
		repRE = "</" + valoresEtiqueta[0] + ">.*$";
		newString = newString.replaceAll( repRE , "" );
		
		return newString;
		
	}
	
	public static String [] extraerEtiquetaRepetida( String etiquetaGlobal, String etiquetaEspecifica, String etiquetaDato, String xml ) {
		
		String repRE = "^.*<" + etiquetaGlobal + ">";
		String newString = xml.replaceAll( repRE , "" );
		String [] valoresEtiquetaGlobal = etiquetaGlobal.split( " " );
		repRE = "</" + valoresEtiquetaGlobal[0] + ">.*$";
		newString = newString.replaceAll( repRE , "" );
		
		String [] datos = newString.split( "</" + etiquetaEspecifica + ">" );
		String [] datosEspecificos = new String[ datos.length ];
		
		for( int i = 0; i < datos.length; i++ ) {
			repRE = "^.*<" + etiquetaDato + ">";
			String datoEspecifico = datos[i];
			datoEspecifico = datoEspecifico.replaceAll( repRE, "" );
			String [] valoresEtiquetaDato = etiquetaDato.split( " " );
			repRE = "</" + valoresEtiquetaDato[0] + ">.*$";
			datoEspecifico = datoEspecifico.replaceAll( repRE , "" );
			datosEspecificos[i] = datoEspecifico;
		}
		
		return datosEspecificos;
		
	}

}

package peliculas.conexionBd;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionManager {
	
	private final static Logger Log = Logger.getLogger(ConnectionManager.class.getName()); 
	
	static String url = "jdbc:postgresql://db-aules.uji.es/ei102714ggl";
	static String driverName= "org.postgresql.Driver";
	static Connection connection=null;
	final static String JDBC_PROPERTIES = "./jdbc.properties";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (connection != null && !connection.isClosed())
			return connection;
		Class.forName(driverName);
 		Log.fine("Driver de PostrgreSQL JDBC registrado");
		
 		Properties props = new Properties();
 		ClassLoader loader = Thread.currentThread().getContextClassLoader();           
 		InputStream stream = loader.getResourceAsStream(JDBC_PROPERTIES);
 		if (stream == null)
 			Log.severe("Fichero " + JDBC_PROPERTIES + " no encontrado");
 		else {
 			try {
 				props.load(stream);
 			}
 			catch(IOException e) {
 	 			Log.severe("No se puede leer el fichero" + JDBC_PROPERTIES + " " + e.getMessage());
 	 			e.printStackTrace();
 			}
 		}
		connection = DriverManager.getConnection(url, props);	 
		
		if (connection != null) {
			Log.fine("Conectado correctamente.");
		} else { 
			Log.severe("La conexión ha fallado.!");
		}
		return connection;
	}

}

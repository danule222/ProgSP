package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controllers.Config;

/**
 * Clase abstracta que controla la conexión
 * con la base de datos.
 * @author Daniel Ramírez Morilla
 */
abstract class AbstractDAO {

	/** Objeto de conexión. */
	static protected Connection con;
	
	/** Establece la conexión con la base de datos. 
	 * @throws ClassNotFoundException 
	 * @throws SQLException */
	public static void establecerConexion() 
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection
				(Config.getStringConexion(),
						Config.getUsuarioBDD(), 
						Config.getContrasennaBDD());
	}
	
}

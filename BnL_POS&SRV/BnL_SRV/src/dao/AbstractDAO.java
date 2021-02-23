package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import common.Constant;

/**
 * Clase abstracta que controla la conexión
 * con la base de datos.
 * @author Daniel Ramírez Morilla
 */
abstract class AbstractDAO {

	/** Objeto de conexión. */
	static protected Connection con;
	
	/** Establece la conexión con la base de datos. */
	public static void establecerConexion() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                con = DriverManager.getConnection
                		(Constant.CONEXION, Constant.USER, Constant.PASSWORD);
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
}

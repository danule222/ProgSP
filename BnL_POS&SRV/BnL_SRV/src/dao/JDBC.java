package dao;

import java.sql.SQLException;

/**
 * Clase que controla la configuración
 * de JDBC.
 * @author Daniel Ramírez Morilla
 */
public class JDBC extends AbstractDAO {

	/**
	 * Establece la función de AutoCommit de JDBC.
	 * @param autoCommit 
	 * <ul>
	 * <li><b>True: </b>Activa la función de AutoCommit.</li>
	 * <li><b>False: </b>Desactiva la función de AutoCommit.</li>
	 * </ul>
	 * @throws SQLException
	 */
	public static void setAutocommit(boolean autoCommit)
			throws SQLException {
		con.setAutoCommit(autoCommit);
	}
	
	/**
	 * Confirma los cambios realizados en la base de datos.
	 * @throws SQLException
	 */
	public static void commit()
			throws SQLException {
		con.commit();
	}
	
	/**
	 * Cancela los cambios realizados en la base de datos.
	 * @throws SQLException
	 */
	public static void rollback()
			throws SQLException {
		con.rollback();
	}
	
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import common.Constant;

public abstract class AbstractDAO {

	protected Connection con;
	public static boolean error = false;
	
	public AbstractDAO() {
		EstablecerConexion();
	}
	
	private void EstablecerConexion() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                this.con = DriverManager.getConnection(Constant.CONEXION, Constant.USER, Constant.PASSWORD);
            } catch (SQLException e) {
            	e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
	
}

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Empleado;

/**
 * Clase de acceso a los datos relacionados
 * con los empleados.
 * @author Daniel Ramírez Morilla
 */
public class EmpleadoDAO extends AbstractDAO {
	
	public EmpleadoDAO() {
		super();
	}
	
	/**
	 * Obtiene un objeto Empleado a partir
	 * de su número privado.
	 * @param Numero_Privado Número privado del
	 * empleado.
	 * @return Objeto Empleado.
	 * @throws SQLException
	 */
	public Empleado getEmpleadoByNumeroPrivado(int Numero_Privado)
			throws SQLException {
        String sql = "SELECT * from EMPLEADOS "
        		   + "WHERE Numero_Privado = ?;";
        
        Empleado e = new Empleado();
        
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, Numero_Privado);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            e.setDNI(rs.getString("DNI"));
            e.setNombre(rs.getString("Nombre"));
            e.setApellidos(rs.getString("Apellidos"));
            e.setFecha_Nacimiento(rs.getDate("Fecha_Nacimiento"));
            e.setVivienda(rs.getString("Vivienda"));
            e.setSalario(rs.getDouble("Salario"));
            e.setFecha_Contratacion(rs.getDate("Fecha_Contratacion"));
            e.setPuesto(rs.getString("Puesto"));
            e.setUltima_Sesion(rs.getDate("Ultima_Sesion"));
            e.setID_Tienda(rs.getInt("ID_Tienda"));
        }

        return e;
	}

}

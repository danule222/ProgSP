package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Empleado;

public class EmpleadoDAO extends AbstractDAO {
	
	public EmpleadoDAO() {
		super();
	}
	
	public Empleado getEmpleadoByNumeroPrivado(int Numero_Privado)
			throws SQLException {
		Statement st;
        Empleado e = new Empleado();

        st = this.con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from EMPLEADOS WHERE Numero_Privado = "
        		+ Numero_Privado + ";");
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

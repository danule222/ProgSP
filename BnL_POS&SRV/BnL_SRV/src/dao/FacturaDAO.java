package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase de acceso a los datos relacionados
 * con la facturacion.
 * @author Daniel Ramírez Morilla
 *
 */
public class FacturaDAO extends AbstractDAO {
	
	public FacturaDAO() {
		super();
	}
	
	/**
	 * Crea una factura asignada al empleado
	 * que la está creando.
	 * @param DNI_Empleado DNI del empleado
	 * que está generando la factura.
	 * @return ID de la factura generada.
	 * @throws SQLException
	 */
	public int crearFactura(String DNI_Empleado) 
			throws SQLException {
		String sql = "INSERT INTO FACTURAS "
				   + "(Fecha_Facturacion, DNI_Empleado) "
				   + "VALUES(?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
		ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
		ps.setString(2, DNI_Empleado);
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		
		return rs.getInt(1);
	}
	
	/**
	 * Añade un elemento a la factura indicada.
	 * @param ID_Factura ID de la factura a la que
	 * se le va a añadir el elemento.
	 * @param ID_Producto ID del producto a añadir.
	 * @param cantidad Cantidad del producto que
	 * se va a facturar.
	 * @throws SQLException
	 */
	public void annadirElementoAFactura(int ID_Factura, 
			int ID_Producto, int cantidad) 
			throws SQLException {
		String sql = "INSERT INTO DETALLES_FACTURA "
				+ "(ID_Factura, ID_Producto, Cantidad) "
				+ "VALUES (?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ID_Factura);
		ps.setInt(2, ID_Producto);
		ps.setInt(3, cantidad);
		ps.executeUpdate();
	}
	
	/**
	 * Obtiene la caja del empleado indicado del
	 * día en el que se realiza la consulta.
	 * @param DNI_Empleado DNI del empleado.
	 * @return Beneficios en la caja del empleado indicado
	 * el día que se realiza la consulta.
	 * @throws SQLException
	 */
	public double cajaDelDia(String DNI_Empleado)
			throws SQLException {
		String sql = "SELECT SUM((Precio_Venta - Precio_Proveedor) "
				   	        + "* Cantidad) "
				   + "FROM DETALLES_FACTURA "
				   + "INNER JOIN PRODUCTOS "
				   			+ "ON DETALLES_FACTURA.ID_PRODUCTO "
				   			+ "= PRODUCTOS.ID "
				   + "WHERE DETALLES_FACTURA.ID_Factura IN (SELECT ID "
				   										 + "FROM FACTURAS "
				   										 + "WHERE Fecha_Facturacion = ? "
				   										 + "AND DNI_Empleado = ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, Date.valueOf(LocalDate.now()));
		ps.setString(2, DNI_Empleado);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getDouble(1);
	}

}

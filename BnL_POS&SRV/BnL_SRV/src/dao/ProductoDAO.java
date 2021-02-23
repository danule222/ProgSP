package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.Mail;

/**
 * Clase de acceso a los datos relacionados
 * con los productos.
 * @author Daniel Ramírez Morilla
 */
public class ProductoDAO extends AbstractDAO {

	public ProductoDAO() {
		super();
	}
	
	/**
	 * Realiza todas las comprobaciones y cambios
	 * en la base de datos para proceder a vender
	 * los diferentes productos. Si se da el caso
	 * de que se intenta vender un producto dejándolo
	 * sin stock, se lanzará una excepción que
	 * anulará los cambios hechos en la base de datos
	 * hasta la última facturación correcta.
	 * @param ID_Tienda ID de la tienda donde se está
	 * realizando la venta.
	 * @param ID_Producto ID del producto que se está
	 * vendiendo.
	 * @param cantidad Cantidad del producto que se
	 * está vendiendo.
	 * @throws Exception Si la cantidad vendida de un
	 * producto lo deja con un stock igual o inferior
	 * a 0, saltará esta excepción.
	 */
	public void venderProducto(int ID_Tienda, int ID_Producto, int cantidad)
			throws Exception {		
		String sql = "UPDATE ALMACENAMIENTO "
				   + "SET Stock = Stock - ? "
				   + "WHERE ID_Producto = ? "
				   + "AND ID_Almacen = ?";
		
		if ((getStock(ID_Tienda, ID_Producto) - cantidad) <= 0) {
			Mail.faltaStock(ID_Producto);
			throw new Exception("Falta de Stock en el producto con el ID "
					+ ID_Producto);
		} else {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cantidad);
			ps.setInt(2, ID_Producto);
			ps.setInt(3, getID_Almacen(ID_Tienda));
			ps.executeUpdate();
		}
	}
	
	/**
	 * Obtiene el stock disponible de un producto en
	 * el almacén de una tienda.
	 * @param ID_Tienda ID de la tienda a la que está
	 * vinculada el almacén.
	 * @param ID_Producto ID del producto a comprobar.
	 * @return Stock del producto.
	 * @throws SQLException
	 */
	public int getStock(int ID_Tienda, int ID_Producto)
			throws SQLException {
		String sql = "SELECT Stock FROM ALMACENAMIENTO "
				   + "WHERE ID_Producto = ? "
				   + "AND ID_Almacen = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ID_Tienda);
		ps.setInt(2, ID_Producto);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}
	
	/**
	 * Obtiene el ID del almacén a partir del ID
	 * de la tienda al que está vinculado.
	 * @param ID_Tienda ID de la tienda.
	 * @return ID del almacén.
	 * @throws SQLException
	 */
	public int getID_Almacen(int ID_Tienda)
			throws SQLException {
		String sql = "SELECT ID_Almacen FROM TIENDAS "
				   + "WHERE ID = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ID_Tienda);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}
	
}
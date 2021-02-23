package models;

/**
 * Clase modelo de Producto.
 * @author Daniel Ramírez Morilla
 */
public class Producto {

	private int ID;
	private String Nombre;
	private String Marca;
	private String Categoria;
	private String Descripcion;
	private double Precio_Venta;
	private double Precio_Proveedor;
	
	public Producto() {
		super();
	}
	
	public Producto(int iD, String nombre, String marca, String categoria, 
			String descripcion, double precio_Venta,
			double precio_Proveedor) {
		super();
		ID = iD;
		Nombre = nombre;
		Marca = marca;
		Categoria = categoria;
		Descripcion = descripcion;
		Precio_Venta = precio_Venta;
		Precio_Proveedor = precio_Proveedor;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public String getCategoria() {
		return Categoria;
	}
	public void setCategoria(String categoria) {
		Categoria = categoria;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public double getPrecio_Venta() {
		return Precio_Venta;
	}
	public void setPrecio_Venta(double precio_Venta) {
		Precio_Venta = precio_Venta;
	}
	public double getPrecio_Proveedor() {
		return Precio_Proveedor;
	}
	public void setPrecio_Proveedor(double precio_Proveedor) {
		Precio_Proveedor = precio_Proveedor;
	}
	
}

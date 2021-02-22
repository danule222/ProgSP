package models;

import java.sql.Date;

public class Empleado {

	private String DNI;
	private int Numero_Privado;
	private String Nombre;
	private String Apellidos;
	private Date Fecha_Nacimiento;
	private String Vivienda;
	private double Salario;
	private Date Fecha_Contratacion;
	private String Puesto;
	private Date Ultima_Sesion;
	private int ID_Tienda;
	
	public Empleado() {
		super();
	}
	
	public Empleado(String dNI, int numero_Privado, String nombre, String apellidos, Date fecha_Nacimiento,
			String vivienda, double salario, Date fecha_Contratacion, String puesto, Date ultima_Sesion,
			int iD_Tienda) {
		super();
		DNI = dNI;
		Numero_Privado = numero_Privado;
		Nombre = nombre;
		Apellidos = apellidos;
		Fecha_Nacimiento = fecha_Nacimiento;
		Vivienda = vivienda;
		Salario = salario;
		Fecha_Contratacion = fecha_Contratacion;
		Puesto = puesto;
		Ultima_Sesion = ultima_Sesion;
		ID_Tienda = iD_Tienda;
	}
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public int getNumero_Privado() {
		return Numero_Privado;
	}
	public void setNumero_Privado(int numero_Privado) {
		Numero_Privado = numero_Privado;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public Date getFecha_Nacimiento() {
		return Fecha_Nacimiento;
	}
	public void setFecha_Nacimiento(Date fecha_Nacimiento) {
		Fecha_Nacimiento = fecha_Nacimiento;
	}
	public String getVivienda() {
		return Vivienda;
	}
	public void setVivienda(String vivienda) {
		Vivienda = vivienda;
	}
	public double getSalario() {
		return Salario;
	}
	public void setSalario(double salario) {
		Salario = salario;
	}
	public Date getFecha_Contratacion() {
		return Fecha_Contratacion;
	}
	public void setFecha_Contratacion(Date fecha_Contratacion) {
		Fecha_Contratacion = fecha_Contratacion;
	}
	public String getPuesto() {
		return Puesto;
	}
	public void setPuesto(String puesto) {
		Puesto = puesto;
	}
	public Date getUltima_Sesion() {
		return Ultima_Sesion;
	}
	public void setUltima_Sesion(Date ultima_Sesion) {
		Ultima_Sesion = ultima_Sesion;
	}
	public int getID_Tienda() {
		return ID_Tienda;
	}
	public void setID_Tienda(int iD_Tienda) {
		ID_Tienda = iD_Tienda;
	}
	
}

package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import controllers.Caja;
import controllers.Cobrar;
import controllers.Logger;
import controllers.Login;
import dao.JDBC;
import models.Empleado;

/**
 * Hilo que ofrece servicio a un cliente conectado.
 * @author Daniel Ramírez Morilla
 */
public class Servidor extends Thread {

	Socket clienteConectado;
	DataInputStream flujoEntrada;
	DataOutputStream flujoSalida;
	ObjectOutputStream objetoSalida;
	ObjectInputStream objetoEntrada;

	Empleado e;

	public Servidor(Socket clienteConectado) {
		this.clienteConectado = clienteConectado;
	}

	/**
	 * Inicializa los flujos de entrada y salida.
	 * @throws IOException
	 */
	void inicializar() throws IOException {
		flujoEntrada = new DataInputStream
				(clienteConectado.getInputStream());
		flujoSalida = new DataOutputStream
				(clienteConectado.getOutputStream());
		objetoSalida = new ObjectOutputStream
				(clienteConectado.getOutputStream());
		objetoEntrada = new ObjectInputStream
				(clienteConectado.getInputStream());
	}

	public void run() {

		try {
			inicializar();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		String entrada = "";
		do {
			try {
				entrada = flujoEntrada.readUTF();
			} catch (IOException e) {
				Logger.log("[F] Error en la lectura del comando.");
				break;
			}
			System.out.println(entrada);
			
			if (entrada.contains("Login"))
				try {
					iniciarSesion(entrada);
				} catch (NumberFormatException e) {
					Logger.log("[F] Se ha recibido un número privado "
							 + "formado de manera incorrecta. Desconectando.");
					break;
				} catch (IOException e) {
					Logger.log("[F] Se ha producido un problema al contactar "
							 + "con el cliente.");
					break;
				} catch (SQLException e) {
					Logger.log("[F] Ha habido un problema con la base de datos.");
					break;
				}
			else if (entrada.contains("Cobro"))
				try {
					cobrar();
				} catch (Exception e) {
					try {
						Logger.log("[E] Compra cancelada debido a la "
								 + "falta de stock en uno de los productos.");
						JDBC.rollback();
						Logger.log("[i] Se ha devuelto la base de datos "
								 + "a su estado anterior.");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			else if (entrada.contains("Caja"))
				try {
					obtenerCaja();
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}

		} while (!entrada.equals("Salir"));
		try {
			Login.cerrarSesion(e.getDNI());
			JDBC.commit();
		} catch (SQLException e1) {
			Logger.log("[F] No se pudo actualizar la última "
					+ "sesión del usuario " + e.getDNI() + ".");
		}
		Logger.log("[i] Cliente desconectado.");
	}

	/**
	 * Realiza el proceso de incio de sesión.
	 * @param entrada Mensaje recibido.
	 * @return
	 * <ul>
	 * <li><b>True: </b>El objeto no es null.</li>
	 * <li><b>False: </b>El objeto es null.</li>
	 * </ul>
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	boolean iniciarSesion(String entrada)
			throws IOException, NumberFormatException, SQLException {
		e = Login.login(entrada);
		objetoSalida.writeObject(e);
		Logger.log("[i] " + e.getNombre() 
				 + " " + e.getApellidos()
				 + " ha inciado sesión.");
		
		if (e.getNombre() == null)
			return false;
		else objetoSalida.writeObject(Login.listaProductos());
		return true;
	}

	/**
	 * Mandar lista de productos al cliente.
	 * @throws IOException
	 * @throws SQLException
	 */
	void mandarProductos()
			throws IOException, SQLException {
		objetoSalida.writeObject(Login.listaProductos());
	}

	/**
	 * Realiza el proceso de cobro.
	 * @throws Exception
	 */
	void cobrar() throws Exception {
		ArrayList<String> listaCompras = new ArrayList<>();
		Object lista = objetoEntrada.readObject();
		if (lista instanceof ArrayList<?>) {
			ArrayList<?> al = (ArrayList<?>) lista;
			if (al.size() > 0) {
				for (int i = 0; i < al.size(); i++) {
					Object o = al.get(i);
					if (o instanceof String) {
						String s = (String) o;
						listaCompras.add(s);
					}
				}
			}
		}
		Cobrar.cobro(listaCompras, e.getDNI());
		JDBC.commit();
	}

	/**
	 * Realiza el proceso de obtención de la caja del día.
	 * @throws SQLException
	 * @throws IOException
	 */
	void obtenerCaja() throws SQLException, IOException {
		System.out.println(Caja.getCajaDelDia(e.getDNI()));
		flujoSalida.writeDouble(Caja.getCajaDelDia(e.getDNI()));
	}

}

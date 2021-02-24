package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controllers.Caja;
import controllers.Config;
import controllers.Login;
import controllers.Salir;
import controllers.Venta;
import models.Empleado;
import models.Producto;

public class Main {

	public static Empleado e;

	static Socket servidorConectado;
	public static DataInputStream flujoEntrada;
	public static DataOutputStream flujoSalida;
	public static ObjectInputStream objetoEntrada;
	public static ObjectOutputStream objetoSalida;

	public static Scanner sc = new Scanner(System.in);

	public static ArrayList<Producto> listaProductos = new ArrayList<>(); 

	/**
	 * Inicializa todos los componentes necesarios
	 * para la ejecución del programa.
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	static void inicializar()
			throws IOException, SAXException, ParserConfigurationException {
		Config.leerConfiguracion();
		servidorConectado = new Socket
				(Config.getDireccionServidor(), Config.getPuertoServidor());
		servidorConectado.setSoTimeout(10000);
		flujoEntrada = new DataInputStream
				(servidorConectado.getInputStream());
		flujoSalida = new DataOutputStream
				(servidorConectado.getOutputStream());
		objetoEntrada = new ObjectInputStream
				(servidorConectado.getInputStream());
		objetoSalida = new ObjectOutputStream
				(servidorConectado.getOutputStream());
	}

	public static void main(String[] args) {

		try {
			inicializar();
			Login.login();
		} catch (IOException e) {
			System.out.println("No se pudo establecer conexión "
					 		 + "con el servidor.");
			System.exit(1);
		} catch (ClassNotFoundException e1) {
			System.out.println("Hubo un problema en la recepción "
							 + "de datos.");
			System.exit(1);
		} catch (SAXException e) {
			System.out.println("Hubo un problema en la lectura "
							 + "del archivo de configuración.");
			System.exit(1);
		} catch (ParserConfigurationException e) {
			System.out.println("Hubo un problema en la lectura "
					 		 + "del archivo de configuración.");
			System.exit(1);
		}

		System.out.println("¡Bienvenido, " + e.getNombre() + "!");
		int opcion = 0;
		do {
			System.out.println("\n1. Vender · 2. Caja del día · 3. Salir\n");
			try {
				System.out.print("Opcion: ");
				opcion = sc.nextInt();
				System.out.println();
			} catch (InputMismatchException e) {
				opcion = 0;
			}

			switch (opcion) {
			/* Vender */
			case 1:
				try {
					Venta.vender();
				} catch (IOException e) {
					System.out.println("Se ha perdido "
							+ "conexion con el servidor.");
					System.exit(1);
				}
				break;
			/* Caja del día */
			case 2:
				try {
					Caja.cajaDelDia();
				} catch (IOException e) {
					System.out.println("Se ha perdido "
							+ "conexion con el servidor.");
					System.exit(1);
				}
				break;
			/* Salir */
			case 3:
				try {
					Salir.salir();
					System.out.print("¡Que tengas un buen día "
								   + e.getNombre() + "!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

		} while (opcion != 3);

	}

}

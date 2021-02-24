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

import models.Empleado;
import models.Producto;

public class Main {

	public static Empleado e;

	static Socket servidorConectado;
	static DataInputStream flujoEntrada;
	static DataOutputStream flujoSalida;
	public static ObjectInputStream objetoEntrada;
	static ObjectOutputStream objetoSalida;

	static Scanner sc = new Scanner(System.in);

	static ArrayList<Producto> listaProductos = new ArrayList<>();

	/**
	 * Inicializa todos los componentes necesarios
	 * para la ejecución del programa.
	 */
	static void inicializar() throws IOException {
		servidorConectado = new Socket("localhost", 6000);
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
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		iniciarSesion();

		System.out.println("1. Vender | 2. Caja del día | 3. Salir");
		
		int opcion = 0;
		do {
			try {
				opcion = sc.nextInt();
			} catch (InputMismatchException e) {
				opcion = 0;
			}

			switch (opcion) {
			case 1:
				try {
					vender();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					caja();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					salir();
					System.out.println("Bye!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}

		} while (opcion != 3);

	}

	/**
	 * Inicio de sesión.
	 */
	static void iniciarSesion() {
		System.out.print("Introducir número privado: ");
		try {
			flujoSalida.writeUTF("Login;" + sc.nextInt());
			System.out.println();
			e = (Empleado) objetoEntrada.readObject();
			Object lista = objetoEntrada.readObject();
			if (lista instanceof ArrayList<?>) {
				ArrayList<?> al = (ArrayList<?>) lista;
				if (al.size() > 0) {
					for (int i = 0; i < al.size(); i++) {
						Object o = al.get(i);
						if (o instanceof Producto) {
							Producto s = (Producto) o;
							listaProductos.add(s);
						}
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realizar compra.
	 * @throws IOException
	 */
	static void vender() throws IOException {
		ArrayList<String> listaCompra = new ArrayList<>();
		int opcion = 0;
		for (Producto p : listaProductos) {
			System.out.println(p.getID() + ". " + p.getNombre());
		}
		do {
			System.out.println("1. Añadir producto | 2. Lista de productos");
			System.out.print("Opcion: ");
			opcion = sc.nextInt();
			System.out.println();
			switch (opcion) {
			
			/* Añadir producto. */
			case 1:
				System.out.print("ID del producto: ");
				int ID_Producto = sc.nextInt();
				System.out.println();
				System.out.print("Cantidad: ");
				int cantidad = sc.nextInt();
				System.out.println();
				listaCompra.add("Cobro;" + e.getID_Tienda() + ";" 
						+ ID_Producto + ";" + cantidad);
				break;
				
			/* Imprimir lista de productos. */
			case 2:
				for (Producto p : listaProductos) {
					System.out.println(p.getID() + ". " + p.getNombre());
				}
				break;
				
			/* Efectuar compra. */
			case 3:
				break;
				
			/* Cancela compra. */
			case 4:
				break;
				
			}
		} while (opcion != 3);
		flujoSalida.writeUTF("Cobro");
		objetoSalida.writeObject(listaCompra);
	}

	/**
	 * Obtener caja del día.
	 * @throws IOException
	 */
	static void caja() throws IOException {
		flujoSalida.writeUTF("Caja");
		System.out.println("Caja de hoy: "
						 + flujoEntrada.readDouble());
	}

	/**
	 * Terminar conexión con el servidor
	 * y cerrar la aplicación.
	 * @throws IOException
	 */
	static void salir() throws IOException {
		flujoSalida.writeUTF("Salir");
	}

}

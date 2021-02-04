package servidor;

import java.io.IOException;
import java.net.SocketException;

import controller.*;

/**
 * Parte servidor del programa.
 * @author Daniel Ramírez Morilla
 */
public class Servidor {

	/**
	 * Lanza la parte servidor del programa.
	 * @throws IOException
	 * @throws SocketException
	 */
	public static void main() 
			throws IOException, SocketException {
		
		// NOMBRE DEL USUARIO
		System.out.print("Introduzca su nombre: ");
		String nombre = Controller.sc2.nextLine();
		System.out.println("\nEsperando anfitrión...\n");
		Controller.crear();
		
		String mensaje = "";
		String[] separarNombre;
		int opcion;
		do {
			System.out.print("1 - Enviar | 2 - Recibir : ");
			opcion = Controller.sc.nextInt();
			System.out.print("\n");
			switch(opcion) {
			case 1:
				System.out.print(nombre + ": ");
				mensaje = Controller.sc2.nextLine();
				Mensajeria.enviarMensajeSRV(mensaje, nombre);
				System.out.print("\n");
				break;
			case 2:
				// SEPARA EL NOMBRE DEL USUARIO DEL MENSAJE
				separarNombre = Mensajeria.esperarRespuestaSRV().split(": ");
				mensaje = separarNombre[separarNombre.length - 1];
				System.out.print("\n");
				break;
			}
		} while (!mensaje.equals("Cambio y corto."));
		
		Controller.cerrarServidor();

	}

}

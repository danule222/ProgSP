package cliente;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import controller.*;

/**
 * Parte cliente del programa.
 * @author Daniel Ramírez Morilla
 */
public class Cliente {

	/**
	 * Lanza la parte cliente del programa.
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws SocketException
	 */
	public static void main() 
			throws UnknownHostException, IOException, SocketException {
		
		System.out.print("Introduzca su nombre: ");
		String nombre = Controller.sc2.nextLine();
		System.out.print("IP del host: ");
		Controller.unirse(Controller.sc2.nextLine());
		System.out.print("\n");
		
		String mensaje = "";
		String separarNombre[];
		int opcion;		
		do {
			System.out.print("1 - Enviar | 2 - Recibir : ");
			opcion = Controller.sc.nextInt();
			System.out.print("\n");
			switch(opcion) {
			case 1:
				System.out.print(nombre + ": ");
				mensaje = Controller.sc2.nextLine();
				Mensajeria.enviarMensajeCLN(mensaje, nombre);
				System.out.print("\n");
				break;
			case 2:
				separarNombre = Mensajeria.esperarRespuestaCLN().split(": ");
				mensaje = separarNombre[separarNombre.length - 1];
				System.out.print("\n");
				break;
			}
		} while (!mensaje.equals("Cambio y corto."));
		
		Controller.cerrarCliente();
		
	}

}

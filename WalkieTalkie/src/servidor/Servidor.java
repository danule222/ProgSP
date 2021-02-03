package servidor;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import controller.*;

public class Servidor {

	public static void main(String[] args) 
			throws IOException, SocketException {
		
		Scanner scs = new Scanner(System.in);
		Scanner scs2 = new Scanner(System.in);
		Controller.crear();
		
		String mensaje = "";
		int opcion;
		do {
			System.out.println("1 - enviar 2 - recibir");
			opcion = scs.nextInt();
			switch(opcion) {
			case 1:
				mensaje = scs2.nextLine();
				Mensajeria.enviarMensajeSRV(mensaje);
				break;
			case 2:
				mensaje = Mensajeria.esperarRespuestaSRV();
				break;
			}
		} while (!mensaje.equals("Cambio y corto."));
		
		Controller.cerrarServidor();
		scs.close();
		scs2.close();

	}

}

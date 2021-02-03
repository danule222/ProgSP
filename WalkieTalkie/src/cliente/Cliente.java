package cliente;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import controller.*;

public class Cliente {

	public static void main(String[] args) 
			throws UnknownHostException, IOException, SocketException {
		
		Scanner scc = new Scanner(System.in);
		Scanner scc2 = new Scanner(System.in);
		Controller.unirse(scc.nextLine());
		
		String mensaje = "";
		int opcion;		
		do {
			System.out.println("1 - enviar 2 - recibir");
			opcion = scc.nextInt();
			switch(opcion) {
			case 1:
				mensaje = scc2.nextLine();
				Mensajeria.enviarMensajeCLN(mensaje);
				break;
			case 2:
				mensaje = Mensajeria.esperarRespuestaCLN();
				break;
			}
		} while (!mensaje.equals("Cambio y corto."));
		
		Controller.cerrarCliente();
		scc.close();
		scc2.close();
		
	}

}

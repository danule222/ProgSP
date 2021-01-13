package servidor;

import java.io.*;
import java.net.*;

public class Servidor {

	public static void main(String[] args) throws IOException {
		
		int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        Socket clienteConectado = null;
        clienteConectado = servidor.accept();
       
        // CREO FLUJO DE SALIDA AL CLIENTE
        OutputStream salida = null;
        salida = clienteConectado.getOutputStream();
        DataOutputStream flujoSalida = new DataOutputStream(salida);
       
        // ENVIO UN SALUDO AL CLIENTE
        flujoSalida.writeUTF("¡" + Controller.buenas() + "! Son las " + 
        	Controller.getHoraActual() + " y hoy es " + Controller.getFechaActual() + ".");
       
        // CERRAR STREAMS Y SOCKETS
        salida.close();
        flujoSalida.close();
        clienteConectado.close();
        servidor.close();

	}

}

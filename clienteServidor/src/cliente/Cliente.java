package cliente;

import java.io.*;
import java.net.*;

public class Cliente {

	public static void main(String[] args) throws IOException {
		
		String Host = "localhost";
        int Puerto = 6000;//puerto remoto
       
        Socket Cliente = new Socket(Host, Puerto);
       
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
       
        // EL SERVIDOR ME ENVIA UN MENSAJE   
        System.out.println(flujoEntrada.readUTF());
       
        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        Cliente.close();

	}

}

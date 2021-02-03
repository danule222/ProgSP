package controller;

import java.io.IOException;

public class Mensajeria {
	
	// SERVIDOR
	
	public static void enviarMensajeSRV(String mensaje) 
			throws IOException {
    	Controller.flujoSalida.writeUTF(mensaje);
    }
	
	public static String esperarRespuestaSRV()
			throws IOException {
		String mensaje = Controller.flujoEntrada.readUTF();
		System.out.println(mensaje);
		return mensaje;
	}
	
	//CLIENTE
	
	public static void enviarMensajeCLN(String mensaje) 
			throws IOException {
    	Controller.flujoSalida.writeUTF(mensaje);
    }
    
    public static String esperarRespuestaCLN()
			throws IOException {
    	
		String mensaje = Controller.flujoEntrada.readUTF();
		System.out.println(mensaje);
		return mensaje;
	}
	
}

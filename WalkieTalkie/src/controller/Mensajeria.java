package controller;

import java.io.IOException;

/**
 * Clase encargada en enviar y recibir los mensajes entre
 * las partes cliente y servidor.
 * @author Daniel Ramírez Morilla
 */
public class Mensajeria {
	
	// SERVIDOR
	
	/**
	 * Envía un mensaje. Solo puede ser usado por el
	 * lado servidor.
	 * @param mensaje Mensaje a enviar al cliente.
	 * @param nombre Nombre del usuario que escribe el mensaje.
	 * @throws IOException
	 */
	public static void enviarMensajeSRV(String mensaje, String nombre) 
			throws IOException {
    	Controller.flujoSalida.writeUTF(nombre + ": " + mensaje);
    }
	
	/**
	 * Espera a que se le envíe un mensaje. Solo puede ser usado 
	 * por el lado servidor.
	 * @return Mensaje recibido por el cliente.
	 * @throws IOException
	 */
	public static String esperarRespuestaSRV()
			throws IOException {
		String mensaje = Controller.flujoEntrada.readUTF();
		System.out.println(mensaje);
		return mensaje;
	}
	
	// CLIENTE
	
	/**
	 * Envía un mensaje. Solo puede ser usado por el
	 * lado cliente.
	 * @param mensaje Mensaje a enviar al servidor.
	 * @param nombre Nombre del usuario que escribe el mensaje.
	 * @throws IOException
	 */
	public static void enviarMensajeCLN(String mensaje, String nombre) 
			throws IOException {
    	Controller.flujoSalida.writeUTF(nombre + ": " + mensaje);
    }
    
	/**
	 * Espera a que se le envíe un mensaje. Solo puede ser usado 
	 * por el lado cliente.
	 * @return Mensaje recibido por el servidor.
	 * @throws IOException
	 */
    public static String esperarRespuestaCLN()
			throws IOException {
    	
		String mensaje = Controller.flujoEntrada.readUTF();
		System.out.println(mensaje);
		return mensaje;
	}
	
}

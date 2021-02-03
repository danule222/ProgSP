package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {
	
	// COMÚN
	
	private static int Puerto = 5093;
	static OutputStream salida;
	static DataInputStream flujoEntrada;
	static DataOutputStream flujoSalida;

	//CLIENTE
	
	static Socket Cliente;
	
	static void inicializarCLN()
			throws IOException {
		salida = null;
    	salida = Controller.Cliente
    			.getOutputStream();
    	flujoSalida = new 
    			DataOutputStream(salida);
    	flujoEntrada = 
				new DataInputStream(Cliente.getInputStream());
	}
	
	public static void unirse(String Host)
			throws UnknownHostException, IOException {
	    Cliente = new Socket(Host, Puerto);
	    inicializarCLN();
	}
    
    public static void cerrarCliente()
    		throws IOException {
    	flujoEntrada.close();
        Cliente.close();
        flujoSalida.close();
        salida.close();
    }
    
    // SERVIDOR
    
    private static ServerSocket servidor;
    static Socket clienteConectado;
    
    static void inicializarSRV()
			throws IOException {
		salida = null;
    	salida = clienteConectado
    			.getOutputStream();
    	flujoSalida = new 
    			DataOutputStream(salida);
    	flujoEntrada = 
				new DataInputStream(clienteConectado.getInputStream());
	}
    
    public static void crear() throws IOException {
        servidor = new ServerSocket(Puerto);
        clienteConectado = null;
        clienteConectado = servidor.accept();
        inicializarSRV();
    }
    
    public static void cerrarServidor() throws IOException {
    	salida.close();
        clienteConectado.close();
        servidor.close();
        flujoEntrada.close();
    }
    
}

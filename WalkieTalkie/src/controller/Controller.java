package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Clase controladora.
 * @author Daniel Ramírez Morilla
 */
public class Controller {
	
	// COMÚN
	
	public static Scanner sc = new Scanner(System.in);
	public static Scanner sc2 = new Scanner(System.in);
	private static int Puerto = 5093;
	static OutputStream salida;
	static DataInputStream flujoEntrada;
	static DataOutputStream flujoSalida;

	// CLIENTE
	
	static Socket Cliente;
	
	/**
	 * Inicializa todos los recursos utilizados por
	 * el cliente.
	 * @throws IOException
	 */
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
	
	/**
	 * Inicia una conexión a la dirección proporcionada.
	 * @param Host Dirección IP/nombre del servidor.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void unirse(String Host)
			throws UnknownHostException, IOException {
	    Cliente = new Socket(Host, Puerto);
	    Cliente.setSoTimeout(10000);
	    inicializarCLN();
	}
    
	/**
	 * Cierra todos los recursos usados por
	 * el cliente.
	 * @throws IOException
	 */
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
    
    /**
     * Inicializa todos los recursos usados por
     * el servidor.
     * @throws IOException
     */
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
    
    /**
     * Crea un Socket al que se pueda conectar un cliente.
     * @throws IOException
     */
    public static void crear() throws IOException {
        servidor = new ServerSocket(Puerto);
        clienteConectado = null;
        clienteConectado = servidor.accept();
        clienteConectado.setSoTimeout(10000);;
        inicializarSRV();
    }
    
    /**
     * Cierra todos los recursos usados por el servidor.
     * @throws IOException
     */
    public static void cerrarServidor() throws IOException {
    	salida.close();
        clienteConectado.close();
        servidor.close();
        flujoEntrada.close();
    }
    
}

package controllers;

import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import models.Producto;

/**
 * Controlador relacionado con las operaciones
 * de venta.
 * @author Daniel Ramírez Morilla
 */
public class Venta {
	
	public static void vender() throws IOException {
		ArrayList<String> listaCompra = new ArrayList<>();
		int opcion = 0;
		for (Producto p : Main.listaProductos) {
			System.out.println(p.getID() + ". " + p.getNombre());
		}
		System.out.println();
		do {
			System.out.println("1. Añadir producto · "
							 + "2. Lista de productos · 3. Salir");
			System.out.print("Opcion: ");
			opcion = Main.sc.nextInt();
			System.out.println();
			switch (opcion) {
			
			/* Añadir producto. */
			case 1:
				System.out.print("ID del producto: ");
				int ID_Producto = Main.sc.nextInt();
				System.out.print("\nCantidad: ");
				int cantidad = Main.sc.nextInt();
				System.out.println();
				if (cantidad > 0)
					listaCompra.add("Cobro;" + Main.e.getID_Tienda() + ";" 
								  + ID_Producto + ";" + cantidad);
				else System.out.println("Cantidad no válida.");
				break;
				
			/* Imprimir lista de productos. */
			case 2:
				for (Producto p : Main.listaProductos) {
					System.out.println(p.getID() + ". " + p.getNombre());
				}
				System.out.println();
				break;
				
			/* Efectuar compra. */
			case 3:
				break;
				
			/* Cancelar compra. */
			case 4:
				break;
				
			}
		} while (opcion != 3);
		if (!listaCompra.isEmpty()) {			
			Main.flujoSalida.writeUTF("Cobro");
			Main.objetoSalida.writeObject(listaCompra);
		} else System.out.println("No se ha comprado nada.");
	}
	
}

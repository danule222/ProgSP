package Aplicacion;

public class ReconocerSO {

	static String SO = System.getProperty("os.name").toLowerCase();

	/**
	 * Compruba si el Sistema Operativo es Windows o no.
	 * @return Valor verdadero si el Sistema Operativo es Windows.
	 */
	public static boolean isWindows() {
		return (SO.indexOf("win") >= 0);
	}

	/**
	 * Compruba si el Sistema Operativo es Unix o no.
	 * @return Valor verdadero si el Sistema Operativo es Unix.
	 */
	public static boolean isUnix() {

		return (SO.indexOf("nix") >= 0 || SO.indexOf("nux") >= 0 || 
				SO.indexOf("aix") > 0 || SO.indexOf("mac") >= 0);

	}
	
	/**
	 * Imprime un mensaje de error relacionado con el reconocimiento
	 * del Sistema Operativo.
	 */
	public static void F() {
		System.out.println("El programa no ha sido capaz de reconocer su SO.");
	}

}
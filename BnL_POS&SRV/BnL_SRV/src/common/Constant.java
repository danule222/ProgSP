package common;

/**
 * Costantes del programa.
 * @author Daniel Ramírez Morilla
 */
public class Constant {

	//  Constantes para la conexión a la BD
	/** Dirección de la base de datos. */
    public static String SERVER = "localhost:3306";
    /** Nombre del esquema. */
    public static String DATABASE = "BnL";
    /** String de conexión de JDBC. */
    public static String CONEXION = "jdbc:mysql://" + SERVER + "/" + DATABASE
            + "?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";
    /** Usuario de la base de datos. */
    public static String USER = "BnLSRV";
    /** Contraseña de la base de datos. */
    public static String PASSWORD = "x!STL6aprE=ed*9lBruY";
	
}

package ConcesionarioGUI;

/**
 * Excepci&oacute;n si no existe el coche en el concesionario.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class CocheNoExisteException extends Exception {
	public CocheNoExisteException(String arg0) {
		super(arg0);
	}

}

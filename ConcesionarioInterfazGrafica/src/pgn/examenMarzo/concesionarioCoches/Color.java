package pgn.examenMarzo.concesionarioCoches;

//import pgn.examenMarzo.utiles.Menu;

/**
 * Se limitarán los colores a tres: plata, rojo y azul. Para solicitar el color
 * al dar de alta al coche podrá implementarse un método pedirColor que mediante
 * la gestión de un menú, devolverá el color indicado
 * 
 * @author MaríaLourdes
 * 
 */
public enum Color {
	PLATA, ROJO, AZUL;

	private static final Color[] VALUES = Color.values();

	public static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Color color : getValues()) {
			opcionesMenu[i++] = color.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	public static Color[] getValues() {
		return VALUES;
	}

}

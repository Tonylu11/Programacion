package pgn.examenMarzo.concesionarioCoches;
/**
 * 
 * @author Antonio Luque Bravo
 *
 */
public enum Modelo {

	SERIE1(Marca.BMW),

	SERIE2(Marca.BMW),

	SERIE3(Marca.BMW),

	SERIE5(Marca.BMW),

	CORDOBA(Marca.SEAT),

	IBIZA(Marca.SEAT),

	TOLEDO(Marca.SEAT);
	private Marca marca;

	private Modelo(Marca marca) {
		this.marca = marca;
	}

	public Marca getMarca() {
		return marca;
	}

	public String toString() {
		return name() + ", " + getMarca();

	}

	// Para el menú-------------------------------------------------
	private static final Modelo[] VALUES = Modelo.values();

	static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length + 1];
		int i = 0;
		for (Modelo modelo : VALUES) {
			opcionesMenu[i++] = modelo.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}

	public static Modelo[] getValues() {
		return VALUES;
	}
	// -------------------------------------------------

}

package pgn.examenMarzo.concesionarioCoches;

import pgn.examenMarzo.utiles.Menu;
import pgn.examenMarzo.utiles.Teclado;
import pgn.examenMarzo.concesionarioCoches.Color;
import pgn.examenMarzo.concesionarioCoches.Modelo;

/**
 * Queremos modelar un concesionario de coches en Java. Nos limitaremos a las
 * siguientes opciones: Añadir un coche (se pedirá matricula, color y modelo),
 * Eliminar un coche (por matrícula), mostrar un coche (por matrícula), mostrar
 * coches (todo el concesionario)
 * 
 * @author Antonio Luque Bravo
 * 
 */
public class TestConcesionario extends ConcesionarioTest {
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Salir" });
	private static Menu menuColores = new Menu("Colores de los coches",
			Color.generarOpcionesMenu());
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.generarOpcionesMenu());
	static Concesionario concesionario = new Concesionario();

	public static void main(String[] args) {
		do {
			switch (menu.gestionar()) {
			case 1:// "Añadir Coche
				annadirCoche();
				break;
			case 2:// Eliminar Coche
				eliminarCoche();
				break;
			case 3:// Obtener Coche
				getCoche();
				break;
			case 4:// Mostrar lista
				System.out.println(concesionario);
				break;
			case 5:// Contar coches
				System.out.println("Número de coches en el concesionario: "
						+ concesionario.size());
				break;
			case 6:// Mostrar coches de un color
				System.out.println(concesionario.getCochesColor(pedirColor()));
				break;

			default:// Salir
				System.out.println("Aaaaaaaaaaaaaaaaaaaaadios");
				return;
			}
		} while (true);
	}

	private static void getCoche() {
		Coche coche = concesionario.get(Teclado
				.leerCadena("Introduce la matrícula"));
		if (coche == null)
			System.out.println("No existe el coche en el concesionario.");
		else
			System.out.println(coche);
	}

	private static void eliminarCoche() {
		if (concesionario
				.eliminar(Teclado.leerCadena("Introduce la matrícula")))
			System.out.println("Coche eliminado");
		else
			System.out.println("No se ha podido eliminar");
	}

	private static void annadirCoche() {
		if (concesionario.annadir(Teclado.leerCadena("Introduce la matrícula"),
				pedirColor(), pedirModelo()))
			System.out.println("Coche añadido con éxito");
		else
			System.out.println("No se ha podido añadir");
	}

	private static Modelo pedirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] arrModelos = Modelo.getValues();
		if (opcion == arrModelos.length + 1)
			return null;
		return arrModelos[opcion - 1];
	}

	private static Color pedirColor() {
		int opcion = menuColores.gestionar();
		Color[] arrColores = Color.getValues();
		if (opcion == arrColores.length + 1)
			return null;
		return arrColores[opcion - 1];
	}
}

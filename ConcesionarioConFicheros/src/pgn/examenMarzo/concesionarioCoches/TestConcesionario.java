package pgn.examenMarzo.concesionarioCoches;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import pgn.examenMarzo.utiles.Menu;
import pgn.examenMarzo.utiles.Teclado;
import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.Color;
import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.Fichero;
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
@SuppressWarnings("serial")
public class TestConcesionario extends Concesionario implements Serializable {
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Ficheros", "Salir" });
	private static Menu menuColores = new Menu("Colores de los coches",
			Color.generarOpcionesMenu());
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.generarOpcionesMenu());
	private static Menu menuFicheros = new Menu("Ficheros", new String[] {
			"Nuevo", "Abrir", "GuardarComo", "Guardar", "Salir" });
	static Concesionario concesionario = new Concesionario();
	private static boolean modificado;
	private static File selectedFile;
	@SuppressWarnings("unused")
	private static Menu menuGuardarCambios;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
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
			case 7:
				gestionarFicheros();
				break;
			default:// Salir
				System.out.println("Adios");
				return;
			}
		} while (true);
	}

	private static void getCoche() {
		Coche coche;
		try {
			coche = concesionario.get(Teclado
					.leerCadena("Introduce la matrícula"));
			System.out.println(coche);
		} catch (MatriculaNoValidaException | CocheNoExisteException e) {
			System.out.println("No existe el coche en el concesionario.");
		}

	}

	private static void eliminarCoche() {
		try {
			concesionario
					.eliminar(Teclado.leerCadena("Introduce la matrícula"));
			System.out.println("Coche eliminado");
			asignarModificado(true);
		} catch (MatriculaNoValidaException | CocheNoExisteException e) {
			System.out.println("No se ha podido eliminar");
			e.printStackTrace();
		}
	}

	private static void annadirCoche() {
		try {
			concesionario.annadir(Teclado.leerCadena("Introduce la matrícula"),
					pedirColor(), pedirModelo());
			System.out.println("Coche añadido con éxito");
			asignarModificado(true);

		} catch (MatriculaNoValidaException | ColorNoValidoException
				| ModeloNoValidoException | CocheYaExistenteException e) {
			System.out.println("No se ha podido añadir");
			e.printStackTrace();
		}
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

	static void gestionarFicheros() throws FileNotFoundException, IOException,
			ClassNotFoundException {
		switch (menuFicheros.gestionar()) {
		case 1:
			nuevo();
			break;
		case 2:
			abrir();
			break;
		case 3:
			guardarComo();
			break;
		case 4:
			guardar();
			break;
		default:
			System.out.println("Adios");
			return;
		}
	}

	private static void abrir() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		guardarSiModificado();
		try {
			File fichero = new File(
					Teclado.leerCadena("Dame el nombre del archivo: "));
			fichero = Fichero.annadirExtension(fichero);
			concesionario = (Concesionario) Fichero.leer(fichero);
		} catch (ClassNotFoundException e) {
			System.out.println("Fichero con información distinta.");
		} catch (IOException e) {
			System.out.println("No se puede abrir el fichero.");
		}
	}

	private static void guardarComo() {
		try {
			File file = new File(
					Teclado.leerCadena("Introduce el nombre del fichero a guardar: "));
			file = Fichero.annadirExtension(file);
			if (Fichero.confirmarExistencia(file)) {
				char caracter = Teclado
						.leerCaracter("El fichero ya existe. ¿Desea sobreescribirlo? (s/n)");
				switch (caracter) {
				case 'n':
				case 'N':
					return;
				}
			}

			Fichero.guardar(concesionario, file);
			asignarModificado(false);
			setSelectedFile(file);
		} catch (IOException e) {
			System.out.println("El sistema no puede guardar el fichero.");
		}
	}

	private static void guardar() throws FileNotFoundException, IOException {
		if (getSelectedFile() == null)
			guardarComo();
		else {
			try {
				Fichero.guardar(concesionario, getSelectedFile());
				asignarModificado(false);
			} catch (IOException e) {
				System.out.println("No se ha podido guardar.");
			}
		}
	}

	private static void nuevo() throws FileNotFoundException, IOException {
		if (estaModificado() == true)
			guardarSiConfirmacion();
		asignarModificado(false);
		concesionario = new Concesionario();
		setSelectedFile(null);
	}

	private static boolean guardarSiConfirmacion()
			throws FileNotFoundException, IOException {
		if (estaModificado() == true) {
			char respuesta = Teclado.leerCaracter("¿Desea guardarlo? s/n : ");
			switch (respuesta) {
			case 's':
			case 'S':
				guardar();
				return true;
			case 'n':
			case 'N':
				return false;
			}
		}
		return false;
	}

	private static boolean guardarSiModificado() throws FileNotFoundException,
			IOException {
		if (estaModificado()) {
			guardarSiConfirmacion();
		}
		return false;
	}

	private static void asignarModificado(boolean b) {
		modificado = b;

	}

	public static boolean estaModificado() {
		return modificado;
	}

	private static File getSelectedFile() {
		return selectedFile;
	}

	private static void setSelectedFile(File selectedFile) {
		TestConcesionario.selectedFile = selectedFile;
	}
}

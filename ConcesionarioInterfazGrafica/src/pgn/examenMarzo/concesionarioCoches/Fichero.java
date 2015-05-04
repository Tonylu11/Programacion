package pgn.examenMarzo.concesionarioCoches;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings({ "serial", "unused" })
public class Fichero implements Serializable {
	public static File fichero = new File("Sin-titulo.obj");

	public static void guardar(Object object, File archivo) throws IOException {
		archivo = annadirExtension(archivo);
		try (ObjectOutputStream salida = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(archivo, false)))) {
			salida.writeObject(object);
		}

	}

	public static Object leer(File archivo) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		archivo = annadirExtension(archivo);
		try (ObjectInputStream ois = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(archivo)))) {
			return (Object) ois.readObject();
		}
	}

	public static File annadirExtension(File archivo) {
		String extension = archivo.getPath();
		if (!extension.endsWith(".obj"))
			return new File(archivo + ".obj");
		return archivo;
	}

	public static boolean confirmarExistencia(File archivo) {
		archivo = annadirExtension(archivo);
		return archivo.exists();
	}
}

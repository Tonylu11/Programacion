package pgn.examenMarzo.concesionarioCoches;

import java.io.Serializable;
import java.util.ArrayList;

import pgn.examenMarzo.concesionarioCoches.CocheNoExisteException;

/*
 * No pueden existir dos coches con la misma matrícula en el almacén del concesinario
 * no puede añadirse un coche al concecionario con alguno de sus atributos inválidos. Han de conocerse todas sus características 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * El nombre del concesionario es "IES Gran Capitán".
 * 
 * Lógicamente, no podrá añadirse un coche inválido glmacén del concesinario)
 * 
 * Han de conocerse todas sus características Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Antonio Luque Bravo
 * 
 */
@SuppressWarnings("serial")
public class Concesionario implements Serializable {
	/**
	 * colección de coches. No puede tener null
	 */
	public ArrayList<Coche> almacen = new ArrayList<Coche>();
	private final String nombre = "IES Gran Capitán";
	private boolean modificado;

	public boolean annadir(String matricula, Color color, Modelo modelo)
			throws MatriculaNoValidaException, ColorNoValidoException,
			ModeloNoValidoException, CocheYaExistenteException {
		Coche coche = new Coche(matricula, color, modelo);
		if (almacen.contains(coche))
			throw new CocheYaExistenteException("El coche ya existe");
		setModificado(true);
		return almacen.add(coche);
	}

	public boolean eliminar(String matricula)
			throws MatriculaNoValidaException, CocheNoExisteException {
		Coche coche = new Coche(matricula);
		if (almacen.contains(coche)) {
			setModificado(true);
			return almacen.remove(coche);
		} else
			throw new CocheNoExisteException("El coche no existe");

	}

	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almacén indicado por la matrícula
	 * 
	 * @param matricula
	 *            Matrícula a buscar
	 * @return Coche contenido en el almacén. null si no existe
	 */
	public Coche get(String matricula) throws MatriculaNoValidaException,
			CocheNoExisteException {
		Coche coche = new Coche(matricula);
		int index = almacen.indexOf(coche);
		if (index != -1) {
			return almacen.get(index);
		}
		throw new CocheNoExisteException("El coche no existe");
	}

	public Coche get(int index) {
		if (almacen.isEmpty() | index < 0 | index > almacen.size() - 1)
			return null;
		return almacen.get(index);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "\n" + almacen;
	}

	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	public boolean isModificado() {
		return modificado;
	}

}
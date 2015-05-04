package pgn.examenMarzo.concesionarioCoches;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class Coche implements Serializable {
	private String matricula;
	private Color color;
	private Modelo modelo;
	static final private Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	Coche(String matricula, Color color, Modelo modelo)
			throws MatriculaNoValidaException, ColorNoValidoException,
			ModeloNoValidoException {
		super();
		setMatricula(matricula);
		setColor(color);
		setModelo(modelo);
	}

	Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}

	static Coche instanciarCoche(String matricula, Color color, Modelo modelo)
			throws MatriculaNoValidaException, ColorNoValidoException,
			ModeloNoValidoException {
		new Coche(matricula, color, modelo);
		return null;
	}

	static Coche instanciarCoche(String matricula)
			throws MatriculaNoValidaException {
		if (esValida(matricula))
			return new Coche(matricula);
		return null;
	}

	public static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}

	private void setMatricula(String matricula)
			throws MatriculaNoValidaException {
		if (esValida(matricula))
			this.matricula = matricula;
		else
			throw new MatriculaNoValidaException("La matrícula no es válida");

	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		this.color = color;
	}

	private void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCoche [matricula=" + matricula + ", color=" + color
				+ ", modelo=" + modelo + "]";
	}

	public Modelo getModelo() {
		return modelo;
	}

	public String getMatricula() {
		return matricula;
	}

}

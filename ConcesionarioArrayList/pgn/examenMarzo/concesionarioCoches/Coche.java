package pgn.examenMarzo.concesionarioCoches;

import java.util.regex.Pattern;
/**
* @author Antonio Luque Bravo
*/
public class Coche {
	private String matricula;
	private String strAyuda="Hola";
	private Color color;
	private Modelo modelo;
	static final private Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	private Coche(String matricula, Color color, Modelo modelo) {
		super();
		setMatricula(matricula);
		setColor(color);
		setModelo(modelo);
	}

	private Coche(String matricula) {
		setMatricula(matricula);
	}

	static Coche instanciarCoche(String matricula, Color color, Modelo modelo) {
		if (esValida(matricula) && color != null && modelo != null)
			return new Coche(matricula, color, modelo);
		return null;
	}

	static Coche instanciarCoche(String matricula) {
		if (esValida(matricula))
			return new Coche(matricula);
		return null;
	}

	private static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}

	private void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	Color getColor() {
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

}

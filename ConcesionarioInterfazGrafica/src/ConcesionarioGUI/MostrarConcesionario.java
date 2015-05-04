package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import pgn.examenMarzo.concesionarioCoches.Coche;

/**
 * Muestra el concesionario.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class MostrarConcesionario extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMatricula;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnRojo;
	private JRadioButton rdbtnAzul;
	private JRadioButton rdbtnPlata;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMarca;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxModelo;
	private int indice = 0;
	private JButton botonAnterior;
	private JButton botonSiguiente;
	private JButton btnMostrar;

	/**
	 * Crea el di&aacute;logo.
	 */
	@SuppressWarnings("rawtypes")
	public MostrarConcesionario() {
		setModal(true);
		setTitle("Mostrar concesionario");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMatricula = new JLabel("Matricula");
			lblMatricula.setBounds(23, 36, 75, 14);
			contentPanel.add(lblMatricula);
		}

		txtMatricula = new JTextField();
		txtMatricula.setBackground(Color.BLACK);
		txtMatricula.setEnabled(false);
		txtMatricula.setEditable(false);
		txtMatricula.setBounds(100, 33, 86, 20);
		contentPanel.add(txtMatricula);
		txtMatricula.setColumns(10);

		JPanel Colores = new JPanel();
		Colores.setToolTipText("");
		Colores.setBorder(new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, null, null), "Colores",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Colores.setBounds(39, 88, 121, 97);
		contentPanel.add(Colores);
		Colores.setLayout(null);

		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setEnabled(false);
		rdbtnRojo.setBounds(6, 16, 109, 23);
		Colores.add(rdbtnRojo);
		buttonGroup.add(rdbtnRojo);

		rdbtnAzul = new JRadioButton("Azul");
		rdbtnAzul.setEnabled(false);
		rdbtnAzul.setBounds(6, 42, 109, 23);
		Colores.add(rdbtnAzul);
		buttonGroup.add(rdbtnAzul);

		rdbtnPlata = new JRadioButton("Plata");
		rdbtnPlata.setEnabled(false);
		rdbtnPlata.setBounds(6, 68, 109, 23);
		Colores.add(rdbtnPlata);
		buttonGroup.add(rdbtnPlata);

		JList list = new JList();
		list.setBounds(90, 155, -57, -63);
		contentPanel.add(list);

		comboBoxMarca = new JComboBox();
		comboBoxMarca.setBackground(Color.WHITE);
		comboBoxMarca.setEnabled(false);
		comboBoxMarca.setBounds(294, 84, 105, 22);
		contentPanel.add(comboBoxMarca);

		comboBoxModelo = new JComboBox();
		comboBoxModelo.setBackground(Color.WHITE);
		comboBoxModelo.setEnabled(false);
		comboBoxModelo.setBounds(294, 132, 105, 22);
		contentPanel.add(comboBoxModelo);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(238, 88, 46, 14);
		contentPanel.add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(238, 136, 46, 14);
		contentPanel.add(lblModelo);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			botonAnterior = new JButton("<");
			botonAnterior.setVisible(false);
			botonAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarAnterior();
				}

			});
			buttonPane.add(botonAnterior);

			botonSiguiente = new JButton(">");
			botonSiguiente.setVisible(false);
			botonSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarSiguiente();
				}
			});
			buttonPane.add(botonSiguiente);

			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});

				btnMostrar = new JButton("Mostrar");
				btnMostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (General.concesionario.size() == 0)
							JOptionPane.showMessageDialog(contentPanel,
									"El concesionario esta vacio", "Error",
									JOptionPane.ERROR_MESSAGE);
						if (General.concesionario.get(0) != null) {
							mostrarCoche(General.concesionario.get(indice));
							comprobarTamaño();
							botonSiguiente.setVisible(true);
							botonAnterior.setVisible(true);
							btnMostrar.setVisible(false);
						}
					}
				});
				buttonPane.add(btnMostrar);
				buttonPane.add(cancelButton);
			}
		}

	}

	/**
	 * Muestra el siguiente coche al actual.
	 */
	private void mostrarSiguiente() {
		mostrarCoche(General.concesionario.get(++indice));
		comprobarTamaño();
	}

	/**
	 * Muestra el coche anterior al actual.
	 */
	private void mostrarAnterior() {
		mostrarCoche(General.concesionario.get(--indice));
		comprobarTamaño();
	}

	/**
	 * Muestra el coche.
	 * 
	 * @param coche
	 *            coche para mostrar.
	 */
	@SuppressWarnings("unchecked")
	private void mostrarCoche(Coche coche) {
		txtMatricula.setText(coche.getMatricula());
		if (coche.getColor().toString().equals("ROJO"))
			rdbtnRojo.setSelected(true);
		else if (coche.getColor().toString().equals("PLATA"))
			rdbtnPlata.setSelected(true);
		else
			rdbtnAzul.setSelected(true);

		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxModelo.setSelectedItem(coche.getModelo());

	}

	/**
	 * Activa-Desactiva los botones dependiendo si hay siguiente o no y si hay
	 * anterior o no.
	 */
	private void comprobarTamaño() {
		if (General.concesionario.get(indice + 1) == null)
			botonSiguiente.setEnabled(false);
		else
			botonSiguiente.setEnabled(true);

		if (General.concesionario.get(indice - 1) == null)
			botonAnterior.setEnabled(false);
		else
			botonAnterior.setEnabled(true);
	}
}

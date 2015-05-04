package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import pgn.examenMarzo.concesionarioCoches.CocheYaExistenteException;
import pgn.examenMarzo.concesionarioCoches.ColorNoValidoException;
import pgn.examenMarzo.concesionarioCoches.Marca;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;
import pgn.examenMarzo.concesionarioCoches.Modelo;
import pgn.examenMarzo.concesionarioCoches.ModeloNoValidoException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * A&ntilde;ade un coche al concesionario.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class AnnadirCoche extends JDialog {
	/**
	 * Crea un nuevo panel donde se plasmar&aacute;n todos los contenidos de la
	 * ventana.
	 */
	private final JPanel contentPanel = new JPanel();
	/**
	 * Caja de texto donde se almacenar&aacute; la matr&iacute;cula del coche.
	 */
	private JTextField matriculaTxt;
	@SuppressWarnings("rawtypes")
	/**
	 * Caja donde estar&aacute;n todas las marcas de los coches.
	 */
	private JComboBox marcasCBox;
	@SuppressWarnings("rawtypes")
	/**
	 * Caja donde estar&aacute;n todas los modelos de los coches.
	 */
	private JComboBox modelosCBox;
	/**
	 * Bot&oacute;n para el color Rojo.
	 */
	private JRadioButton rojoRButton;
	/**
	 * Bot&oacute;n para el color Rojo.
	 */
	private JRadioButton azulRButton;
	/**
	 * Bot&oacute;n para el color Rojo.
	 */
	private JRadioButton plataRButton;
	/**
	 * Bot&oacute;n para la confirmaci&oacute;n de la creaci&oacute;n del coche.
	 */
	private JButton okButton;
	/**
	 * Agrupaci&oacute;n de botones que contiene todos los botones de los
	 * colores.
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Bot&oacute;n para limpiar los campos de matricula, marca y modelo del
	 * coche.
	 */
	private JButton btnLimpiar;

	/**
	 * Inicializa los contenidos de la ventana principal.
	 */
	public static void main(String[] args) {
		try {
			AnnadirCoche dialog = new AnnadirCoche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AnnadirCoche() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel matriculaLabel = new JLabel("Matricula");
			matriculaLabel.setBounds(48, 16, 62, 14);
			contentPanel.add(matriculaLabel);
		}
		{
			matriculaTxt = new JTextField();
			matriculaTxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					matriculaTxt.setForeground(Color.BLACK);
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (!pgn.examenMarzo.concesionarioCoches.Coche
							.esValida(matriculaTxt.getText())) {
						matriculaTxt.setForeground(Color.RED);

					} else {
						matriculaTxt.setForeground(Color.BLACK);
					}
				}
			});
			matriculaTxt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});
			matriculaTxt
					.setToolTipText("Formato: 1234-BBB (no se admite A, E, I, O, U o \u00D1)");
			matriculaTxt.setBounds(115, 13, 86, 20);
			contentPanel.add(matriculaTxt);
			matriculaTxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Colores",
					TitledBorder.LEADING, TitledBorder.TOP, null));
			panel.setBounds(26, 80, 121, 98);
			contentPanel.add(panel);
			panel.setLayout(null);

			rojoRButton = new JRadioButton("Rojo");
			rojoRButton.setSelected(true);
			rojoRButton.setToolTipText("Color del coche rojo");
			buttonGroup.add(rojoRButton);
			rojoRButton.setBounds(6, 16, 109, 23);
			panel.add(rojoRButton);
			rojoRButton.setForeground(new Color(255, 0, 51));

			plataRButton = new JRadioButton("Plata");
			plataRButton.setToolTipText("Color del coche plata");
			buttonGroup.add(plataRButton);
			plataRButton.setBounds(6, 42, 109, 23);
			panel.add(plataRButton);
			plataRButton.setForeground(Color.GRAY);

			azulRButton = new JRadioButton("Azul");
			azulRButton.setToolTipText("Color del coche azul");
			buttonGroup.add(azulRButton);
			azulRButton.setBounds(6, 68, 109, 23);
			panel.add(azulRButton);
			azulRButton.setForeground(Color.BLUE);
		}

		JLabel marcasLabel = new JLabel("Marcas");
		marcasLabel.setBounds(173, 89, 46, 14);
		contentPanel.add(marcasLabel);

		marcasCBox = new JComboBox();
		marcasCBox.setToolTipText("Elige una marca entre las disponibles");
		marcasCBox.setModel(new DefaultComboBoxModel(Marca.values()));
		marcasCBox.setBounds(229, 89, 132, 32);
		contentPanel.add(marcasCBox);

		JLabel modelosLabel = new JLabel("Modelos");
		modelosLabel.setBounds(173, 155, 62, 14);
		contentPanel.add(modelosLabel);

		modelosCBox = new JComboBox();
		modelosCBox.setModel(new DefaultComboBoxModel(Modelo.values()));
		modelosCBox.setToolTipText("Elige un modelo entre los disponibles");
		modelosCBox.setBounds(229, 146, 132, 32);
		contentPanel.add(modelosCBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});

				btnLimpiar = new JButton("Limpiar");
				btnLimpiar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						matriculaTxt.setText("");
						rojoRButton.setSelected(false);
						plataRButton.setSelected(false);
						azulRButton.setSelected(false);
						marcasCBox.setSelectedIndex(-1);
						modelosCBox.setSelectedIndex(-1);
						azulRButton.setSelected(false);
					}
				});
				buttonPane.add(btnLimpiar);
				{
					okButton = new JButton("A\u00F1adir");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							alta();

						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}

		marcasCBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				modelosCBox.setModel(new DefaultComboBoxModel(
						getModelo(marcasCBox)));
			}
		});

	}

	/**
	 * Alta del coche.
	 */
	protected void alta() {
		try {
			if (General.concesionario.annadir(matriculaTxt.getText(),
					getColorSeleccionado(),
					(Modelo) modelosCBox.getSelectedItem())) {
				JOptionPane.showMessageDialog(okButton,
						"Coche añadido con éxito");
			}

		} catch (HeadlessException | MatriculaNoValidaException
				| ColorNoValidoException | ModeloNoValidoException
				| CocheYaExistenteException e) {

			JOptionPane.showMessageDialog(contentPanel,
					"El coche no se ha podido añadir.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Dependiendo del color seleccionado devuelve un color u otro.
	 * 
	 * @return Color para almacenar
	 */
	private pgn.examenMarzo.concesionarioCoches.Color getColorSeleccionado() {
		if (azulRButton.isSelected()) {
			return pgn.examenMarzo.concesionarioCoches.Color.AZUL;
		} else if (plataRButton.isSelected()) {
			return pgn.examenMarzo.concesionarioCoches.Color.PLATA;
		}
		return pgn.examenMarzo.concesionarioCoches.Color.ROJO;

	}

	/**
	 * Obtiene el modelo del coche.
	 * 
	 * @param comboBoxMarca
	 *            ComboBox de marca.
	 * @return devuelve la marca del coche en cuesti&oacute;n
	 */
	private Object[] getModelo(JComboBox<Marca> comboBoxMarca) {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca)
				modelos.add(m);
		}
		return modelos.toArray();
	}
}

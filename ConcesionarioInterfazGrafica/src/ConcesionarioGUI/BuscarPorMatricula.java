package ConcesionarioGUI;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.CocheYaExistenteException;
import pgn.examenMarzo.concesionarioCoches.Marca;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;
import pgn.examenMarzo.concesionarioCoches.Modelo;

import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Busca un coche por su matr&iacute;cula.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings({ "serial", "unused" })
public class BuscarPorMatricula extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField matriculaTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rojoButton;
	private JRadioButton plataButton;
	private JRadioButton azulButton;
	@SuppressWarnings("rawtypes")
	private JComboBox marcaCBox;
	@SuppressWarnings("rawtypes")
	private JComboBox modeloCBox;
	private JButton okButton;

	/**
	 * Lanza la aplicaci&oacute;n.
	 */
	public static void main(String[] args) {
		try {
			BuscarPorMatricula dialog = new BuscarPorMatricula();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BuscarPorMatricula() {
		setModal(true);
		setBounds(100, 100, 449, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			matriculaTxt = new JTextField();
			matriculaTxt.setBounds(95, 32, 86, 20);
			contentPanel.add(matriculaTxt);
			matriculaTxt.setColumns(10);
		}
		{
			JLabel matriculaLabel = new JLabel("Matr\u00EDcula");
			matriculaLabel.setBounds(29, 35, 56, 14);
			contentPanel.add(matriculaLabel);
		}

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Colores", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(29, 74, 121, 98);
		contentPanel.add(panel);
		panel.setLayout(null);

		rojoButton = new JRadioButton("Rojo");
		rojoButton.setBounds(6, 16, 109, 23);
		panel.add(rojoButton);
		buttonGroup.add(rojoButton);
		rojoButton.setEnabled(false);

		plataButton = new JRadioButton("Plata");
		plataButton.setBounds(6, 42, 109, 23);
		panel.add(plataButton);
		buttonGroup.add(plataButton);
		plataButton.setEnabled(false);

		azulButton = new JRadioButton("Azul");
		azulButton.setBounds(6, 68, 109, 23);
		panel.add(azulButton);
		buttonGroup.add(azulButton);
		azulButton.setEnabled(false);

		marcaCBox = new JComboBox();
		marcaCBox.setEnabled(false);
		marcaCBox.setModel(new DefaultComboBoxModel(Marca.values()));
		marcaCBox.setBounds(230, 74, 86, 35);
		contentPanel.add(marcaCBox);

		modeloCBox = new JComboBox();
		modeloCBox.setEnabled(false);
		modeloCBox.setModel(new DefaultComboBoxModel(Modelo.values()));
		modeloCBox.setBounds(230, 137, 86, 35);
		contentPanel.add(modeloCBox);

		JLabel marcaLabel = new JLabel("Marca");
		marcaLabel.setBounds(179, 84, 41, 14);
		contentPanel.add(marcaLabel);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(179, 147, 46, 14);
		contentPanel.add(lblModelo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarCoche();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	/**
	 * Muestra el coche.
	 */
	@SuppressWarnings("unchecked")
	protected void mostrarCoche() {
		try {
			Coche coche = General.concesionario.get(matriculaTxt.getText());
			if (coche.getColor().toString().equals("ROJO")) {
				rojoButton.setSelected(true);

			} else if (coche.getColor().toString().equals("PLATA")) {
				plataButton.setSelected(true);

			} else if (coche.getColor().toString().equals("AZUL")) {
				azulButton.setSelected(true);
			}
			marcaCBox.addItem(coche.getModelo().getMarca());
			marcaCBox.setSelectedItem(coche.getModelo().getMarca());
			modeloCBox.addItem(coche.getModelo());
			modeloCBox.setSelectedItem(coche.getModelo());
		} catch (MatriculaNoValidaException | CocheNoExisteException e) {
			JOptionPane.showMessageDialog(okButton,
					"No se ha podido encontrar", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}

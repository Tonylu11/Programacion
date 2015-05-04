package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;

/**
 * Elimina un coche del concesionario.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class EliminarCoche extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField matriculaTxt;
	private JButton okButton;

	/**
	 * Lanza la aplicaci&oacute;n.
	 */
	public static void main(String[] args) {
		try {
			EliminarCoche dialog = new EliminarCoche();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public EliminarCoche() {
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(100, 100, 329, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		matriculaTxt = new JTextField();
		matriculaTxt.setBounds(108, 36, 86, 20);
		contentPanel.add(matriculaTxt);
		matriculaTxt.setColumns(10);

		JLabel lblInserteSuMatrcula = new JLabel(
				"Inserte su matr\u00EDcula para eliminarlo");
		lblInserteSuMatrcula.setBounds(45, 11, 215, 14);
		contentPanel.add(lblInserteSuMatrcula);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							eliminar();
						} catch (MatriculaNoValidaException
								| CocheNoExisteException e) {
							e.printStackTrace();
						}
					}

					private void eliminar() throws MatriculaNoValidaException,
							CocheNoExisteException {
						try {
							if (General.concesionario.eliminar(matriculaTxt
									.getText())) {
								JOptionPane.showMessageDialog(okButton,
										"Coche eliminado con éxito");
								// setModificado(true);
							} else
								JOptionPane
										.showMessageDialog(
												contentPanel,
												"El coche no se encuentra en el concesionario.",
												"Error",
												JOptionPane.ERROR_MESSAGE);
						} catch (HeadlessException | MatriculaNoValidaException
								| CocheNoExisteException e) {
							JOptionPane.showMessageDialog(okButton,
									"No se ha podido añadir", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

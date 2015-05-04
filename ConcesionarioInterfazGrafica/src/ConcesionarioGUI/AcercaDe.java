package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Antonio Luque Bravo
 */
@SuppressWarnings("serial")
public class AcercaDe extends JDialog {
	/**
	 * Crea un nuevo panel donde se plasmar&aacute;n todos los contenidos de la
	 * ventana.
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * Lanza la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		try {
			AcercaDe dialog = new AcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inicializa los contenidos de la ventana principal.
	 */
	public AcercaDe() {
		setModal(true);
		setBounds(100, 100, 389, 211);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnAcercaDeConcesionario = new JTextPane();
			txtpnAcercaDeConcesionario.setEditable(false);
			txtpnAcercaDeConcesionario.setFont(new Font("Calibri Light",
					Font.PLAIN, 15));
			txtpnAcercaDeConcesionario
					.setText("Acerca de Concesionario de Coches IES Gran Capit\u00E1n\r\nVersi\u00F3n: 4.0\r\nCreado por Antonio Luque Bravo\r\nCurso: 1\u00BADAW \r\nCopyright \u00A9 All Right Reserved");
			txtpnAcercaDeConcesionario.setBounds(10, 11, 342, 127);
			contentPanel.add(txtpnAcercaDeConcesionario);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Atr\u00E1s");
				okButton.addActionListener(new ActionListener() {
					/**
					 * Acci&oacute;n que lleva a cabo el bot&oacute;n
					 * Atr&aacute;s
					 */
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}

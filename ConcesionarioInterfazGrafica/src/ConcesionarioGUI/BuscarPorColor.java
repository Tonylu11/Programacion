package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

/**
 * Busca un coche por color.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class BuscarPorColor extends AnnadirCoche {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Lanza la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		try {

			BuscarPorColor dialog = new BuscarPorColor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public BuscarPorColor() {
		super();
		setModal(true);
		setTitle("B\u00FAsqueda por Color");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Colores",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel);
			{
				JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
						"New radio button");
				panel.add(rdbtnNewRadioButton_1);
				buttonGroup.add(rdbtnNewRadioButton_1);
			}
			{
				JRadioButton rdbtnNewRadioButton = new JRadioButton(
						"New radio button");
				panel.add(rdbtnNewRadioButton);
				buttonGroup.add(rdbtnNewRadioButton);
			}
			{
				JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
						"New radio button");
				panel.add(rdbtnNewRadioButton_2);
				buttonGroup.add(rdbtnNewRadioButton_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

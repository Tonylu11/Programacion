package ConcesionarioGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Ayuda del concesionario de coches.
 * 
 * @author Antonio Luque Bravo
 *
 */
@SuppressWarnings("serial")
public class Ayuda extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Lanza la aplicaci&oacute;n.
	 */
	public static void main(String[] args) {
		try {
			Ayuda dialog = new Ayuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el di&aacute;logo.
	 */
	public Ayuda() {
		setModal(true);
		setTitle("Ayuda");
		setBounds(100, 100, 589, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 573, 330);
		contentPanel.add(scrollPane);

		JEditorPane editor = new JEditorPane();
		editor.setEditable(false);
		editor.setContentType("text/html");
		editor.setText("<h1>Ayuda para Concesionario I.E.S Gran Capit&aacute;n</h1>\r\n<h2>Men&uacute;s implementados:</h2>\r\n<ol>\r\n\t<li>Men&uacute; Archivo contiene las siguientes opciones:</li>\r\n\t<ul>\r\n\t\t<li>Nuevo (Tecla de acceso r&aacute;pido Ctrl+N): Crea un nuevo concesionario de coches, pidiendo guardar si hubo alg&uacute;n concesionario anterior sin guardar.</li>\r\n\t\t<li>Abrir (Tecla de acceso r&aacute;pido Ctrl+O): Abre un archivo que contenga un concesionario previamente guardado, debe contener la extensi&oacute;n .obj</li>\r\n\t\t<li>Guardar (Tecla de acceso r&aacute;pido Ctrl+G): Guarda un concesionario en la carpeta que le indiques</li>\r\n\t\t<li>Guardar como: Guarda un concesionario en la carpeta que le indiques.</li>\r\n\t\t<li>Salir: Sale del programa.</li>\r\n\t</ul>\r\n\t<li>Men&uacute; Edici&oacute;n contiene las siguientes opciones:</li>\r\n\t<ul>\r\n\t\t<li>A&ntilde;adir coche (Tecla de acceso r&aacute;pido Ctrl+A): A&ntilde;ade un coche al concesionario de I.E.S Gran Capit&aacute;n, pidiendo su matr&iacute;cula, color, marca y modelo.</li>\r\n\t\t<li>Eliminar coche (Tecla de acceso r&aacute;pido Ctrl+E): Elimina un coche del concesionario por su matr&iacute;cula.</li>\r\n\t</ul>\r\n\t<li>Men&uacute; Ver contiene las siguientes opciones:</li>\r\n\t<ul>\r\n\t\t<li>Buscar por matr&iacute;cula (Tecla de acceso r&aacute;pido Ctrl+M): Busca un coche del concesionario por su matr&iacute;cula, mostrando su color, marca y modelo correspondientes.</li>\r\n\t\t<li>Buscar por color (Tecla de acceso r&aacute;pido Ctrl+C): Busca un coche del concesionario por su color, mostrando su matr&iacute;cula, marca y modelo.</li>\r\n\t\t<li>N&uacute;mero total de coches (Tecla de acceso r&aacute;pido Ctrl+T): Muestra en un ventana de informaci&oacute;n el n&uacute;mero de coches que hay en el concesionario en ese momento.</li>\r\n\t\t<li>Mostrar concesionario (Tecla de acceso r&aacute;pido Alt+S): Muestra los coches existentes en el concesionario con su matr&iacute;cula, color, marca y modelo.</li>\r\n\t</ul>\r\n\t<li>Men&uacute; Ayuda contiene las siguientes opciones:</li>\r\n\t<ul>\r\n\t\t<li>Ayuda (Tecla de acceso r&aacute;pido Ctrl+H): Manual de los distintos men&uacute;s que implementa este programa.</li>\r\n\t\t<li>Acerca de..: Muestra informaci&oacute; del creador, su versi&oacute;n y el tipo de licencia que posee el programa.</li>\r\n\t</ul>\r\n</ol>\r\n");
		scrollPane.setViewportView(editor);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				JButton okButton = new JButton("Atr\u00E1s");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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
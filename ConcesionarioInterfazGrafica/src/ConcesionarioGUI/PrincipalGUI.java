package ConcesionarioGUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.Fichero;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Paquetes. Deberás olvidarte del paquete utiles. Necesitas un paquete sólo
 * para el GUI, donde tendrás la clase Principal.java que abrirá la ventana
 * principal. Recuerda que las excepciones han de estar en el paquete que las
 * crean. La página principal tendrá como título el fichero abierto
 * ("Sin título" en caso de no tener asociado ningún fichero). Será al estilo
 * del Notepad. Una barra de menú (JMenuBar) con los siguientes menús
 * (JMenu):Ficheros, Coches, Ayuda. Tendrá las teclas de acceso rápido asociadas
 * a cada elemento del menú, así como mnemotécnicos. Añade también separadores
 * entre elementos del menú. Será al estilo del Notepad. Un diálogo para
 * trabajar con ficheros (JFileChooser). Ha de usar un filtro para visualizar
 * los ficheros con extensión ".obj" Un diálogo para trabajar con Coches
 * (JDialog). El diálogo contendrá:
 * 
 * Una caja de texto (JTextField) debidamente etiquetada(JLabel) para las
 * matrículas, Al perder el foco cambiará el color si la matrícula no es válida.
 * Al editar, la matrícula siempre lo hará en negro. Una lista (JList) para el
 * modelo y otra para la marca. Las listas han de estar sincronizadas. Tres
 * radio buttons con los tres colores (tres JRadioButton y un ButtonGroup
 * Selecciona los tres, menú contextual > set ButtonGroup con un borde
 * etiquetado Color Selecciona los tres, menú contextual > Surround with >
 * javax.swing.JPanel border) y Botones (JButton) para Salir o realizar la
 * acción (crear, eliminar, mostrar...).
 * 
 * Los mensajes de error (JOptionPane) han de ser lo más conciso posible. Quizás
 * deban basarse en el atributo message de las excepciones. El menú ayuda
 * dispondrá de un Ver ayuda y Acerca de...
 * 
 * @author Antonio Luque Bravo
 * @version 4.0
 *
 */
@SuppressWarnings("unused")
public class PrincipalGUI {
	/**
	 * Ventana principal del programa.
	 */
	private JFrame frame;
	/**
	 * Ventana vacía del programa.
	 */
	private static JFrame nuevo = new JFrame();
	/**
	 * Para poder llamar a AnnadirCoche debemos crear un objeto con su tipo
	 */
	protected AnnadirCoche annadirCoche = new AnnadirCoche();
	/**
	 * Para poder llamar a EliminarCoche debemos crear un objeto con su tipo
	 */
	protected EliminarCoche eliminarCoche = new EliminarCoche();
	/**
	 * Para poder llamar a BuscarPorMatricula debemos crear un objeto con su
	 * tipo
	 */
	protected BuscarPorMatricula buscarPorMatricula = new BuscarPorMatricula();
	/**
	 * Para poder llamar a MostrarConcesionario debemos crear un objeto con su
	 * tipo
	 */
	protected MostrarConcesionario mostrarConcesionario = new MostrarConcesionario();
	/**
	 * Para poder llamar a MostrarPorColor debemos crear un objeto con su tipo
	 */
	protected MostrarPorColor mostrarPorColor = new MostrarPorColor();
	/**
	 * Para poder llamar a AcercaDe debemos crear un objeto con su tipo
	 */
	protected AcercaDe acercaDe = new AcercaDe();
	/**
	 * Para poder llamar a Ayuda debemos crear un objeto con su tipo
	 */
	protected Ayuda ayuda = new Ayuda();
	/**
	 * Aqu&iacute; se declara un JMenuItem para ver el n&uacute;mero total de
	 * coches en el concesionario.
	 */
	private JMenuItem mntmNmeroTotalDe;
	/**
	 * Se declara un archivo llamado file del tipo File.
	 */
	private File file;
	/**
	 * Se crea un filtro para s&oacute;lo poder ver los archivos terminados en
	 * .obj
	 */
	private FileNameExtensionFilter filtro = new FileNameExtensionFilter(
			"Archivos .obj", "obj");
	/**
	 * Se declara un JMenuItem llamado mntmNuevo para crear un concesionario
	 * nuevo.
	 */
	private JMenuItem mntmNuevo;

	/**
	 * M&eacute;todo Main para lanzar la aplicaci&oacute;n
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalGUI window = new PrincipalGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicaci&oacute;n
	 */
	public PrincipalGUI() {
		initialize();
	}

	/**
	 * Inicializa los contenidos de la ventana principal.
	 */
	private void initialize() {
		frame = new JFrame("Concesionario IES Gran Capitán");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/principal.png"))
				.getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(37, 28, 168, 174);
		frame.getContentPane().add(label);

		JLabel lblConcesionario = new JLabel("Concesionario");
		lblConcesionario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConcesionario.setBounds(245, 90, 132, 27);
		frame.getContentPane().add(lblConcesionario);

		JLabel lblNewLabel = new JLabel("I.E.S. Gran Capit\u00E1n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(233, 128, 168, 14);
		frame.getContentPane().add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFicheros = new JMenu("Archivo");
		menuBar.add(mnFicheros);

		mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute;n que llevar&aacute; a cabo el bot&oacute;n Nuevo.
			 */
			public void actionPerformed(ActionEvent arg0) {
				if (General.concesionario.isModificado()) {
					int opcion = JOptionPane.showConfirmDialog(nuevo,
							"¿Deseas guardar el concesionario?");
					if (opcion == 0) {
						JFileChooser guardar = new JFileChooser();
						guardar.setFileFilter(filtro);
						opcion = guardar.showSaveDialog(frame);
						if (opcion == JFileChooser.APPROVE_OPTION) {
							file = guardar.getSelectedFile();
							try {
								Fichero.guardar(General.concesionario, file);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						General.concesionario = new Concesionario();
					} else if (opcion == 2)
						nuevo.setVisible(false);
					else
						General.concesionario = new Concesionario();
				} else
					General.concesionario = new Concesionario();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnFicheros.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir...");
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		mntmAbrir.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Abrir.
			 */
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser abrir = new JFileChooser();
				abrir.setFileFilter(filtro);
				int opcion = abrir.showOpenDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					file = abrir.getSelectedFile();
					try {
						General.concesionario = (Concesionario) Fichero
								.leer(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					General.concesionario.setModificado(false);
				}
			}
		});
		mnFicheros.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Guardar.
			 */
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser guardarComo = new JFileChooser();
				guardarComo.setFileFilter(filtro);
				int opcion = guardarComo.showSaveDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					file = guardarComo.getSelectedFile();
					try {
						Fichero.guardar(General.concesionario, file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					General.concesionario.setModificado(false);
				}
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				InputEvent.CTRL_MASK));
		mnFicheros.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Guardar
			 * como.
			 */
			public void actionPerformed(ActionEvent e) {
				JFileChooser guardarComo = new JFileChooser();
				guardarComo.setFileFilter(filtro);
				int opcion = guardarComo.showSaveDialog(frame);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					file = guardarComo.getSelectedFile();
					try {
						Fichero.guardar(General.concesionario, file);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					General.concesionario.setModificado(false);
				}
			}
		});
		mnFicheros.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnFicheros.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (General.concesionario.isModificado()) {
					int opcion = JOptionPane.showConfirmDialog(nuevo,
							"¿Deseas guardar el concesionario?");
					if (opcion == 0) {
						JFileChooser guardar = new JFileChooser();
						guardar.setFileFilter(filtro);
						opcion = guardar.showSaveDialog(frame);
						if (opcion == JFileChooser.APPROVE_OPTION) {
							file = guardar.getSelectedFile();
							try {
								Fichero.guardar(General.concesionario, file);
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						System.exit(0);
					} else if (opcion == 2)
						nuevo.setVisible(false);
					else
						System.exit(0);
				} else
					System.exit(0);
			}
		});
		mnFicheros.add(mntmSalir);

		JMenu mnEdicin = new JMenu("Edici\u00F3n");
		menuBar.add(mnEdicin);

		JMenuItem mntmAadirCoche = new JMenuItem("A\u00F1adir coche");
		mntmAadirCoche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mntmAadirCoche.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n
			 * A&nacute;adir coche.
			 */
			public void actionPerformed(ActionEvent e) {
				annadirCoche.setVisible(true);

			}
		});
		mnEdicin.add(mntmAadirCoche);

		JMenuItem mntmNewMenuItem = new JMenuItem("Eliminar coche");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Eliminar
			 * coche.
			 */
			public void actionPerformed(ActionEvent e) {
				eliminarCoche.setVisible(true);
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_MASK));
		mnEdicin.add(mntmNewMenuItem);

		JMenu mnVer = new JMenu("Ver");
		menuBar.add(mnVer);

		JMenuItem mntmBuscarPorMatrcula = new JMenuItem(
				"Buscar por matr\u00EDcula");
		mntmBuscarPorMatrcula.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_M, InputEvent.CTRL_MASK));
		mntmBuscarPorMatrcula.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Buscar por
			 * Matr&iacute;cula.
			 */
			public void actionPerformed(ActionEvent e) {
				buscarPorMatricula.setVisible(true);
			}
		});
		mnVer.add(mntmBuscarPorMatrcula);

		JMenuItem mntmBuscarPorColor = new JMenuItem("Buscar por color");
		mntmBuscarPorColor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));
		mntmBuscarPorColor.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Buscar por
			 * Color.
			 */
			public void actionPerformed(ActionEvent arg0) {
				mostrarPorColor.setVisible(true);
			}
		});
		mnVer.add(mntmBuscarPorColor);

		mntmNmeroTotalDe = new JMenuItem("N\u00FAmero total de coches");
		mntmNmeroTotalDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				InputEvent.CTRL_MASK));
		mntmNmeroTotalDe.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n
			 * N&uacute;mero total de coches.
			 */
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmNmeroTotalDe,
						"Número total de coches en el concesionario: "
								+ General.concesionario.size());
			}
		});
		mnVer.add(mntmNmeroTotalDe);

		JMenuItem mntmMostrarConcesionario = new JMenuItem(
				"Mostrar concesionario");
		mntmMostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, InputEvent.ALT_MASK));
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Mostrar
			 * Concesionario.
			 */
			public void actionPerformed(ActionEvent e) {
				mostrarConcesionario.setVisible(true);
			}
		});
		mnVer.add(mntmMostrarConcesionario);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			/**
			 * Acci&oacute; que llevar&aacute; a cabo el bot&oacute;n Acerca
			 * De..
			 */
			public void actionPerformed(ActionEvent e) {
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda.setVisible(true);
			}
		});
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_MASK));
		mnAyuda.add(mntmAyuda);
		mnAyuda.add(mntmAcercaDe);
	}
}

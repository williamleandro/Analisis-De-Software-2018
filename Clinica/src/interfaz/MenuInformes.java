package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utilities.Log;
import utilities.Utils;

import java.awt.Desktop;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MenuInformes {

	private JFrame frame;
	private MenuInformes menu;
	private MenuPrincipal menuPrincipal;
	
	/**
	 * Create the application.
	 * @param menu2 
	 */
	public MenuInformes(MenuPrincipal menuPrincipal) {
		this.menuPrincipal=menuPrincipal;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		this.menu = this;
		frame.setBounds(100, 100, 640, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Sistema de Control de Pacientes - Cl�nica Nuevo Espiritu");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Informes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(241, 16, 111, 43);
		frame.getContentPane().add(lblNewLabel);

		JButton btnListadoDePacientes = new JButton("Listado de pacientes por m\u00E9dico");
		btnListadoDePacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InformePaciente(menu);
				frame.setVisible(false);
			}
		});
		btnListadoDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnListadoDePacientes.setBounds(146, 116, 307, 50);
		frame.getContentPane().add(btnListadoDePacientes);

		JButton btnEnfermedadesQueAtiende = new JButton("Enfermedades por m\u00E9dico");
		btnEnfermedadesQueAtiende.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new InformeMedico(menu);
				frame.setVisible(false);
			}
		});
		btnEnfermedadesQueAtiende.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEnfermedadesQueAtiende.setBounds(146, 222, 307, 50);
		frame.getContentPane().add(btnEnfermedadesQueAtiende);

		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPrincipal.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAtras.setBounds(146, 329, 307, 50);
		frame.getContentPane().add(btnAtras);
		
		JButton btnAyuda = new JButton("?");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File(Utils.obtenerUrlAyuda());
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					Log.getInstance().error(e.getMessage());
				}
			}
		});
		btnAyuda.setBounds(581, 0, 43, 23);
		frame.getContentPane().add(btnAyuda);

	}

	public void setVisible(boolean visible) {
		this.frame.setVisible(visible);
	}
}
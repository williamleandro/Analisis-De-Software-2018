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

public class MenuPrincipal {

	private JFrame frame;
	private Login login;
	private MenuPrincipal menu;

	/**
	 * @wbp.parser.constructor
	 */
	public MenuPrincipal(Login login) {
		this.login = login;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.menu = this;
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sistema de Control de Pacientes - Cl�nica Nuevo Espiritu");
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMenuPrincipal.setBounds(210, 16, 176, 38);
		frame.getContentPane().add(lblMenuPrincipal);

		JButton btnIngresoDeDatos = new JButton("Ingreso de Datos");
		btnIngresoDeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuIngresoPacientes(menu);
				frame.setVisible(false);
			}
		});
		btnIngresoDeDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnIngresoDeDatos.setBounds(184, 112, 216, 50);
		frame.getContentPane().add(btnIngresoDeDatos);

		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MenuInformes(menu);
				frame.setVisible(false);
			}
		});
		btnInformes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInformes.setBounds(184, 202, 216, 50);
		frame.getContentPane().add(btnInformes);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				login.closeApp();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSalir.setBounds(184, 374, 216, 50);
		frame.getContentPane().add(btnSalir);

		JButton btnRegistro = new JButton("Registro de Usuario");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Registro(menu);
				frame.setVisible(false);
			}
		});

		btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistro.setBounds(184, 294, 216, 50);
		frame.getContentPane().add(btnRegistro);
		
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
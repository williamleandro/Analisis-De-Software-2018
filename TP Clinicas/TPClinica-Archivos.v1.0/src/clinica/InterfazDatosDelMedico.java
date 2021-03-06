package clinica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InterfazDatosDelMedico extends JFrame {

	private JPanel contentPane;
	private JButton btnCancelar;
	private JLabel lblFondoCentroMedico;
	private JLabel lblControlDePacientes;
	private JLabel lblDatosDelMedico;
	private JLabel lblFondoControlDePacientes;
	private JLabel lblFondoDatosDelMedico;
	private JLabel lblFondo;
	private JButton btnConfirmar;
	private JLabel lblCodMedico;
	private JLabel lblNombre;
	private JLabel lblEspecialidades;
	private JLabel lblMatricula;
	private JLabel lblTelefono;
	private JTextField texCodPaciente;
	private JTextField texNombre;
	private JTextField texMatricula;
	private JTextField texTelefono;
	private JLabel lblCentroMedico;
	private JTextArea texEspecialidades;
	private JLabel lblFechaDeIngreso;
	private JTextField texFechaDeIngreso;
	
	private int ultimoCod= -1;
	private Medico medico;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazDatosDelMedico frame = new InterfazDatosDelMedico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazDatosDelMedico() {
		
		//inicio de busqueda del ultimo codigo asignado
		
		try{
			FileReader input = new FileReader("./src/Archivos\\Medicos.txt");
			BufferedReader bufInput = new BufferedReader(input);
			
			String line;
			
			line = bufInput.readLine();
			
			while(line != null){
				String [] datos;
				
				datos = line.split(" ");
							
				ultimoCod = Integer.parseInt(datos[0]);
				
				line = bufInput.readLine();
			}
			
			ultimoCod++; //Lo utilizo incrementado en 1
			
			bufInput.close();
			
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//fin de busqueda del ultimo codigo asignado
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCentroMedico = new JLabel("Centro Medico");
		lblCentroMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCentroMedico.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblCentroMedico.setBackground(Color.WHITE);
		lblCentroMedico.setBounds(32, 12, 378, 26);
		contentPane.add(lblCentroMedico);
		
		lblFondoCentroMedico = new JLabel("");
		lblFondoCentroMedico.setIcon(new ImageIcon("./src/Source\\borde.png"));
		lblFondoCentroMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondoCentroMedico.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblFondoCentroMedico.setBackground(Color.WHITE);
		lblFondoCentroMedico.setBounds(32, 12, 378, 26);
		contentPane.add(lblFondoCentroMedico);
		
		lblControlDePacientes = new JLabel("Control de Pacientes");
		lblControlDePacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblControlDePacientes.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblControlDePacientes.setBackground(Color.WHITE);
		lblControlDePacientes.setBounds(32, 92, 378, 26);
		contentPane.add(lblControlDePacientes);
		
		lblFondoControlDePacientes = new JLabel("");
		lblFondoControlDePacientes.setIcon(new ImageIcon("./src/Source\\borde.png"));
		lblFondoControlDePacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondoControlDePacientes.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		lblFondoControlDePacientes.setBackground(Color.WHITE);
		lblFondoControlDePacientes.setBounds(32, 92, 378, 26);
		contentPane.add(lblFondoControlDePacientes);
		
		lblDatosDelMedico = new JLabel("\"Datos Del Medico\"");
		lblDatosDelMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDelMedico.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblDatosDelMedico.setBackground(Color.WHITE);
		lblDatosDelMedico.setBounds(32, 41, 378, 47);
		contentPane.add(lblDatosDelMedico);
		
		lblFondoDatosDelMedico = new JLabel("");
		lblFondoDatosDelMedico.setIcon(new ImageIcon("./src/Source\\borde2.png"));
		lblFondoDatosDelMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondoDatosDelMedico.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblFondoDatosDelMedico.setBackground(Color.WHITE);
		lblFondoDatosDelMedico.setBounds(32, 41, 378, 47);
		contentPane.add(lblFondoDatosDelMedico);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//al cancelar el ingreso de datos del medico se regresa a la ventana anterior que es ingreso de datos
				//genero una ventana de ingreso de datos
				InterfazIngresoDeDatos iIngresoDeDatos=new InterfazIngresoDeDatos();
				//pongo visible la ventana de ingreso de datos
				iIngresoDeDatos.setVisible(true);
				//cierro la ventana de ingreso de datos del medico
				InterfazDatosDelMedico.this.dispose();
			}
		});
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnCancelar.setBackground(new Color(0, 128, 128));
		btnCancelar.setBounds(10, 476, 138, 23);
		contentPane.add(btnCancelar);
		
		btnConfirmar = new JButton("Confirmar");
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String [] enfermedades = texEspecialidades.getText().split(" ");
				
				medico = new Medico(ultimoCod, texMatricula.getText(), texNombre.getText(), enfermedades, texFechaDeIngreso.getText(), Integer.parseInt(texTelefono.getText()));
				
				//registro de la informacion en el Archivo Medicos
				
				FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		            fichero = new FileWriter("./src/Archivos\\Medicos.txt", true); //Con pasarle por parametro el true agrega al final sin borrar el contenido
		            pw = new PrintWriter(fichero);
		            
		            pw.println(medico.toString());

		        } catch (Exception e1) {
		            e1.printStackTrace();
		        } finally {
		           try {
		           // Nuevamente aprovechamos el finally para 
		           // asegurarnos que se cierra el fichero.
		           if (null != fichero)
		              fichero.close();
		           } catch (Exception e2) {
		              e2.printStackTrace();
		           }
		        }
				
				//fin registro
		        
				//suponemos que todos los datos ingresados son correctos y se regresa a la ventana de ingreso de datos
				//genero una ventana de ingreso de datos
				InterfazIngresoDeDatos iIngresoDeDatos=new InterfazIngresoDeDatos();
				//pongo visible la ventana de ingreso de datos
				iIngresoDeDatos.setVisible(true);
				//cierro la ventana de ingreso de datos del medico
				InterfazDatosDelMedico.this.dispose();
			}
		});
		
		btnConfirmar.setForeground(Color.BLACK);
		btnConfirmar.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		btnConfirmar.setBackground(new Color(0, 128, 128));
		btnConfirmar.setBounds(286, 476, 138, 23);
		contentPane.add(btnConfirmar);
		
		lblCodMedico = new JLabel("Cod. Medico");
		lblCodMedico.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodMedico.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblCodMedico.setBackground(Color.WHITE);
		lblCodMedico.setBounds(10, 134, 131, 26);
		contentPane.add(lblCodMedico);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(32, 171, 83, 26);
		contentPane.add(lblNombre);
		
		lblEspecialidades = new JLabel("Especialidades:");
		lblEspecialidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspecialidades.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblEspecialidades.setBackground(Color.WHITE);
		lblEspecialidades.setBounds(10, 336, 138, 26);
		contentPane.add(lblEspecialidades);
		
		lblMatricula = new JLabel("Matricula");
		lblMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatricula.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblMatricula.setBackground(Color.WHITE);
		lblMatricula.setBounds(30, 208, 93, 26);
		contentPane.add(lblMatricula);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblTelefono.setBackground(Color.WHITE);
		lblTelefono.setBounds(32, 250, 83, 26);
		contentPane.add(lblTelefono);
		
		lblFechaDeIngreso = new JLabel("Fecha de Ingreso");
		lblFechaDeIngreso.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDeIngreso.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblFechaDeIngreso.setBackground(Color.WHITE);
		lblFechaDeIngreso.setBounds(1, 287, 147, 26);
		contentPane.add(lblFechaDeIngreso);
		
		texCodPaciente = new JTextField();
		texCodPaciente.setEditable(false);
		texCodPaciente.setText("" + ultimoCod);
		texCodPaciente.setBounds(168, 138, 242, 20);
		contentPane.add(texCodPaciente);
		texCodPaciente.setColumns(10);
		
		texNombre = new JTextField();
		texNombre.setColumns(10);
		texNombre.setBounds(168, 175, 242, 20);
		contentPane.add(texNombre);
		
		texMatricula = new JTextField();
		texMatricula.setColumns(10);
		texMatricula.setBounds(168, 212, 242, 20);
		contentPane.add(texMatricula);
		
		texTelefono = new JTextField();
		texTelefono.setColumns(10);
		texTelefono.setBounds(168, 254, 242, 20);
		contentPane.add(texTelefono);
		
		texFechaDeIngreso = new JTextField();
		texFechaDeIngreso.setColumns(10);
		texFechaDeIngreso.setBounds(168, 293, 242, 20);
		contentPane.add(texFechaDeIngreso);
		
		texEspecialidades = new JTextArea();
		texEspecialidades.setFont(new Font("Tahoma", Font.PLAIN, 11));
		texEspecialidades.setLineWrap(true);
		texEspecialidades.setBounds(10, 373, 390, 92);
		contentPane.add(texEspecialidades);
		
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("./src/Source\\IntCentroMedico.png"));
		lblFondo.setBounds(0, 0, 434, 510);
		contentPane.add(lblFondo);
	}
}

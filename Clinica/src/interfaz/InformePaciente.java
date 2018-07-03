package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.DiagnosticoRepository;
import data.MedicoRepository;
import data.PacienteRepository;
import entidades.Diagnostico;
import entidades.Medico;
import utilities.Log;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class InformePaciente extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private MedicoRepository medicoRepo;
	private JComboBox<Medico> comboMedicos;
	private MenuInformes menu;
	private DiagnosticoRepository diagnosticoRepo;
	private PacienteRepository pacienteRepo;

	/**
	 * Create the frame.
	 * 
	 * @param menu
	 */
	public InformePaciente(MenuInformes menu) {
		this.menu = menu;
		pacienteRepo = new PacienteRepository();
		medicoRepo = new MedicoRepository();
		diagnosticoRepo = new DiagnosticoRepository();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 620);
		setVisible(true);
		setTitle("Sistema de Control de Pacientes - Cl�nica Nuevo Espiritu");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblInformeDePacientes = new JLabel("Informe de Pacientes");
		lblInformeDePacientes.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblInformeDePacientes.setBounds(181, 16, 242, 31);
		contentPane.add(lblInformeDePacientes);

		JLabel lblIngreseMatriculaDel = new JLabel("Seleccione el M\u00E9dico:");
		lblIngreseMatriculaDel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIngreseMatriculaDel.setBounds(15, 82, 308, 31);
		contentPane.add(lblIngreseMatriculaDel);

		comboMedicos = new JComboBox<Medico>();
		comboMedicos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboMedicos.setBounds(233, 84, 290, 26);
		contentPane.add(comboMedicos);
		comboMedicos.setModel(new DefaultComboBoxModel<Medico>(medicoRepo.listadoMedicos().toArray(new Medico[0])));

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codMedico = obtenerCodigoMedico();
				agregaDatosATabla(codMedico);
			}
		});
		btnBuscar.setBounds(408, 138, 115, 29);
		contentPane.add(btnBuscar);

		JLabel lblPacientes = new JLabel("Pacientes");
		lblPacientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPacientes.setBounds(15, 185, 115, 31);
		contentPane.add(lblPacientes);

		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnAtrs.setBounds(233, 512, 115, 36);
		contentPane.add(btnAtrs);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		table.setBounds(25, 246, 498, 228);
		contentPane.add(table);
	}

	private void agregaDatosATabla(String codMedico) {

		try {
			DefaultTableModel modelo = new DefaultTableModel();
			modelo.addColumn("Paciente");
			modelo.addColumn("Diagnostico");
			ArrayList<Diagnostico> diagnosticos = (ArrayList<Diagnostico>) diagnosticoRepo
					.listadoDiagnosticoMedico(codMedico);
			for (Diagnostico diagnostico : diagnosticos) {
				Object[] fila = new Object[2];
				fila[0] = pacienteRepo.obtenerNombrePaciente(diagnostico.getIdPaciente());
				fila[1] = diagnostico.getDiagnostico();
				modelo.addRow(fila);
			}
			table.setModel(modelo);
		} catch (Exception e) {
			Log.getInstance().error(e.getMessage());
		}
	}

	private String obtenerCodigoMedico() {
		Medico m = (Medico) comboMedicos.getSelectedItem();
		return String.valueOf(m.getCodigo());
	}
}

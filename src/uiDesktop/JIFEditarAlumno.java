package uiDesktop;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import data.DataAlumno;
import entidades.Alumno;
import entidades.Curso;
import utils.ApplicationException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class JIFEditarAlumno extends JInternalFrame {
	private JTextField txtNombre;
	private JTextField txtFechaNacimiento;
	private JTextField txtLegajo;
	private DataAlumno aluData = new DataAlumno();
	private ArrayList<Alumno> alumnos;
	private JComboBox<String> cmbAlumnos;
	private Alumno alumnoAEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFEditarAlumno frame = new JIFEditarAlumno();
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
	public JIFEditarAlumno() {
		try {
			alumnos = aluData.getAll();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Editar Alumno");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 419, 362);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(72, 87, 70, 14);
		getContentPane().add(label);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(164, 84, 129, 20);
		getContentPane().add(txtNombre);
		
		JLabel label_1 = new JLabel("Fecha de Nacimiento:");
		label_1.setBounds(10, 131, 130, 14);
		getContentPane().add(label_1);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(164, 128, 129, 20);
		getContentPane().add(txtFechaNacimiento);
		
		JLabel label_2 = new JLabel("Legajo:");
		label_2.setBounds(77, 173, 46, 14);
		getContentPane().add(label_2);
		
		txtLegajo = new JTextField();
		txtLegajo.setEditable(false);
		txtLegajo.setColumns(10);
		txtLegajo.setBounds(164, 170, 129, 20);
		getContentPane().add(txtLegajo);
		
		JButton btnEditarAlumno = new JButton("Editar Alumno");
		btnEditarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					editarAlumno();
				} catch (ApplicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditarAlumno.setBounds(98, 237, 195, 23);
		getContentPane().add(btnEditarAlumno);
		
		cmbAlumnos = new JComboBox<String>();
		cmbAlumnos.setBounds(54, 29, 150, 30);
		for (int i = 0; i < alumnos.size(); i++) {
			cmbAlumnos.addItem(alumnos.get(i).getNombre());
		}
		getContentPane().add(cmbAlumnos);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarAlumno();
			}
		});
		btnBuscar.setBounds(244, 33, 89, 23);
		getContentPane().add(btnBuscar);		
	}
	
	private void buscarAlumno() {
		try {
			alumnoAEditar = aluData.getByName(cmbAlumnos.getSelectedItem().toString());
			this.txtNombre.setText(alumnoAEditar.getNombre());
			this.txtFechaNacimiento.setText(alumnoAEditar.getFechaNacimiento().toString());
			this.txtLegajo.setText(String.valueOf(alumnoAEditar.getLegajo()));
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void editarAlumno() throws ApplicationException {
		if(this.txtFechaNacimiento.getText().equals("") || this.txtNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Rellene todos los campos");
		}
		else {
			alumnoAEditar.setNombre(this.txtNombre.getText());
			alumnoAEditar.setFechaNacimiento(convertirStringToDate(this.txtFechaNacimiento.getText()));
			alumnoAEditar.setEdad();
			aluData.update(alumnoAEditar);
			JOptionPane.showMessageDialog(this, "El alumno ha sido modificado con exito");
			
		}
	}
	
	private Date convertirStringToDate(String fechaNac) throws ApplicationException {
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(fechaNac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Ha ingresado una fecha incorrecta. Formato: dd-MM-yyyy");
		}
		return date;
	}
}

package uiDesktop;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.message.Message;
import org.eclipse.swt.widgets.MessageBox;

import data.DataAlumno;
import entidades.Alumno;
import utils.ApplicationException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JIFRegistrarAlumno extends JInternalFrame {
	private JTextField txtNombreAlumno;
	private JTextField txtFechaNacimiento;
	private JTextField txtLegajo;
	private DataAlumno aluData = new DataAlumno();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFRegistrarAlumno frame = new JIFRegistrarAlumno();
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
	public JIFRegistrarAlumno() {
		setTitle("Registrar alumno");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 419, 362);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(72, 112, 70, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblFechaDeNacimiento.setBounds(10, 156, 130, 14);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtNombreAlumno = new JTextField();
		txtNombreAlumno.setBounds(172, 109, 129, 20);
		getContentPane().add(txtNombreAlumno);
		txtNombreAlumno.setColumns(10);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setBounds(172, 153, 129, 20);
		getContentPane().add(txtFechaNacimiento);
		txtFechaNacimiento.setColumns(10);
		
		JLabel lblLegajo = new JLabel("Legajo:");
		lblLegajo.setBounds(77, 198, 46, 14);
		getContentPane().add(lblLegajo);
		
		txtLegajo = new JTextField();
		txtLegajo.setEditable(false);
		txtLegajo.setBounds(172, 195, 129, 20);
		getContentPane().add(txtLegajo);
		txtLegajo.setColumns(10);
		
		JButton btnCrearAlumno = new JButton("Crear Alumno");
		btnCrearAlumno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					agregarAlumno();
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnCrearAlumno.setBounds(106, 262, 195, 23);
		getContentPane().add(btnCrearAlumno);
		
		JLabel label = new JLabel("(dd/MM/yyyy)");
		label.setBounds(25, 170, 100, 14);
		getContentPane().add(label);
	}
	
	private void agregarAlumno() throws ApplicationException {
		// TODO Auto-generated method stub
		Alumno a = this.mapearDeFormulario();
		if(a.getFechaNacimiento() != null && a.getNombre() != null) {		
			aluData.add(a);
			JOptionPane.showMessageDialog(this, "El alumno ha sido creado con exito. Su legajo es: "+a.getLegajo());
			this.txtLegajo.setText(String.valueOf(a.getLegajo()));
		} 
	}

	private Alumno mapearDeFormulario() throws ApplicationException {
		// TODO Auto-generated method stub
		Alumno a=new Alumno();
		a.setNombre(this.txtNombreAlumno.getText());		
		a.setFechaNacimiento(convertirStringToDate(this.txtFechaNacimiento.getText()));
		if(a.getFechaNacimiento() != null) {
			a.setIDAlumno(aluData.consultarMax());
			a.setLegajo(aluData.getMaxLegajo());
			a.setEdad();	
		}
		return a;
	}

	private Date convertirStringToDate(String fechaNac) throws ApplicationException {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = df.parse(fechaNac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Ha ingresado una fecha incorrecta. Formato: dd/MM/yyyy");
		}
		return date;
	}
}

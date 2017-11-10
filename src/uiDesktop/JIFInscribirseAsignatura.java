package uiDesktop;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import data.DataAlumno;
import data.DataCurso;
import data.DataInscripcion;
import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import utils.ApplicationException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFInscribirseAsignatura extends JInternalFrame {
	
	JComboBox<String> cmbAlumnos;
	JComboBox<String> cmbAsignaturas;
	private DataAlumno aluData = new DataAlumno();
	private DataCurso cursoData = new DataCurso();
	private DataInscripcion insData = new DataInscripcion();
	private ArrayList<Alumno> alumnos;
	private ArrayList<Curso> cursos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFInscribirseAsignatura frame = new JIFInscribirseAsignatura();
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
	public JIFInscribirseAsignatura() {
		try {
			alumnos = aluData.getAll();
			cursos = cursoData.getAll();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Inscripcion a Curso");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 419, 362);
		getContentPane().setLayout(null);
		
		cmbAlumnos = new JComboBox<String>();
		cmbAlumnos.setBounds(183, 91, 169, 33);
		for (int i = 0; i < alumnos.size(); i++) {
			cmbAlumnos.addItem(alumnos.get(i).getNombre());
		}
		getContentPane().add(cmbAlumnos);
		
		cmbAsignaturas = new JComboBox<String>();
		cmbAsignaturas.setBounds(183, 151, 169, 33);
		for (int i = 0; i < cursos.size(); i++) {
			cmbAsignaturas.addItem(cursos.get(i).getAsignatura());
		}
		getContentPane().add(cmbAsignaturas);
		
		JLabel lblAlumno = new JLabel("Alumno:");
		lblAlumno.setBounds(30, 100, 90, 14);
		getContentPane().add(lblAlumno);
		
		JLabel lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setBounds(30, 160, 90, 14);
		getContentPane().add(lblAsignatura);
		
		JButton btnInscribir = new JButton("Inscribir");
		btnInscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inscribirAlumno();
				} catch (ApplicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnInscribir.setBounds(86, 243, 225, 23);
		getContentPane().add(btnInscribir);

	}
	
	private void inscribirAlumno() throws ApplicationException {
		Alumno alumnoAInscribir = new Alumno();
		Curso cursoAInscribir = new Curso();
		alumnoAInscribir = aluData.getByName(cmbAlumnos.getSelectedItem().toString());
		cursoAInscribir = cursoData.getByName(cmbAsignaturas.getSelectedItem().toString());
		Inscripcion ins = new Inscripcion();
		ins.setIDAlumno(alumnoAInscribir.getIDAlumno());
		ins.setIDCurso(cursoAInscribir.getIDCurso());
		ins.setFechaInscripcion(new Date());
		ins.setEstado(1); //1 es inscripto
		insData.add(ins);
		JOptionPane.showMessageDialog(this, "Alumno inscripto correctamente");
	}

}

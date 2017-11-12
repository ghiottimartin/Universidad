package uiDesktop;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import data.DataAlumno;
import data.DataCurso;
import data.DataInscripcion;
import entidades.Alumno;
import entidades.Curso;
import entidades.Inscripcion;
import utils.ApplicationException;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFEstadoAcademico extends JInternalFrame {
	private DataInscripcion inscripcionData = new DataInscripcion();
	private DataAlumno aluData = new DataAlumno();
	private ArrayList<Curso> materiasRegulares;
	private ArrayList<Curso> materiasNoRegulares;
	private ArrayList<Curso> materiasInscripto;
	private ArrayList<Alumno> alumnos;
	private JComboBox<String> cmbAlumnos = new JComboBox<String>();
	private Alumno alumnoABuscar = new Alumno();
	private JTable tblRegulares;
	private JTable tblNoRegulares;
	private JTable tblInscripto;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFEstadoAcademico frame = new JIFEstadoAcademico();
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
	public JIFEstadoAcademico() {
		try {
			alumnos = aluData.getAll();

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Estado Académico");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 947, 455);
	
		cmbAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					alumnoABuscar = aluData.getByName(cmbAlumnos.getSelectedItem().toString());
					materiasRegulares = inscripcionData.getMateriasRegulares(alumnoABuscar);
					materiasNoRegulares = inscripcionData.getMateriasNoRegulares(alumnoABuscar);
					materiasInscripto = inscripcionData.getMateriasInscripto(alumnoABuscar);
					initDataBindings();
				} catch (ApplicationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cmbAlumnos.setBounds(192, 22, 192, 32);
		for (int i = 0; i < alumnos.size(); i++) {
			cmbAlumnos.addItem(alumnos.get(i).getNombre());
		}
		getContentPane().setLayout(null);
		getContentPane().add(cmbAlumnos);
		
		JLabel lblBuscarAlumno = new JLabel("Buscar Alumno:");
		lblBuscarAlumno.setBounds(81, 31, 120, 14);
		getContentPane().add(lblBuscarAlumno);
		
		JScrollPane scrollPaneRegulares = new JScrollPane();
		scrollPaneRegulares.setBounds(28, 96, 202, 272);
		getContentPane().add(scrollPaneRegulares);
		tblRegulares = new JTable();
		scrollPaneRegulares.setViewportView(tblRegulares);
		
		JScrollPane scrollPaneNoRegulares = new JScrollPane();
		scrollPaneNoRegulares.setBounds(343, 96, 202, 272);
		getContentPane().add(scrollPaneNoRegulares);
		tblNoRegulares = new JTable();
		scrollPaneNoRegulares.setViewportView(tblNoRegulares);
		
		JScrollPane scrollPaneInscriptos = new JScrollPane();
		scrollPaneInscriptos.setBounds(615, 96, 202, 272);
		getContentPane().add(scrollPaneInscriptos);
		tblInscripto = new JTable();
		scrollPaneInscriptos.setViewportView(tblInscripto);
		
		initDataBindings();
	}
	private void initDataBindings() {
		JTableBinding<Curso, List<Curso>, JTable> jTableBindingRegulares = SwingBindings.createJTableBinding(UpdateStrategy.READ, materiasRegulares, tblRegulares);

		BeanProperty<Curso, String> cursoBeanPropertyRegulares = BeanProperty.create("asignatura");
		jTableBindingRegulares.addColumnBinding(cursoBeanPropertyRegulares).setColumnName("Materias Regulares").setEditable(false);
		
		jTableBindingRegulares.setEditable(false);
		jTableBindingRegulares.bind();
		
		JTableBinding<Curso, List<Curso>, JTable> jTableBindingNoRegulares = SwingBindings.createJTableBinding(UpdateStrategy.READ, materiasNoRegulares, tblNoRegulares);

		BeanProperty<Curso, String> cursoBeanPropertyNoRegulares = BeanProperty.create("asignatura");
		jTableBindingNoRegulares.addColumnBinding(cursoBeanPropertyNoRegulares).setColumnName("Materias No Regulares").setEditable(false);
		
		jTableBindingNoRegulares.setEditable(false);
		jTableBindingNoRegulares.bind();
		
		JTableBinding<Curso, List<Curso>, JTable> jTableBindingInscripto = SwingBindings.createJTableBinding(UpdateStrategy.READ, materiasInscripto, tblInscripto);
		//
		BeanProperty<Curso, String> cursoBeanPropertyInscripto = BeanProperty.create("asignatura");
		jTableBindingInscripto.addColumnBinding(cursoBeanPropertyInscripto).setColumnName("Ciclo Actual").setEditable(false);
		
		jTableBindingInscripto.setEditable(false);
		jTableBindingInscripto.bind();
	}
}

package uiDesktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import entidades.Curso;
import logic.CtrlCurso;
import net.proteanit.sql.DbUtils;
import utils.ApplicationException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import data.DataCurso;

import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Universidad {

	private JFrame frame;
	private DataCurso cursoData;
	private ArrayList<Curso> cursos;
	private CtrlCurso ctrlCursos = new CtrlCurso();
	private JTable tableCursos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Universidad window = new Universidad();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ApplicationException 
	 */
	public Universidad() throws ApplicationException {
		initialize();
		cursoData = new DataCurso();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCursos = new JMenu("Cursos");
		menuBar.add(mnCursos);
		
		JMenuItem mntmListarCursos = new JMenuItem("Listar Cursos");
		mnCursos.add(mntmListarCursos);
		
		JMenu mnAlumno = new JMenu("Alumno");
		menuBar.add(mnAlumno);
		
		JMenuItem mntmRegistrarAlumno = new JMenuItem("Registrar Alumno");
		mnAlumno.add(mntmRegistrarAlumno);
		
		JMenuItem mntmEditarAlumno = new JMenuItem("Editar alumno");
		mnAlumno.add(mntmEditarAlumno);
		
		JMenuItem mntmInscribirseAAsignatura = new JMenuItem("Inscribirse a asignatura");
		mnAlumno.add(mntmInscribirseAAsignatura);
		
		JMenuItem mntmEstadoAcadmico = new JMenuItem("Estado Acad\u00E9mico");
		mnAlumno.add(mntmEstadoAcadmico);
		
		JMenuItem mntmCicloActual = new JMenuItem("Ciclo Actual");
		mnAlumno.add(mntmCicloActual);
		
		JMenu mnAsignatura = new JMenu("Asignatura");
		menuBar.add(mnAsignatura);
		
		JMenuItem mntmIncriptos = new JMenuItem("Inscriptos");
		mnAsignatura.add(mntmIncriptos);
		
		JMenuItem mntmRecursantes = new JMenuItem("Recursantes");
		mnAsignatura.add(mntmRecursantes);
		frame.getContentPane().setLayout(null);
		
		tableCursos = new JTable();
		tableCursos.setBounds(56, 38, 355, 184);
		frame.getContentPane().add(tableCursos);
		
		
		}
}


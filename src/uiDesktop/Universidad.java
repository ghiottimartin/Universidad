package uiDesktop;

import java.awt.EventQueue;

import javax.swing.JFrame;
import entidades.Curso;
import logic.CtrlCurso;
import utils.ApplicationException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JTable;
import data.DataCurso;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Universidad {

	private JFrame frame;
	private JDesktopPane desktopPaneMain;

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
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1372, 740);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		desktopPaneMain = new JDesktopPane();
		desktopPaneMain.setBounds(10, 11, 1342, 662);
		frame.getContentPane().add(desktopPaneMain);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnCursos = new JMenu("Cursos");
		menuBar.add(mnCursos);
		
		JMenuItem mntmListarCursos = new JMenuItem("Listar Cursos");
		mntmListarCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JIFListadoCursos listaCursos = new JIFListadoCursos();
				listaCursos.setVisible(true);
				desktopPaneMain.add(listaCursos);
				
			}
		});
		mnCursos.add(mntmListarCursos);
		
		JMenu mnAlumnos = new JMenu("Alumnos");
		menuBar.add(mnAlumnos);
		
		JMenuItem mntmRegistrarAlumnos = new JMenuItem("Registrar alumnos");
		mntmRegistrarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JIFRegistrarAlumno registrarAlumno = new JIFRegistrarAlumno();
				registrarAlumno.setVisible(true);
				desktopPaneMain.add(registrarAlumno);
			}
		});
		mnAlumnos.add(mntmRegistrarAlumnos);
		
		JMenuItem mntmEditarAlumno = new JMenuItem("Editar alumno");
		mntmEditarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JIFEditarAlumno editarAlumno = new JIFEditarAlumno();
				editarAlumno.setVisible(true);
				desktopPaneMain.add(editarAlumno);
				
			}
		});
		mnAlumnos.add(mntmEditarAlumno);
		
		JMenuItem mntmInscribirseAAsignatura = new JMenuItem("Inscribirse a asignatura");
		mntmInscribirseAAsignatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIFInscribirseAsignatura inscripcion = new JIFInscribirseAsignatura();
				inscripcion.setVisible(true);
				desktopPaneMain.add(inscripcion);
			}
		});
		mnAlumnos.add(mntmInscribirseAAsignatura);
		
		JMenuItem mntmEstadoAcadmico = new JMenuItem("Estado acad\u00E9mico");
		mnAlumnos.add(mntmEstadoAcadmico);
		
		JMenuItem mntmCicloActual = new JMenuItem("Ciclo Actual");
		mnAlumnos.add(mntmCicloActual);
		
		JMenu mnAsignaturas = new JMenu("Asignaturas");
		menuBar.add(mnAsignaturas);
		
		JMenuItem mntmInscriptos = new JMenuItem("Inscriptos");
		mnAsignaturas.add(mntmInscriptos);
		
		JMenuItem mntmRecursantes = new JMenuItem("Recursantes");
		mnAsignaturas.add(mntmRecursantes);
		
		}
}


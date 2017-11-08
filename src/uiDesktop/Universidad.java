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
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

public class Universidad {

	private JFrame frame;
	private ArrayList<Curso> cursos;
	private CtrlCurso ctrlCurso;
	private JTable tblCursos;

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
		cursos = new ArrayList<Curso>();
		addRowToJTable();
	}

	private void addRowToJTable() throws ApplicationException {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) tblCursos.getModel();
        ArrayList<Curso> cursos = ctrlCurso.getAll();
        Object rowData[] = new Object[4];
        for(int i = 0; i < cursos.size(); i++)
        {
            rowData[0] = cursos.get(i).getIDCurso();
            rowData[1] = cursos.get(i).getAsignatura();
            rowData[2] = cursos.get(i).getCupoMaximo();
            rowData[3] = cursos.get(i).getDocente();
            model.addRow(rowData);
        }
		
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
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBounds(1350, 10, 2, 2);
		frame.getContentPane().add(jScrollPane);
		tblCursos = new JTable();
		tblCursos.setBounds(10, 11, 1, 1);
		frame.getContentPane().add(tblCursos);
		frame.setVisible(true);
		
		tblCursos.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "ID Curso", "Asignatura", "Cupo Maximo", "Docente"
	            }
	        ));
		jScrollPane.setViewportView(tblCursos);
		
		
	}
}

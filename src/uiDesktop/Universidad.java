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
	private DataCurso cursoData;
	private ArrayList<Curso> cursos;
	private CtrlCurso ctrlCursos;
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
		cursoData = new DataCurso();
		ctrlCursos = new CtrlCurso();
		cursos = ctrlCursos.getAll();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
				JInternalFrameCursos listaCursos = new JInternalFrameCursos();
				listaCursos.setVisible(true);
				desktopPaneMain.add(listaCursos);
				
			}
		});
		mnCursos.add(mntmListarCursos);
		
		}
}


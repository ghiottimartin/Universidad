package uiDesktop;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import data.DataCurso;
import data.DataInscripcion;
import entidades.Alumno;
import entidades.Curso;
import utils.ApplicationException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JIFInscriptos extends JInternalFrame {
	private JTable tblAlumnosInscriptos;
	private JComboBox<String> cmbAsignaturas;
	private ArrayList<Alumno> alumnosInscriptos;
	private ArrayList<Curso> cursos;
	private DataCurso cursoData = new DataCurso();
	private DataInscripcion inscripcionData = new DataInscripcion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFInscriptos frame = new JIFInscriptos();
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
	public JIFInscriptos() {
		try {
			cursos = cursoData.getAll();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Inscriptos");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 730, 462);
		getContentPane().setLayout(null);
		
		JLabel lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setBounds(31, 29, 120, 14);
		getContentPane().add(lblAsignatura);
		
		cmbAsignaturas = new JComboBox<String>();
		cmbAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					alumnosInscriptos = inscripcionData.getInscriptos(cmbAsignaturas.getSelectedItem().toString());
					initDataBindings();
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			}
		});
		for (int i = 0; i < cursos.size(); i++) {
			cmbAsignaturas.addItem(cursos.get(i).getAsignatura());
		}
		cmbAsignaturas.setBounds(102, 22, 236, 28);
		getContentPane().add(cmbAsignaturas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 82, 684, 325);
		getContentPane().add(scrollPane);		
		tblAlumnosInscriptos = new JTable();
		scrollPane.setViewportView(tblAlumnosInscriptos);
		
		initDataBindings();
	}
	
	private void initDataBindings() {
		// TODO Auto-generated method stub
				JTableBinding<Alumno, List<Alumno>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, alumnosInscriptos, tblAlumnosInscriptos);
				//
				BeanProperty<Alumno, String> cursoBeanProperty_1 = BeanProperty.create("nombre");
				jTableBinding.addColumnBinding(cursoBeanProperty_1).setColumnName("Nombre").setEditable(false);
				//
				BeanProperty<Alumno, Integer> cursoBeanProperty_2 = BeanProperty.create("legajo");
				jTableBinding.addColumnBinding(cursoBeanProperty_2).setColumnName("Legajo").setEditable(false);
				//
				BeanProperty<Alumno, Integer> cursoBeanProperty_3 = BeanProperty.create("edad");
				jTableBinding.addColumnBinding(cursoBeanProperty_3).setColumnName("Edad").setEditable(false);
				//
				BeanProperty<Alumno, Date> cursoBeanProperty_4 = BeanProperty.create("fechaNacimiento");
				jTableBinding.addColumnBinding(cursoBeanProperty_4).setColumnName("Fecha de Nacimiento").setEditable(false);
				//
				jTableBinding.setEditable(false);
				jTableBinding.bind();
		
	}
}

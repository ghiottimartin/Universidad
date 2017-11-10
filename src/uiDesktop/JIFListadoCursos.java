package uiDesktop;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import data.DataCurso;
import entidades.Curso;
import logic.CtrlCurso;
import utils.ApplicationException;

public class JIFListadoCursos extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblCursos;
	private DataCurso cursoData = new DataCurso();
	private ArrayList<Curso> cursos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JIFListadoCursos frame = new JIFListadoCursos();
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
	public JIFListadoCursos() {
		try {
			cursos = cursoData.getAll();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("Listado Cursos");
		setClosable(true);
		setMaximizable(false);
		setResizable(false);
		setBounds(100, 100, 947, 455);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 929, 420);
		getContentPane().add(scrollPane);
		tblCursos = new JTable();
		scrollPane.setViewportView(tblCursos);
		
		initDataBindings();
	}

	private void initDataBindings() {
		// TODO Auto-generated method stub
		JTableBinding<Curso, List<Curso>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, cursos, tblCursos);
		//
		BeanProperty<Curso, Integer> cursoBeanProperty = BeanProperty.create("IDCurso");
		jTableBinding.addColumnBinding(cursoBeanProperty).setColumnName("IDCurso").setEditable(false);
		//
		BeanProperty<Curso, String> cursoBeanProperty_1 = BeanProperty.create("asignatura");
		jTableBinding.addColumnBinding(cursoBeanProperty_1).setColumnName("Asignatura").setEditable(false);
		//
		BeanProperty<Curso, Integer> cursoBeanProperty_2 = BeanProperty.create("cupoMaximo");
		jTableBinding.addColumnBinding(cursoBeanProperty_2).setColumnName("Cupo Máximo").setEditable(false);
		//
		BeanProperty<Curso, String> cursoBeanProperty_3 = BeanProperty.create("docente");
		jTableBinding.addColumnBinding(cursoBeanProperty_3).setColumnName("Docente").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
}

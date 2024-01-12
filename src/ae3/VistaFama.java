package ae3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JTable;

public class VistaFama extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public VistaFama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 452);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbltitolFama = new JLabel("Saló de la fama");
		lbltitolFama.setBounds(375, 11, 144, 22);
		lbltitolFama.setForeground(Color.WHITE);
		lbltitolFama.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lbltitolFama);

		tableModel = new DefaultTableModel();

		JScrollPane scrollPaneTable = new JScrollPane();
		scrollPaneTable.setBounds(10, 30, 630, 266);
		contentPane.add(scrollPaneTable);

		table = new JTable(tableModel);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		scrollPaneTable.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);

		setVisible(false);
	}

	public void carregarSelectEnTaula(ArrayList<JSONObject> llista) {
		if (llista != null && !llista.isEmpty()) {
			// Limpiar el modelo de la tabla
			tableModel.setColumnCount(0);
			tableModel.setRowCount(0);

			// Obtener nombres de columnas de la primera fila (si hay alguna)
			JSONObject primerFila = llista.get(0);
			Set<String> nombresColumnas = primerFila.keySet();

			// Añadir las columnas al modelo de la tabla
			for (String nombreColumna : nombresColumnas) {
				tableModel.addColumn(nombreColumna);
			}

			// Añadir filas al modelo de la tabla
			for (JSONObject fila : llista) {
				Object[] datosFila = new Object[nombresColumnas.size()];

				int i = 0;
				for (String nombreColumna : nombresColumnas) {
					datosFila[i++] = fila.get(nombreColumna);
				}

				tableModel.addRow(datosFila);
			}
		}
	}

}
package ae3;

import java.awt.Color;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNomRegistro;
	private JTextField textFieldContrasenyaRegistro;
	private JTextField textFieldContrasenyaRepetidaRegistro;
	private JButton btnRegistroEnRegistro;
	private JButton btnTornarRegistre;

	/**
	 * Create the frame.
	 */
	public VistaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 341);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(70, 30, 308, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNom = new JLabel("Nom:");
		lblNom.setForeground(Color.DARK_GRAY);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNom.setBounds(27, 65, 46, 14);
		panel.add(lblNom);

		JLabel lblIniciDeSessi = new JLabel("Registre");
		lblIniciDeSessi.setBounds(112, 11, 85, 22);
		panel.add(lblIniciDeSessi);
		lblIniciDeSessi.setForeground(Color.DARK_GRAY);
		lblIniciDeSessi.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldNomRegistro = new JTextField();
		textFieldNomRegistro.setBounds(27, 90, 181, 20);
		panel.add(textFieldNomRegistro);
		textFieldNomRegistro.setColumns(10);

		JLabel lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setForeground(Color.DARK_GRAY);
		lblContrasenya.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasenya.setBounds(27, 118, 98, 14);
		panel.add(lblContrasenya);

		textFieldContrasenyaRegistro = new JTextField();
		textFieldContrasenyaRegistro.setColumns(10);
		textFieldContrasenyaRegistro.setBounds(27, 143, 242, 20);
		panel.add(textFieldContrasenyaRegistro);

		JLabel lblRepiteLaContrasenya = new JLabel("Repite la contrasenya:");
		lblRepiteLaContrasenya.setForeground(Color.DARK_GRAY);
		lblRepiteLaContrasenya.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRepiteLaContrasenya.setBounds(27, 174, 170, 14);
		panel.add(lblRepiteLaContrasenya);

		textFieldContrasenyaRepetidaRegistro = new JTextField();
		textFieldContrasenyaRepetidaRegistro.setColumns(10);
		textFieldContrasenyaRepetidaRegistro.setBounds(27, 199, 242, 20);
		panel.add(textFieldContrasenyaRepetidaRegistro);

		btnRegistroEnRegistro = new JButton("Registrarse");
		btnRegistroEnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistroEnRegistro.setForeground(Color.DARK_GRAY);
		btnRegistroEnRegistro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistroEnRegistro.setBackground(Color.WHITE);
		btnRegistroEnRegistro.setBounds(76, 230, 157, 25);
		panel.add(btnRegistroEnRegistro);

		btnTornarRegistre = new JButton("<---");
		btnTornarRegistre.setForeground(Color.DARK_GRAY);
		btnTornarRegistre.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTornarRegistre.setBackground(Color.WHITE);
		btnTornarRegistre.setBounds(10, 11, 85, 43);
		panel.add(btnTornarRegistre);

		setVisible(false);
	}

	public JButton getBtnRegistroEnRegistro() {
		return btnRegistroEnRegistro;
	}

	public JTextField getTextFieldNomRegistro() {
		return textFieldNomRegistro;
	}

	public JTextField getTextFieldContrasenyaRegistro() {
		return textFieldContrasenyaRegistro;
	}

	public JTextField getTextFieldContrasenyaRepetidaRegistro() {
		return textFieldContrasenyaRepetidaRegistro;
	}

	public JButton getBtnTornarRegistre() {
		return btnTornarRegistre;
	}

}
package ae3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaInicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldContrasenya;
	private JButton btnIniciDeSesioEnincideSesion;
	private JButton btnTornarIniciSessio;

	/**
	 * Create the frame.
	 */
	public VistaInicioSesion() {
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

		JLabel lblIniciDeSessi = new JLabel("Inici de sessió");
		lblIniciDeSessi.setBounds(106, 11, 127, 22);
		panel.add(lblIniciDeSessi);
		lblIniciDeSessi.setForeground(Color.DARK_GRAY);
		lblIniciDeSessi.setFont(new Font("Tahoma", Font.BOLD, 18));

		textFieldNom = new JTextField();
		textFieldNom.setBounds(27, 90, 181, 20);
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);

		JLabel lblContrasenya = new JLabel("Contrasenya:");
		lblContrasenya.setForeground(Color.DARK_GRAY);
		lblContrasenya.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasenya.setBounds(29, 142, 98, 14);
		panel.add(lblContrasenya);

		textFieldContrasenya = new JTextField();
		textFieldContrasenya.setColumns(10);
		textFieldContrasenya.setBounds(27, 167, 242, 20);
		panel.add(textFieldContrasenya);

		btnIniciDeSesioEnincideSesion = new JButton("Inici de sessió");
		btnIniciDeSesioEnincideSesion.setForeground(Color.DARK_GRAY);
		btnIniciDeSesioEnincideSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciDeSesioEnincideSesion.setBackground(Color.WHITE);
		btnIniciDeSesioEnincideSesion.setBounds(76, 230, 157, 25);
		panel.add(btnIniciDeSesioEnincideSesion);

		btnTornarIniciSessio = new JButton("<---");
		btnTornarIniciSessio.setBounds(10, 11, 81, 43);
		panel.add(btnTornarIniciSessio);
		btnTornarIniciSessio.setForeground(Color.DARK_GRAY);
		btnTornarIniciSessio.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTornarIniciSessio.setBackground(Color.WHITE);

		setVisible(false);
	}

	public JButton getbtnIniciDeSesioEnincideSesion() {
		return btnIniciDeSesioEnincideSesion;
	}

	public JTextField getTextFieldNom() {
		return textFieldNom;
	}

	public JTextField getTextFieldContrasenya() {
		return textFieldContrasenya;
	}

	public JButton getBtnTornarIniciSessio() {
		return btnTornarIniciSessio;
	}

}
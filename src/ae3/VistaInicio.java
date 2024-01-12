package ae3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnIniciDeSesio;
	private JButton btnRegistrarse;

	/**
	 * Create the frame.
	 */
	public VistaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnIniciDeSesio = new JButton("Inici de sessió");
		btnIniciDeSesio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciDeSesio.setForeground(Color.WHITE);
		btnIniciDeSesio.setBackground(Color.DARK_GRAY);
		btnIniciDeSesio.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciDeSesio.setBounds(143, 90, 157, 25);
		contentPane.add(btnIniciDeSesio);

		JLabel lblNewLabel = new JLabel("Benvinguts a la nostra aplicació de memòria");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(16, 30, 408, 35);
		contentPane.add(lblNewLabel);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setForeground(Color.WHITE);
		btnRegistrarse.setBackground(Color.DARK_GRAY);
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistrarse.setBounds(143, 135, 157, 25);
		contentPane.add(btnRegistrarse);

		setVisible(true);
	}

	public JButton getbtnIniciDeSesio() {
		return btnIniciDeSesio;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}

}
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
	private JButton btnInicioDeSesion;
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
		
		btnInicioDeSesion = new JButton("Inici de sessió");
		btnInicioDeSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInicioDeSesion.setForeground(Color.WHITE);
		btnInicioDeSesion.setBackground(Color.DARK_GRAY);
		btnInicioDeSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInicioDeSesion.setBounds(143, 90, 157, 25);
		contentPane.add(btnInicioDeSesion);
		
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

	public JButton getBtnInicioDeSesion() {
		return btnInicioDeSesion;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}
	
}

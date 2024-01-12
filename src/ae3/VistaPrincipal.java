package ae3;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnJugar;
	private JButton btnGuardar;
	private JButton btnSalDeLa;
	private JRadioButton rdbtn4x4;
	private JRadioButton rdbtn4x2;
	private static ArrayList<JButton> buttonsImatgesArray = new ArrayList<>();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public JRadioButton getRdbtn4x4() {
		return rdbtn4x4;
	}

	public void setRdbtn4x4(JRadioButton rdbtn4x4) {
		this.rdbtn4x4 = rdbtn4x4;
	}

	public JRadioButton getRdbtn4x2() {
		return rdbtn4x2;
	}

	public void setRdbtn4x2(JRadioButton rdbtn4x2) {
		this.rdbtn4x2 = rdbtn4x2;
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnSalDeLa() {
		return btnSalDeLa;
	}

	public static ArrayList<JButton> getButtonsImatgesArray() {
		return buttonsImatgesArray;
	}

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 981, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnJugar = new JButton("Jugar");
		btnJugar.setBounds(50, 82, 157, 25);
		btnJugar.setForeground(new Color(255, 255, 255));
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnJugar.setBackground(Color.DARK_GRAY);
		contentPane.add(btnJugar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setBounds(50, 185, 157, 25);
		contentPane.add(btnGuardar);

		btnSalDeLa = new JButton("Saló de la fama");
		btnSalDeLa.setForeground(new Color(255, 255, 255));
		btnSalDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalDeLa.setBackground(Color.DARK_GRAY);
		btnSalDeLa.setBounds(50, 289, 157, 25);
		contentPane.add(btnSalDeLa);

		rdbtn4x4 = new JRadioButton("4x4");
		rdbtn4x4.setSelected(true);
		rdbtn4x4.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn4x4.setForeground(Color.WHITE);
		rdbtn4x4.setBackground(Color.DARK_GRAY);
		buttonGroup.add(rdbtn4x4);
		rdbtn4x4.setBounds(50, 114, 64, 23);
		contentPane.add(rdbtn4x4);

		rdbtn4x2 = new JRadioButton("4x2");
		rdbtn4x2.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtn4x2.setForeground(Color.WHITE);
		rdbtn4x2.setBackground(Color.DARK_GRAY);
		buttonGroup.add(rdbtn4x2);
		rdbtn4x2.setBounds(143, 114, 64, 23);
		contentPane.add(rdbtn4x2);

		for (int i = 0; i < 16; i++) {
			JButton boton = new JButton("");
			boton.setEnabled(false);
			boton.setForeground(Color.WHITE);
			boton.setFont(new Font("Tahoma", Font.BOLD, 14));
			boton.setBackground(Color.DARK_GRAY);

			int x = (i % 4) * 167 + 278;
			int y = (i / 4) * 121 + 36;

			boton.setBounds(x, y, 157, 110);

			contentPane.add(boton);
			buttonsImatgesArray.add(boton);
		}

		setVisible(true);
	}

}

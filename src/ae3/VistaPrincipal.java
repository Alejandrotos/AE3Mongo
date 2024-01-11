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
import java.awt.event.ActionEvent;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnJugar;
	private JButton btnGuardar;
	private JButton btnSalDeLa;
	private JRadioButton rdbtn4x4;
	private JRadioButton rdbtn4x2;
	private static JButton[] buttonsImatgesArray = new JButton[16];
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
	
	public static JButton[] getButtonsImatgesArray() {
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

		buttonsImatgesArray[0] = new JButton("");
		buttonsImatgesArray[0].setEnabled(false);
		buttonsImatgesArray[0].setForeground(Color.WHITE);
		buttonsImatgesArray[0].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[0].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[0].setBounds(278, 36, 157, 110);
		contentPane.add(buttonsImatgesArray[0]);

		buttonsImatgesArray[1] = new JButton("");
		buttonsImatgesArray[1].setEnabled(false);
		buttonsImatgesArray[1].setForeground(Color.WHITE);
		buttonsImatgesArray[1].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[1].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[1].setBounds(445, 36, 157, 110);
		contentPane.add(buttonsImatgesArray[1]);

		buttonsImatgesArray[2] = new JButton("");
		buttonsImatgesArray[2].setEnabled(false);
		buttonsImatgesArray[2].setForeground(Color.WHITE);
		buttonsImatgesArray[2].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[2].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[2].setBounds(612, 36, 157, 110);
		contentPane.add(buttonsImatgesArray[2]);

		buttonsImatgesArray[3] = new JButton("");
		buttonsImatgesArray[3].setEnabled(false);
		buttonsImatgesArray[3].setForeground(Color.WHITE);
		buttonsImatgesArray[3].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[3].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[3].setBounds(777, 36, 157, 110);
		contentPane.add(buttonsImatgesArray[3]);

		buttonsImatgesArray[4] = new JButton("");
		buttonsImatgesArray[4].setEnabled(false);
		buttonsImatgesArray[4].setForeground(Color.WHITE);
		buttonsImatgesArray[4].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[4].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[4].setBounds(278, 157, 157, 110);
		contentPane.add(buttonsImatgesArray[4]);

		buttonsImatgesArray[5] = new JButton("");
		buttonsImatgesArray[5].setEnabled(false);
		buttonsImatgesArray[5].setForeground(Color.WHITE);
		buttonsImatgesArray[5].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[5].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[5].setBounds(445, 157, 157, 110);
		contentPane.add(buttonsImatgesArray[5]);

		buttonsImatgesArray[6] = new JButton("");
		buttonsImatgesArray[6].setEnabled(false);
		buttonsImatgesArray[6].setForeground(Color.WHITE);
		buttonsImatgesArray[6].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[6].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[6].setBounds(612, 157, 157, 110);
		contentPane.add(buttonsImatgesArray[6]);

		buttonsImatgesArray[7] = new JButton("");
		buttonsImatgesArray[7].setEnabled(false);
		buttonsImatgesArray[7].setForeground(Color.WHITE);
		buttonsImatgesArray[7].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[7].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[7].setBounds(777, 157, 157, 110);
		contentPane.add(buttonsImatgesArray[7]);
		
		buttonsImatgesArray[8] = new JButton("");
		buttonsImatgesArray[8].setEnabled(false);
		buttonsImatgesArray[8].setForeground(Color.WHITE);
		buttonsImatgesArray[8].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[8].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[8].setBounds(278, 278, 157, 110);
		contentPane.add(buttonsImatgesArray[8]);

		buttonsImatgesArray[9] = new JButton("");
		buttonsImatgesArray[9].setEnabled(false);
		buttonsImatgesArray[9].setForeground(Color.WHITE);
		buttonsImatgesArray[9].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[9].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[9].setBounds(445, 278, 157, 110);
		contentPane.add(buttonsImatgesArray[9]);

		buttonsImatgesArray[10] = new JButton("");
		buttonsImatgesArray[10].setEnabled(false);
		buttonsImatgesArray[10].setForeground(Color.WHITE);
		buttonsImatgesArray[10].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[10].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[10].setBounds(612, 278, 157, 110);
		contentPane.add(buttonsImatgesArray[10]);

		buttonsImatgesArray[11] = new JButton("");
		buttonsImatgesArray[11].setEnabled(false);
		buttonsImatgesArray[11].setForeground(Color.WHITE);
		buttonsImatgesArray[11].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[11].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[11].setBounds(777, 278, 157, 110);
		contentPane.add(buttonsImatgesArray[11]);

		buttonsImatgesArray[12] = new JButton("");
		buttonsImatgesArray[12].setEnabled(false);
		buttonsImatgesArray[12].setForeground(Color.WHITE);
		buttonsImatgesArray[12].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[12].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[12].setBounds(278, 399, 157, 110);
		contentPane.add(buttonsImatgesArray[12]);

		buttonsImatgesArray[13] = new JButton("");
		buttonsImatgesArray[13].setEnabled(false);
		buttonsImatgesArray[13].setForeground(Color.WHITE);
		buttonsImatgesArray[13].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[13].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[13].setBounds(445, 399, 157, 110);
		contentPane.add(buttonsImatgesArray[13]);

		buttonsImatgesArray[14] = new JButton("");
		buttonsImatgesArray[14].setEnabled(false);
		buttonsImatgesArray[14].setForeground(Color.WHITE);
		buttonsImatgesArray[14].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[14].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[14].setBounds(612, 399, 157, 110);
		contentPane.add(buttonsImatgesArray[14]);

		buttonsImatgesArray[15] = new JButton("");
		buttonsImatgesArray[15].setEnabled(false);
		buttonsImatgesArray[15].setForeground(Color.WHITE);
		buttonsImatgesArray[15].setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonsImatgesArray[15].setBackground(Color.DARK_GRAY);
		buttonsImatgesArray[15].setBounds(777, 399, 157, 110);
		contentPane.add(buttonsImatgesArray[15]);

		setVisible(true);
	}

}

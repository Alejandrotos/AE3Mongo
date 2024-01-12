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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnJugar;
	private JButton btnGuardar;
	private JButton btnSaloDeLa;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea textAreaFama;
	private static JRadioButton rdbtn4x2;
	private static JRadioButton rdbtn4x4;

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
		btnJugar.setForeground(Color.DARK_GRAY);
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnJugar.setBackground(Color.DARK_GRAY);
		contentPane.add(btnJugar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.DARK_GRAY);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBackground(Color.DARK_GRAY);
		btnGuardar.setBounds(50, 185, 157, 25);
		contentPane.add(btnGuardar);
		
		btnSaloDeLa = new JButton("Saló de la fama");
		btnSaloDeLa.setForeground(Color.DARK_GRAY);
		btnSaloDeLa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSaloDeLa.setBackground(Color.DARK_GRAY);
		btnSaloDeLa.setBounds(50, 290, 157, 25);
		contentPane.add(btnSaloDeLa);
		
		rdbtn4x4 = new JRadioButton("4x4");
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
		
		JButton btnImgr_1_1 = new JButton("");
		btnImgr_1_1.setForeground(Color.WHITE);
		btnImgr_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_1_1.setBackground(Color.DARK_GRAY);
		btnImgr_1_1.setBounds(278, 36, 157, 110);
		contentPane.add(btnImgr_1_1);
		
		JButton btnImgr_1_2 = new JButton("");
		btnImgr_1_2.setForeground(Color.WHITE);
		btnImgr_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_1_2.setBackground(Color.DARK_GRAY);
		btnImgr_1_2.setBounds(445, 36, 157, 110);
		contentPane.add(btnImgr_1_2);
		
		JButton btnImgr_1_3 = new JButton("");
		btnImgr_1_3.setForeground(Color.WHITE);
		btnImgr_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_1_3.setBackground(Color.DARK_GRAY);
		btnImgr_1_3.setBounds(612, 36, 157, 110);
		contentPane.add(btnImgr_1_3);
		
		JButton btnImgr_1_4 = new JButton("");
		btnImgr_1_4.setForeground(Color.WHITE);
		btnImgr_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_1_4.setBackground(Color.DARK_GRAY);
		btnImgr_1_4.setBounds(777, 36, 157, 110);
		contentPane.add(btnImgr_1_4);
		
		JButton btnImgr_2_1 = new JButton("");
		btnImgr_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImgr_2_1.setForeground(Color.WHITE);
		btnImgr_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_2_1.setBackground(Color.DARK_GRAY);
		btnImgr_2_1.setBounds(278, 157, 157, 110);
		contentPane.add(btnImgr_2_1);
		
		JButton btnImgr_2_2 = new JButton("");
		btnImgr_2_2.setForeground(Color.WHITE);
		btnImgr_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_2_2.setBackground(Color.DARK_GRAY);
		btnImgr_2_2.setBounds(445, 157, 157, 110);
		contentPane.add(btnImgr_2_2);
		
		JButton btnImgr_2_3 = new JButton("");
		btnImgr_2_3.setForeground(Color.WHITE);
		btnImgr_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_2_3.setBackground(Color.DARK_GRAY);
		btnImgr_2_3.setBounds(612, 157, 157, 110);
		contentPane.add(btnImgr_2_3);
		
		JButton btnImgr_2_4 = new JButton("");
		btnImgr_2_4.setForeground(Color.WHITE);
		btnImgr_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_2_4.setBackground(Color.DARK_GRAY);
		btnImgr_2_4.setBounds(777, 157, 157, 110);
		contentPane.add(btnImgr_2_4);
		
		JButton btnImgr_3_1 = new JButton("");
		btnImgr_3_1.setForeground(Color.WHITE);
		btnImgr_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_3_1.setBackground(Color.DARK_GRAY);
		btnImgr_3_1.setBounds(278, 278, 157, 110);
		contentPane.add(btnImgr_3_1);
		
		JButton btnImgr_3_2 = new JButton("");
		btnImgr_3_2.setForeground(Color.WHITE);
		btnImgr_3_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_3_2.setBackground(Color.DARK_GRAY);
		btnImgr_3_2.setBounds(445, 278, 157, 110);
		contentPane.add(btnImgr_3_2);
		
		JButton btnImgr_3_3 = new JButton("");
		btnImgr_3_3.setForeground(Color.WHITE);
		btnImgr_3_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_3_3.setBackground(Color.DARK_GRAY);
		btnImgr_3_3.setBounds(612, 278, 157, 110);
		contentPane.add(btnImgr_3_3);
		
		JButton btnImgr_3_4 = new JButton("");
		btnImgr_3_4.setForeground(Color.WHITE);
		btnImgr_3_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_3_4.setBackground(Color.DARK_GRAY);
		btnImgr_3_4.setBounds(777, 278, 157, 110);
		contentPane.add(btnImgr_3_4);
		
		JButton btnImgr_4_1 = new JButton("");
		btnImgr_4_1.setForeground(Color.WHITE);
		btnImgr_4_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_4_1.setBackground(Color.DARK_GRAY);
		btnImgr_4_1.setBounds(278, 399, 157, 110);
		contentPane.add(btnImgr_4_1);
		
		JButton btnImgr_4_2 = new JButton("");
		btnImgr_4_2.setForeground(Color.WHITE);
		btnImgr_4_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_4_2.setBackground(Color.DARK_GRAY);
		btnImgr_4_2.setBounds(445, 399, 157, 110);
		contentPane.add(btnImgr_4_2);
		
		JButton btnImgr_4_3 = new JButton("");
		btnImgr_4_3.setForeground(Color.WHITE);
		btnImgr_4_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_4_3.setBackground(Color.DARK_GRAY);
		btnImgr_4_3.setBounds(612, 399, 157, 110);
		contentPane.add(btnImgr_4_3);
		
		JButton btnImgr_4_4 = new JButton("");
		btnImgr_4_4.setForeground(Color.WHITE);
		btnImgr_4_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnImgr_4_4.setBackground(Color.DARK_GRAY);
		btnImgr_4_4.setBounds(777, 399, 157, 110);
		contentPane.add(btnImgr_4_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 326, 157, 203);
		contentPane.add(scrollPane);
		
		textAreaFama = new JTextArea();
		scrollPane.setViewportView(textAreaFama);
		textAreaFama.setEditable(false);
		textAreaFama.setLineWrap(true);
		
		setVisible(false);
	}

	public JButton getBtnJugar() {
		return btnJugar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnSaloDeLa() {
		return btnSaloDeLa;
	}

	public JTextArea getTextAreaFama() {
		return textAreaFama;
	}

	public void setTextAreaFama(String textAreaFama) {
	    this.textAreaFama.setText(textAreaFama);
	}
	
	
	
	public JRadioButton getRdbtn4x2() {
		return rdbtn4x2;
	}

	public JRadioButton getRdbtn4x4() {
		return rdbtn4x4;
	}

	public static int rdbtnDificultad() {
		if(rdbtn4x2.isSelected()) {
			return 8;
		}else {
			return 16;
		}
	}
	
	
}

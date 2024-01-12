package ae3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controlador {
	private Model Model;
	private VistaInicio Inici;
	private VistaRegistro Registro = new VistaRegistro();
	private VistaInicioSesion IniciSesio = new VistaInicioSesion();
	private VistaPrincipal Principal = new VistaPrincipal();
	private ActionListener actionListenerbtnIniciDeSesio;
	private ActionListener actionListenerbtnRegistrarse;
	private ActionListener actionListenerbtnIniciDeSesioEnincideSesion;
	private ActionListener actionListenerbtnRegistroEnRegistro;
	private ActionListener actionListenerbtnTornarRegistre;
	private ActionListener actionListenerbtnTornarIniciSessio;
	private ActionListener actionListenerbtnSaloDeLa;

	Controlador(VistaInicio Inici, Model Model) {
		this.Inici = Inici;
		this.Model = Model;
		Control();
	}

	public void Control() {
		actionListenerbtnIniciDeSesio = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				IniciSesio.setVisible(true);
				Inici.setVisible(false);
			}
		};
		Inici.getbtnIniciDeSesio().addActionListener(actionListenerbtnIniciDeSesio);

		actionListenerbtnRegistrarse = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro.setVisible(true);
				Inici.setVisible(false);
			}
		};
		Inici.getBtnRegistrarse().addActionListener(actionListenerbtnRegistrarse);

		actionListenerbtnIniciDeSesioEnincideSesion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = IniciSesio.getTextFieldNom().getText();
				String contrasenya = IniciSesio.getTextFieldContrasenya().getText();
				if (nom.isEmpty() || contrasenya.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Els apartats no poden estar buits.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (Model.iniciUsuari(nom, contrasenya)) {
					JOptionPane.showMessageDialog(null, "Usuari i contrasenya correctes");
					Principal.setVisible(true);
					IniciSesio.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuari o contrasenya incorrectes");
				}
			}
		};
		IniciSesio.getbtnIniciDeSesioEnincideSesion().addActionListener(actionListenerbtnIniciDeSesioEnincideSesion);

		actionListenerbtnRegistroEnRegistro = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = Registro.getTextFieldNomRegistro().getText();
				String contrasenya = Registro.getTextFieldContrasenyaRegistro().getText();
				String contrasenyaRepetida = Registro.getTextFieldContrasenyaRepetidaRegistro().getText();
				if (nom.isEmpty() || contrasenya.isEmpty() || contrasenyaRepetida.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Els apartats no poden estar buits.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (!contrasenya.equals(contrasenyaRepetida)) {
					JOptionPane.showMessageDialog(null, "La contrasenya no es igual.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (Model.insertUsuari(nom, contrasenya)) {
					JOptionPane.showMessageDialog(null, "Usuari fet");
					IniciSesio.setVisible(true);
					Registro.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Usuari ya creat");
				}
			}
		};
		Registro.getBtnRegistroEnRegistro().addActionListener(actionListenerbtnRegistroEnRegistro);

		actionListenerbtnTornarRegistre = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Inici.setVisible(true);
				Registro.setVisible(false);
			}
		};
		Registro.getBtnTornarRegistre().addActionListener(actionListenerbtnTornarRegistre);

		actionListenerbtnTornarIniciSessio = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Inici.setVisible(true);
				IniciSesio.setVisible(false);
			}
		};
		IniciSesio.getBtnTornarIniciSessio().addActionListener(actionListenerbtnTornarIniciSessio);

		actionListenerbtnSaloDeLa = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String nom = IniciSesio.getTextFieldNom().getText();
				//Model.insertRecord(nom, Principal.rdbtnDificultad());
				Principal.setTextAreaFama(Model.insertRecordEnJTextArea(nom, Principal.rdbtnDificultad()));
			}
		};
		Principal.getBtnSaloDeLa().addActionListener(actionListenerbtnSaloDeLa);
	}
}
//Ejemplo controlador de otra evaluable:

//private Model Model;
//private Vista Vista = new Vista();
//private IniciSesio IniciSesio;
//private ActionListener actionListenerBtnInici;
//private ActionListener actionListenerExecutarConsulta;
//private ActionListener actionListenerTancarSessio;
//private ActionListener actionListenerTancarConexioBD;
//
//Controlador(IniciSesio IniciSesio, Model Model) {
//	this.IniciSesio = IniciSesio;
//	this.Model = Model;
//	Control();
//}
//
//public void Control() {
//
//	actionListenerBtnInici = new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
//			Model.openConexion("client");
//			String nomString = IniciSesio.getNom().getText();
//
//			char[] password = IniciSesio.getContrasenya().getPassword();
//			String contrasenyaString = new String(password);
//
//			if (nomString.isEmpty() || contrasenyaString.isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Els apartats no poden estar buits.", "Error",
//						JOptionPane.ERROR_MESSAGE);
//				Model.closeConexion();
//
//			} else if (!Model.comprobarUserExisteix(nomString)) {
//				JOptionPane.showMessageDialog(null, "L'usuari especificat no existeix.", "Error",
//						JOptionPane.ERROR_MESSAGE);
//				Model.closeConexion();
//
//			} else if (!Model.comprobarContrasenya(nomString, contrasenyaString)) {
//				JOptionPane.showMessageDialog(null, "La contrasenya no es correcta.", "Error",
//						JOptionPane.ERROR_MESSAGE);
//				Model.closeConexion();
//			} else {
//				// Comprobacions correctes
//				if (nomString.equals("administrador1")) {
//					Model.closeConexion();
//					Model.openConexion("admin");
//				}
//				IniciSesio.setVisible(false);
//				IniciSesio.vaciarCamps();
//				Vista.setVisible(true);
//			}
//		}
//	};
//	IniciSesio.getIniciSessi().addActionListener(actionListenerBtnInici);
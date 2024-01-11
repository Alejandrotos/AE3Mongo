package ae3;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.plaf.synth.Region;

public class Controlador {
	private Model Model;
	private VistaInicio Inici;
	// private VistaRegistro Registro = new VistaRegistro();
	private VistaInicioSesion IniciSesio = new VistaInicioSesion();
	private VistaPrincipal vistaPrincipal = new VistaPrincipal(); // setVisible(true) para hacer pruebas sin iniciar
																	// sesion
	private ActionListener actionListenerbtnIniciDeSesio;
	private ActionListener actionListenerbtnRegistrarse;
	// Aqui irían ActionsListeners de VistaInicioSesion
	// Aqui irían ActionsListeners de VistaInicioSesion
	private ActionListener actionListenerbtnJugar;

	Controlador(VistaInicio Inici, Model Model) {
		this.Inici = Inici;
		this.Model = Model;
		Control();
	}

	public void Control() {
		actionListenerbtnIniciDeSesio = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IniciSesio.setVisible(true);
			}
		};
		Inici.getbtnIniciDeSesio().addActionListener(actionListenerbtnIniciDeSesio);

		// Funcionalidades de VistaPrincipal
		actionListenerbtnJugar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton[] botonsImatges = VistaPrincipal.getButtonsImatgesArray();
				ArrayList<String> rutasDeImages = Model.getRutaDeImages();

				File carpetaImg = new File("img");
				String[] archivos = carpetaImg.list();

				// if (!carpetaImg.exists() || archivos == null || archivos.length == 0)
				Model.extraureImatges();

				// Modo de juego 4x2
				if (vistaPrincipal.getRdbtn4x2().isSelected()) {
					/*
					 * for (int i = 0; i < 8; i++) { botonsImatges[i].setEnabled(true);
					 * botonsImatges[i].setBackground(Color.WHITE); // ImageIcon icon = new
					 * ImageIcon(rutasDeImages.get(i)); // botonsImatges[i].setIcon(icon);
					 * 
					 * }
					 */
					asignarIconosAleatoriosABotones(botonsImatges, rutasDeImages, 8);
					return;

				}
				// Modo de juego 4x4

				/*
				 * for (int i = 0; i < botonsImatges.length; i++) {
				 * botonsImatges[i].setEnabled(true);
				 * botonsImatges[i].setBackground(Color.WHITE);
				 * 
				 * }
				 */
				asignarIconosAleatoriosABotones(botonsImatges, rutasDeImages, 16);
				return;

			}
		};
		vistaPrincipal.getBtnJugar().addActionListener(actionListenerbtnJugar);
	}

	private void asignarIconosAleatoriosABotones(JButton[] botones, ArrayList<String> rutasDeImagenes,
			int cantidadBotones) {
		if (rutasDeImagenes.size() < cantidadBotones / 2) {
			System.out.println("No hay suficientes imágenes para el número de botones.");
			return;
		}

		ArrayList<Integer> indicesImagenes = new ArrayList<>();
		for (int i = 0; i < rutasDeImagenes.size(); i++) {
			indicesImagenes.add(i);
		}
		Collections.shuffle(indicesImagenes);

		ArrayList<Integer> indicesBotones = new ArrayList<>();
		for (int i = 0; i < cantidadBotones; i++) {
			indicesBotones.add(i);
		}
		Collections.shuffle(indicesBotones);

		for (int i = 0; i < cantidadBotones; i++) {
			int indiceImagen = indicesImagenes.get(i / 2);
			String rutaImagen = rutasDeImagenes.get(indiceImagen);
			ImageIcon icon = new ImageIcon(rutaImagen);

			int indiceBoton = indicesBotones.get(i);
			botones[indiceBoton].setEnabled(true);
			botones[indiceBoton].setBackground(Color.WHITE);
			botones[indiceBoton].setIcon(icon);
		}
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
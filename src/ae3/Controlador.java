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
	private ActionListener actionListenerBotonsPropiJoc;

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
		
		actionListenerBotonsPropiJoc = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				
			}
		};
		// Funcionalidades de VistaPrincipal
		actionListenerbtnJugar = new ActionListener() {
			JButton[] botonsImatges = VistaPrincipal.getButtonsImatgesArray();
			private ArrayList<String> rutasDeImages = Model.getRutaDeImages();
			private int cantidadBotones;
			private JButton primerBotonClicado;
			private JButton segundoBotonClicado;

			public void actionPerformed(ActionEvent e) {
				File carpetaImg = new File("img");
				String[] archivos = carpetaImg.list();

				// if (!carpetaImg.exists() || archivos == null || archivos.length == 0)
				Model.extraureImatges();

				Boolean modo4x2 = vistaPrincipal.getRdbtn4x2().isSelected();
				cantidadBotones = modo4x2 ? 8 : 16;
				
				asignarIconosAleatoriosABotones();
				
				 // Asignar ActionListener a los botones
		        for (JButton boton : botonsImatges) {
		            boton.addActionListener();
		        }
			}
		};
		vistaPrincipal.getBtnJugar().addActionListener(actionListenerbtnJugar);
	}

	private class BotonListener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JButton botonClicado = (JButton) e.getSource();

	        // Verificar si el botón ya ha sido emparejado
	        if ((boolean) botonClicado.getClientProperty("iconoMostrado")) {
	            return;
	        }

	        if (primerBotonClicado == null) {
	            // Primer clic
	            primerBotonClicado = botonClicado;
	            mostrarIcono(primerBotonClicado);
	        } else {
	            // Segundo clic
	            segundoBotonClicado = botonClicado;
	            mostrarIcono(segundoBotonClicado);

	            // Verificar si los iconos coinciden
	            if (sonIconosIguales(primerBotonClicado, segundoBotonClicado)) {
	                // Dejar los iconos permanentemente
	                primerBotonClicado.putClientProperty("iconoMostrado", true);
	                segundoBotonClicado.putClientProperty("iconoMostrado", true);

	                // Desactivar los botones emparejados
	                primerBotonClicado.setEnabled(false);
	                segundoBotonClicado.setEnabled(false);
	            } else {
	                // Ocultar los iconos después de un breve período
	                Timer timer = new Timer(1000, new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        ocultarIcono(primerBotonClicado);
	                        ocultarIcono(segundoBotonClicado);
	                    }
	                });
	                timer.setRepeats(false);
	                timer.start();
	            }

	            // Reiniciar variables para el siguiente par de clics
	            primerBotonClicado = null;
	            segundoBotonClicado = null;
	        }
	    }

	}
	private void mostrarIcono(JButton boton) {
        String rutaImagen = (String) boton.getClientProperty("rutaImagen");
        ImageIcon icon = new ImageIcon(rutaImagen);
        boton.setIcon(icon);
    }

    private void ocultarIcono(JButton boton) {
        boton.setIcon(null);
    }

    private boolean sonIconosIguales(JButton boton1, JButton boton2) {
        String ruta1 = (String) boton1.getClientProperty("rutaImagen");
        String ruta2 = (String) boton2.getClientProperty("rutaImagen");
        return ruta1.equals(ruta2);
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

	private ArrayList<String> obtenerRutasAleatorias(ArrayList<String> rutasDeImages) {
		ArrayList<String> rutasAleatorias = new ArrayList<>(rutasDeImages);
		Collections.shuffle(rutasAleatorias);
		return rutasAleatorias;
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
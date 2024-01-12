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
import javax.swing.Timer;
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
			private ArrayList<JButton> botonsImatges;
			private ArrayList<String> rutasDeImages;
			private int cantidadBotones;
			private JButton primerBotonClicado;
			private JButton segundoBotonClicado;

			public void actionPerformed(ActionEvent e) {
				botonsImatges = VistaPrincipal.getButtonsImatgesArray();
				rutasDeImages = Model.getRutaDeImages();

				// ArrayList<JButton> randomNutonsImatges = new
				// ArrayList<JButton>(botonsImatges);

				File carpetaImg = new File("img");
				String[] archivos = carpetaImg.list();

				Model.extraureImatges();

				// Verificar el modo de juego
				cantidadBotones = vistaPrincipal.getRdbtn4x2().isSelected() ? 8 : 16;

				asignarIconosAleatoriosABotones();

				// Asignar ActionListener a los botones
				for (JButton boton : botonsImatges) {
					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							manejarClicBoton(boton);
						}
					});
				}
			}

			private void asignarIconosAleatoriosABotones() {
				// Obtén las rutas de las imágenes aleatorias
				/*
				 * ArrayList<String> rutasAleatorias = obtenerRutasAleatorias();
				 * ArrayList<JButton> botonsAletoris = obtindreBotonsAletoris();
				 */

				ArrayList<Integer> indicesImagenes = new ArrayList<>();
				for (int i = 0; i < rutasDeImages.size(); i++) {
					indicesImagenes.add(i);
				}
				Collections.shuffle(indicesImagenes);

				ArrayList<Integer> indicesBotones = new ArrayList<>();
				for (int i = 0; i < cantidadBotones; i++) {
					indicesBotones.add(i);
				}
				Collections.shuffle(indicesBotones);

				// Asigna la información a los botones
				for (int i = 0; i < cantidadBotones; i++) {
					int indiceImagen = indicesImagenes.get(i / 2);
					String rutaImagen = rutasDeImages.get(indiceImagen);

					int indiceBoton = indicesBotones.get(i);
					botonsImatges.get(indiceBoton).setEnabled(true);
					botonsImatges.get(indiceBoton).setBackground(Color.WHITE);

					botonsImatges.get(indiceBoton).putClientProperty("rutaImatge", rutaImagen);
					botonsImatges.get(indiceBoton).putClientProperty("iconoVisible", false);
				}
			}

			private void manejarClicBoton(JButton botonClicado) {

				// Verificar si el botón ya ha sido emparejado
				if ((boolean) botonClicado.getClientProperty("iconoVisible")) {
					return;
				}

				if (primerBotonClicado == null) {
					// Primer clic
					primerBotonClicado = botonClicado;
					mostrarIcono(primerBotonClicado);
				} else {
					// Segundo clic

					Timer timer = null;
					segundoBotonClicado = botonClicado;
					mostrarIcono(segundoBotonClicado);

					// Verificar si los iconos coinciden
					if (sonIconosIguales(primerBotonClicado, segundoBotonClicado)) {
						// Dejar los iconos permanentemente
						primerBotonClicado.putClientProperty("iconoVisible", true);
						segundoBotonClicado.putClientProperty("iconoVisible", true);

						// Desactivar los botones emparejados
						primerBotonClicado.setEnabled(false);
						segundoBotonClicado.setEnabled(false);

						primerBotonClicado = null;
						segundoBotonClicado = null;
					} else {
						// Ocultar los iconos después de un breve período
						timer = new Timer(500, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								ocultarIcono(primerBotonClicado);
								ocultarIcono(segundoBotonClicado);

								primerBotonClicado = null;
								segundoBotonClicado = null;
							}
						});
						timer.setRepeats(false);
						timer.start();
					}
				}
			}

			private void mostrarIcono(JButton boton) {
				String rutaImatge = (String) boton.getClientProperty("rutaImatge");
				ImageIcon icon = new ImageIcon(rutaImatge);
				boton.setIcon(icon);
			}

			private void ocultarIcono(JButton boton) {
				boton.setIcon(null);
			}

			private boolean sonIconosIguales(JButton boton1, JButton boton2) {
				String ruta1 = (String) boton1.getClientProperty("rutaImatge");
				String ruta2 = (String) boton2.getClientProperty("rutaImatge");
				return ruta1.equals(ruta2);
			}
		};

		vistaPrincipal.getBtnJugar().addActionListener(actionListenerbtnJugar);
	}

}
package ae3;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.plaf.synth.Region;

public class Controlador {
	private Model model;
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
	private ActionListener actionListenerbtnGuardar;
	private int quantitatBotons;
	private ArrayList<JButton> botonsImatges;

	Controlador(VistaInicio Inici, Model Model) {
		this.Inici = Inici;
		this.model = Model;
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
			private ArrayList<String> rutasDeImages;
			private JButton primerBotonClicado;
			private JButton segundoBotonClicado;

			public void actionPerformed(ActionEvent e) {
				model.iniciarContadorPartida();
				model.extraureImatges();

				botonsImatges = VistaPrincipal.getButtonsImatgesArray();
				rutasDeImages = model.getRutaDeImages();

				quantitatBotons = vistaPrincipal.getRdbtn4x2().isSelected() ? 8 : 16;

				asignarIconosAleatoriosABotones();

				for (JButton boton : botonsImatges) {
					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							manejarClicBoton(boton);
						}
					});
				}
			}

			private void asignarIconosAleatoriosABotones() {
				ArrayList<Integer> indicesImagenes = new ArrayList<>();
				for (int i = 0; i < rutasDeImages.size(); i++) {
					indicesImagenes.add(i);
				}
				Collections.shuffle(indicesImagenes);

				ArrayList<Integer> indicesBotones = new ArrayList<>();
				for (int i = 0; i < quantitatBotons; i++) {
					indicesBotones.add(i);
				}
				Collections.shuffle(indicesBotones);

				for (int i = 0; i < quantitatBotons; i++) {
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

				if ((boolean) botonClicado.getClientProperty("iconoVisible")) {
					return;
				}

				if (primerBotonClicado == null) {
					// Primer clic
					primerBotonClicado = botonClicado;
					mostrarIcono(primerBotonClicado);
				} else {
					// Segon clic

					Timer timer = null;
					segundoBotonClicado = botonClicado;
					mostrarIcono(segundoBotonClicado);

					if (sonIconosIguales(primerBotonClicado, segundoBotonClicado)) {
						primerBotonClicado.putClientProperty("iconoVisible", true);
						segundoBotonClicado.putClientProperty("iconoVisible", true);

						primerBotonClicado.setEnabled(false);
						segundoBotonClicado.setEnabled(false);

						if (finalDelJoc())
							model.detindreContador();

						primerBotonClicado = null;
						segundoBotonClicado = null;
					} else {
						timer = new Timer(300, new ActionListener() {
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

			private boolean finalDelJoc() {
				int contador = 0;
				for (JButton jButton : botonsImatges) {
					contador++;
					if (contador > quantitatBotons)
						break;
					if (!(boolean) jButton.getClientProperty("iconoVisible")) {
						return false;
					}
				}
				return true;
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

		actionListenerbtnGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Mensaje de enhorabuena si es el mejor tiempo del usuari
				int bestRecord = model.selectBestRecord(quantitatBotons);
				if (model.newRecord(bestRecord)) {
					JOptionPane.showMessageDialog(null, "Has aconseguit un nou record.", "FELICITATS",
							JOptionPane.INFORMATION_MESSAGE);
				}
				// Insert de timestamp y duracioTotal en records
				model.insertRecord("prueba", quantitatBotons);
				JOptionPane.showMessageDialog(null, "Temps guardat amb exit.", "Guardat", JOptionPane.INFORMATION_MESSAGE);

				// Resetear interfaz
				for (JButton boton : botonsImatges) {
					boton.setEnabled(false);
					boton.setForeground(Color.WHITE);
					boton.setBackground(Color.DARK_GRAY);
					boton.putClientProperty("rutaImatge", null);
					boton.putClientProperty("iconoVisible", false);
					boton.setIcon(null);
				}
			}
		};
		vistaPrincipal.getBtnGuardar().addActionListener(actionListenerbtnGuardar);
	}

}
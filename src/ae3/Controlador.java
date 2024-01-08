package ae3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	private Model Model;
	private VistaInicio Inicio;
	//private VistaRegistro Registro = new VistaRegistro();
	//private VistaInicioSesion InicioSesion = new VistaInicioSesion();
	private VistaPrincipal Principal = new VistaPrincipal();
	private ActionListener actionListenerbtnInicioDeSesion;
	private ActionListener actionListenerbtnRegistrarse;

	Controlador(VistaInicio Inicio, Model Model) {
		this.Inicio = Inicio;
		this.Model = Model;
		Control();
	}
	
	public void Control() {
		actionListenerbtnInicioDeSesion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			VistaInicioSesion InicioSesion = new VistaInicioSesion();
			};
	}
}

package ae3;

public class Principal {

	public static void main(String[] args) {
		VistaInicio vistaInicio = new VistaInicio();
		Model model = new Model();
		Controlador controlador = new Controlador(vistaInicio, model);

	}

}

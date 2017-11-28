package co.ceiba.parqueadero.negocio.excepcion;

public class VehiculoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public VehiculoException(String message){
		super(message);
	}
}

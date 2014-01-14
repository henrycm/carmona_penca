package co.com.multinivel.exception;

public class MultinivelServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public MultinivelServiceException() {
	}

	public MultinivelServiceException(String mensaje, Class clase) {
		super(" Error en el servicio:" + clase.getName() + ":" + mensaje);
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.exception.MultinivelServiceException
 */
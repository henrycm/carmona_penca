package co.com.multinivel.exception;

public class MultinivelDAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public MultinivelDAOException() {
	}

	public MultinivelDAOException(String mensaje, Class clase) {
		super("El sistema ha presentando un error:" + mensaje);
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.exception.MultinivelDAOException
 */
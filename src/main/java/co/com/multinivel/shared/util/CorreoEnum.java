package co.com.multinivel.shared.util;

public enum CorreoEnum {
	CORREO_HOST("smtp.gmail.com"), CORREO_ENABLE("true"), CORREO_PORT("587"), CORREO_AUTH("true"), CORREO_FROM("RedMultiAloe@gmail.com"), CORREO_TO(
			"RedMultiAloe@gmail.com"), CORREO_CLAVE("multi@loe"), CORREO_USER("RedMultiAloe@gmail.com");

	private String valor;

	private CorreoEnum(String recurso) {
		setValor(recurso);
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.shared.util.CorreoEnum
 */
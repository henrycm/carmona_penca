package co.com.multinivel.shared.util;

public enum CorreoEnum {
	CORREO_HOST("smtp.1and1.com"), CORREO_ENABLE("true"), CORREO_PORT("587"), CORREO_AUTH("true"), CORREO_FROM("jcamilo@grupovegaflor.com"), CORREO_TO(
			"jcamilo@grupovegaflor.com"), CORREO_CLAVE("sistemas"), CORREO_USER("jcamilo@grupovegaflor.com");

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
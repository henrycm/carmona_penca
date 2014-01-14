package co.com.multinivel.util;

public enum CorreoEnum {
	CORREO_HOST("smtp.gmail.com"), CORREO_ENABLE("true"), CORREO_PORT("587"), CORREO_AUTH("true"), CORREO_FROM(
			"inscripciones@multinivel.com"), CORREO_TO("multi_aloe@hotmail.com"), CORREO_CLAVE(
			"cambiar_en_produccion"), CORREO_USER("servicioalcliente@multialoe.com");

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
 * Qualified Name: co.com.multinivel.util.CorreoEnum
 */
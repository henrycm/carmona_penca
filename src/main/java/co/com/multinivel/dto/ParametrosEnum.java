package co.com.multinivel.dto;

public enum ParametrosEnum {
	URL_DATABASE("jdbc:mysql://localhost/multinivel"), DRIVER_DATABASE("com.mysql.jdbc.Driver"), USUARIO(
			"root"), PASSWORD("");

	private String valor;

	private ParametrosEnum(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dto.ParametrosEnum
 * 
 * 
 */
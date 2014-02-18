package co.com.multinivel.shared.util;

public enum ParametrosEnum {
	URL_DATABASE("jdbc:mysql://localhost/multinivel"), DRIVER_DATABASE("com.mysql.jdbc.Driver"), USUARIO(
			"root"), PASSWORD("root"), TAM_PAGINA("100");

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

	public int getValorInt() {
		return Integer.parseInt(getValor());
	}
}
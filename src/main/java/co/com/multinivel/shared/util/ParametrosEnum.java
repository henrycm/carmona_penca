package co.com.multinivel.shared.util;

public enum ParametrosEnum {
	URL_DATABASE("jdbc:jtds:sqlserver://localhost:1433/Multinivel"), DRIVER_DATABASE(
			"net.sourceforge.jtds.jdbc.Driver"), USUARIO("sa"), PASSWORD(
			"RedAloe2013"), TAM_PAGINA("100");

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
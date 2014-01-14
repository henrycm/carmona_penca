package co.com.multinivel.shared.dto;

public class CompensacionAfiliadoDTO {
	private String afiliado;
	private String nombreAfiliado;
	private double consumo;
	private double comisionDinero;
	private double comisionProducto;
	private double comisionTotal;
	private int nivel;
	private String periodo;
	private String red;

	public String getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	public double getConsumo() {
		return this.consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public double getComisionDinero() {
		return this.comisionDinero;
	}

	public void setComisionDinero(double comisionDinero) {
		this.comisionDinero = comisionDinero;
	}

	public double getComisionProducto() {
		return this.comisionProducto;
	}

	public void setComisionProducto(double comisionProducto) {
		this.comisionProducto = comisionProducto;
	}

	public double getComisionTotal() {
		return this.comisionTotal;
	}

	public void setComisionTotal(double comisionTotal) {
		this.comisionTotal = comisionTotal;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getNombreAfiliado() {
		return this.nombreAfiliado;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.CompensacionAfiliadoDTO
 */
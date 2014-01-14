package co.com.multinivel.dto;

public class LiquidacionDTO {
	private String red;
	private String padre;
	private int nivelPadre;
	private int nivelReal;
	private int cantidad;
	private int nivelPremio;

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getPadre() {
		return this.padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	public int getNivelPadre() {
		return this.nivelPadre;
	}

	public void setNivelPadre(int nivelPadre) {
		this.nivelPadre = nivelPadre;
	}

	public int getNivelPremio() {
		return this.nivelPremio;
	}

	public void setNivelPremio(int nivelPremio) {
		this.nivelPremio = nivelPremio;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setNivelReal(int nivelReal) {
		this.nivelReal = nivelReal;
	}

	public int getNivelReal() {
		return this.nivelReal;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dto.LiquidacionDTO
 */
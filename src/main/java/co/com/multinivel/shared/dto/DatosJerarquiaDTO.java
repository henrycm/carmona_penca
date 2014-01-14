package co.com.multinivel.shared.dto;

public class DatosJerarquiaDTO {
	private String cedulaPadre;
	private String cedulaHijo;
	private int nivel;

	public String getCedulaPadre() {
		return this.cedulaPadre;
	}

	public void setCedulaPadre(String cedulaPadre) {
		this.cedulaPadre = cedulaPadre;
	}

	public String getCedulaHijo() {
		return this.cedulaHijo;
	}

	public void setCedulaHijo(String cedulaHijo) {
		this.cedulaHijo = cedulaHijo;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return this.nivel;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.DatosJerarquiaDTO
 */
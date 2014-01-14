package co.com.multinivel.shared.dto;

import java.math.BigDecimal;

public class HijoConsumoDTO {
	private String cedulaPadre;
	private String cedula;
	private Integer nivel;
	private BigDecimal totalPedido;

	public String getCedulaPadre() {
		return this.cedulaPadre;
	}

	public void setCedulaPadre(String cedulaPadre) {
		this.cedulaPadre = cedulaPadre;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public BigDecimal getTotalPedido() {
		return this.totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.HijoConsumoDTO
 */
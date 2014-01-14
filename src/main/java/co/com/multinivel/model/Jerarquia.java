package co.com.multinivel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Jerarquia implements Serializable {
	private static final long serialVersionUID = 1L;
	private JerarquiaPK id;
	private Date fechaIngreso;
	private BigDecimal nivel;
	private String red;
	private String usuarioIngreso;

	public JerarquiaPK getId() {
		return this.id;
	}

	public void setId(JerarquiaPK id) {
		this.id = id;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public BigDecimal getNivel() {
		return this.nivel;
	}

	public void setNivel(BigDecimal nivel) {
		this.nivel = nivel;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getUsuarioIngreso() {
		return this.usuarioIngreso;
	}

	public void setUsuarioIngreso(String usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.Jerarquia
 */
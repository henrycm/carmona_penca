package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_cantidad_afiliaciones_distribuidor")
public class CantidadAfiliacionesDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Distribuidor")
	private String distribuidor;
	@Column(name = "Cantidad")
	private int cantidad;

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidor
 */
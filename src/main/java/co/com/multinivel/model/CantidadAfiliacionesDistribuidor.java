package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_cantidad_afiliaciones_distribuidor")
public class CantidadAfiliacionesDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CantidadAfiliacionesDistribuidorPK id;
	private int cantidad;

	public CantidadAfiliacionesDistribuidorPK getId() {
		return this.id;
	}

	public void setId(CantidadAfiliacionesDistribuidorPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.CantidadAfiliacionesDistribuidor
 * 
 * 
 */
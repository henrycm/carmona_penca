package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CantidadAfiliacionesDistribuidorPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private String distribuidor;
	private String periodo;

	public String getDistribuidor() {
		return this.distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CantidadAfiliacionesDistribuidorPK)) {
			return false;
		}
		CantidadAfiliacionesDistribuidorPK castOther = (CantidadAfiliacionesDistribuidorPK) other;

		return (this.distribuidor.equals(castOther.distribuidor))
				&& (this.periodo.equals(castOther.periodo));
	}

	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * 31 + this.distribuidor.hashCode();
		hash = hash * 31 + this.periodo.hashCode();

		return hash;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.CantidadAfiliacionesDistribuidorPK
 */
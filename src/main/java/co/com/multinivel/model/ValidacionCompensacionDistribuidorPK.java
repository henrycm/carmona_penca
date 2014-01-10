package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ValidacionCompensacionDistribuidorPK implements Serializable {
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
		if (!(other instanceof ValidacionCompensacionDistribuidorPK)) {
			return false;
		}
		ValidacionCompensacionDistribuidorPK castOther = (ValidacionCompensacionDistribuidorPK) other;

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
 * Qualified Name: co.com.multinivel.model.ValidacionCompensacionDistribuidorPK
 * 
 * 
 */
package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LiquidacionCompensacionPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private String red;
	private String periodo;

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
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
		if (!(other instanceof LiquidacionCompensacionPK)) {
			return false;
		}
		LiquidacionCompensacionPK castOther = (LiquidacionCompensacionPK) other;

		return (this.red.equals(castOther.red)) && (this.periodo.equals(castOther.periodo));
	}

	public int hashCode() {
		int prime = 31;
		int hash = 17;
		hash = hash * 31 + this.red.hashCode();
		hash = hash * 31 + this.periodo.hashCode();

		return hash;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.LiquidacionCompensacionPK
 */
package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JerarquiaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "CEDULA_PADRE")
	private String cedulaPadre;
	@Column(name = "CEDULA_HIJO")
	private String cedulaHijo;

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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JerarquiaPK)) {
			return false;
		}
		JerarquiaPK castOther = (JerarquiaPK) other;

		return (this.cedulaPadre.equals(castOther.cedulaPadre)) && (this.cedulaHijo.equals(castOther.cedulaHijo));
	}

	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + this.cedulaPadre.hashCode();
		hash = hash * 31 + this.cedulaHijo.hashCode();

		return hash;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.JerarquiaPK
 */
package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventarioDistribuidorPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "distribuidor", nullable = false)
	private String distribuidor;

	@Column(name = "cod_producto", nullable = false)
	private String cod_producto;

	public InventarioDistribuidorPK() {
		super();
	}

	public InventarioDistribuidorPK(String distribuidor, String cod_producto) {
		super();
		this.distribuidor = distribuidor;
		this.cod_producto = cod_producto;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getCod_producto() {
		return cod_producto;
	}

	public void setCod_producto(String cod_producto) {
		this.cod_producto = cod_producto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InventarioDistribuidorPK)) {
			return false;
		}
		InventarioDistribuidorPK castOther = (InventarioDistribuidorPK) other;

		return (this.distribuidor.equals(castOther.distribuidor))
				&& (this.cod_producto.equals(castOther.cod_producto));
	}
}

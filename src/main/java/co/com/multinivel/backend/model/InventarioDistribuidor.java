package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_inventario_distribuidor")
public class InventarioDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InventarioDistribuidorPK pk;

	private int cantidad;
	private double valor_total;

	public InventarioDistribuidor(InventarioDistribuidorPK pk) {
		super();
		this.pk = pk;
	}

	public InventarioDistribuidorPK getPk() {
		return pk;
	}

	public void setPk(InventarioDistribuidorPK pk) {
		this.pk = pk;
	}

	public InventarioDistribuidor()
	{
		super();
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

}

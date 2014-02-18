package co.com.multinivel.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_det_consumos")
public class DetConsumo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int cantidad;
	@Column(name = "codigo_producto")
	private String codigoProducto;
	@Column(name = "TOTALPRODUCTO")
	private BigDecimal totalProducto;
	private BigDecimal valorUnitario;
	private transient String nombreProducto;
	@ManyToOne
	@JoinColumn(name = "codigo_consumo")
	private Consumo Consumo;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public BigDecimal getTotalProducto() {
		return this.totalProducto;
	}

	public void setTotalProducto(BigDecimal totalproducto) {
		this.totalProducto = totalproducto;
	}

	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Consumo getConsumo() {
		return this.Consumo;
	}

	public void setConsumo(Consumo TConsumo) {
		this.Consumo = TConsumo;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.DetConsumo
 */
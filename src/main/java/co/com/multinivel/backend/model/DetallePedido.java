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
@Table(name = "t_det_pedidos")
public class DetallePedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private int cantidad;
	@Column(name = "codigo_producto")
	private String codigoProducto;
	private BigDecimal totalProducto;
	private BigDecimal valorUnitario;
	private transient String nombreProducto;
	private transient BigDecimal valorUnitarioAfiliado;
	private transient BigDecimal totalProductoAfiliado;
	@ManyToOne
	@JoinColumn(name = "codigo_pedido")
	private Pedido pedido;

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

	public void setTotalProducto(BigDecimal totalProducto) {
		this.totalProducto = totalProducto;
	}

	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido TPedido) {
		this.pedido = TPedido;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public BigDecimal getValorUnitarioAfiliado() {
		return valorUnitarioAfiliado;
	}

	public void setValorUnitarioAfiliado(BigDecimal valorUnitarioAfiliado) {
		this.valorUnitarioAfiliado = valorUnitarioAfiliado;
	}

	public BigDecimal getTotalProductoAfiliado() {
		return totalProductoAfiliado;
	}

	public void setTotalProductoAfiliado(BigDecimal totalProductoAfiliado) {
		this.totalProductoAfiliado = totalProductoAfiliado;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.DetallePedido
 */
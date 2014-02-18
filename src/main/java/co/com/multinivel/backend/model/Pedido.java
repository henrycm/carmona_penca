package co.com.multinivel.backend.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_pedido")
	private int codigoPedido;
	private String afiliado;
	private String distribuidor;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private BigDecimal totalPedido;
	private BigDecimal transporte;
	@OneToMany(targetEntity = DetallePedido.class, mappedBy = "pedido", cascade = { javax.persistence.CascadeType.ALL })
	private Set<DetallePedido> TDetPedidos;

	public int getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public String getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotalPedido() {
		return this.totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Set<DetallePedido> getTDetPedidos() {
		return this.TDetPedidos;
	}

	public void setTDetPedidos(Set<DetallePedido> TDetPedidos) {
		this.TDetPedidos = TDetPedidos;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getDistribuidor() {
		return this.distribuidor;
	}

	public void setTransporte(BigDecimal transporte) {
		this.transporte = transporte;
	}

	public BigDecimal getTransporte() {
		return this.transporte;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.Pedido
 */
package co.com.multinivel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "t_consumos")
public class Consumo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codigo_consumo")
	private int codigoConsumo;
	private String afiliado;
	private String distribuidor;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private BigDecimal totalpedido;
	@OneToMany(targetEntity = DetConsumo.class, mappedBy = "Consumo", cascade = { javax.persistence.CascadeType.ALL })
	private List<DetConsumo> TDetConsumos;

	public int getCodigoConsumo() {
		return this.codigoConsumo;
	}

	public void setCodigoConsumo(int codigoConsumo) {
		this.codigoConsumo = codigoConsumo;
	}

	public String getAfiliado() {
		return this.afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	public String getDistribuidor() {
		return this.distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTotalpedido() {
		return this.totalpedido;
	}

	public void setTotalpedido(BigDecimal totalpedido) {
		this.totalpedido = totalpedido;
	}

	public List<DetConsumo> getTDetConsumos() {
		return this.TDetConsumos;
	}

	public void setTDetConsumos(List<DetConsumo> TDetConsumos) {
		this.TDetConsumos = TDetConsumos;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.Consumo
 */
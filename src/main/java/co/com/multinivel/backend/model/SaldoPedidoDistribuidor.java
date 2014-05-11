package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_saldos_pedido_distribuidor")
public class SaldoPedidoDistribuidor implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Distribuidor")
	private String distribuidor;
	@Column(name = "Saldo")
	private double saldo;
	@Column(name = "SaldoAbonado")
	private double saldoAbonado;

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getSaldoAbonado() {
		return saldoAbonado;
	}

	public void setSaldoAbonado(double saldoAbonado) {
		this.saldoAbonado = saldoAbonado;
	}
}
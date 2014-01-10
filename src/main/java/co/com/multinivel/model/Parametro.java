package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_parametros")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "NOMBRE_PARAMETRO")
	private String nombreParametro;
	@Column(name = "VALOR")
	private String valor;

	public String getNombreParametro() {
		return this.nombreParametro;
	}

	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.Parametro
 * 
 * 
 */
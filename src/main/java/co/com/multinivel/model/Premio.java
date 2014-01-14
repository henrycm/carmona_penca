package co.com.multinivel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_premios")
public class Premio implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int codigo;
	private int consumos;
	private int consumoMaximo;
	private String mantenerConsumo3Meses;
	private String nivel;
	private String nombre;

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getConsumos() {
		return this.consumos;
	}

	public void setConsumos(int consumos) {
		this.consumos = consumos;
	}

	public String getMantenerConsumo3Meses() {
		return this.mantenerConsumo3Meses;
	}

	public void setMantenerConsumo3Meses(String mantenerConsumo3Meses) {
		this.mantenerConsumo3Meses = mantenerConsumo3Meses;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setConsumoMaximo(int consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}

	public int getConsumoMaximo() {
		return this.consumoMaximo;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.Premio
 */
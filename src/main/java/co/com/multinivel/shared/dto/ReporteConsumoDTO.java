package co.com.multinivel.shared.dto;

import java.io.Serializable;

public class ReporteConsumoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String afiliado;
	private double comision;
	private long nivel;
	private String periodo;
	private String distribuidor;
	private String nom_distribuidor;
	private String nom_patrocinador;
	private String nom_afiliado;
	private double consumoAfiliado;
	private String papa;
	private double comisionProducto;
	private double comisionDinero;

	public String getAfiliado() {
		return afiliado;
	}

	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public long getNivel() {
		return nivel;
	}

	public void setNivel(long nivel) {
		this.nivel = nivel;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public String getNom_distribuidor() {
		return nom_distribuidor;
	}

	public void setNom_distribuidor(String nom_distribuidor) {
		this.nom_distribuidor = nom_distribuidor;
	}

	public String getNom_patrocinador() {
		return nom_patrocinador;
	}

	public void setNom_patrocinador(String nom_patrocinador) {
		this.nom_patrocinador = nom_patrocinador;
	}

	public String getNom_afiliado() {
		return nom_afiliado;
	}

	public void setNom_afiliado(String nom_afiliado) {
		this.nom_afiliado = nom_afiliado;
	}

	public double getConsumoAfiliado() {
		return consumoAfiliado;
	}

	public void setConsumoAfiliado(double consumoAfiliado) {
		this.consumoAfiliado = consumoAfiliado;
	}

	public String getPapa() {
		return papa;
	}

	public void setPapa(String papa) {
		this.papa = papa;
	}

	public double getComisionProducto() {
		return comisionProducto;
	}

	public void setComisionProducto(double comisionProducto) {
		this.comisionProducto = comisionProducto;
	}

	public double getComisionDinero() {
		return comisionDinero;
	}

	public void setComisionDinero(double comisionDinero) {
		this.comisionDinero = comisionDinero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

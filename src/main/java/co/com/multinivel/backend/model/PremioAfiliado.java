package co.com.multinivel.backend.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_premios_afiliado_periodo")
public class PremioAfiliado implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String cedula;
	private String periodo;
	private int premio;

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getPremio() {
		return this.premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.model.PremioAfiliado
 */
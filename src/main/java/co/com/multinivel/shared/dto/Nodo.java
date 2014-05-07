package co.com.multinivel.shared.dto;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private String cedula;
	private String cedulaPadre;
	private String nombre;
	private String distribuidor;
	private List<Nodo> hijos;
	private int nivel;
	private String telefono;
	private String direccion;
	private String email;

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Nodo() {
		this.hijos = new ArrayList<Nodo>();
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCedulaPadre() {
		return this.cedulaPadre;
	}

	public void setCedulaPadre(String cedulaPadre) {
		this.cedulaPadre = cedulaPadre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDistribuidor() {
		return this.distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public List<Nodo> getHijos() {
		return this.hijos;
	}

	public void setHijos(List<Nodo> hijos) {
		this.hijos = hijos;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getNivel() {
		return this.nivel;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.Nodo
 */
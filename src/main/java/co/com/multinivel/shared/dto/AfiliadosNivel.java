package co.com.multinivel.shared.dto;

public class AfiliadosNivel {
	private int nivel;
	private String red;
	private String cedula;
	private String nombre;
	private String cedulaPadre;
	private String nombrePadre;
	private double comisionTotal;
	private double comisionProducto;
	private double comisionDinero;
	private double consumoTotal;

	public double getComisionTotal() {
		return this.comisionTotal;
	}

	public void setComisionTotal(double comisionTotal) {
		this.comisionTotal = comisionTotal;
	}

	public double getComisionDinero() {
		return this.comisionDinero;
	}

	public void setComisionDinero(double comisionDinero) {
		this.comisionDinero = comisionDinero;
	}

	public int getNivel() {
		return this.nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getRed() {
		return this.red;
	}

	public void setRed(String red) {
		this.red = red;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedulaPadre() {
		return this.cedulaPadre;
	}

	public void setCedulaPadre(String cedulaPadre) {
		this.cedulaPadre = cedulaPadre;
	}

	public String getNombrePadre() {
		return this.nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public void setComisionProducto(double comisionProducto) {
		this.comisionProducto = comisionProducto;
	}

	public double getComisionProducto() {
		return this.comisionProducto;
	}

	public void setConsumoTotal(double consumoTotal) {
		this.consumoTotal = consumoTotal;
	}

	public double getConsumoTotal() {
		return this.consumoTotal;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.AfiliadosNivel
 */
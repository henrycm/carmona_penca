package co.com.multinivel.shared.dto;

import java.math.BigDecimal;

public class ProductoDTO {
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombreProducto;
	private BigDecimal precioAfiliado;
	private BigDecimal precioDistribuidor;
	private BigDecimal precioPublico;
	private String tipo;
	private String codigoTipo;
	private int cantidad;
	private double valor;
	private double porcentajeCantidad;
	private double porcentajeValor;
	private int disponibilidadDist;

	public int getDisponibilidadDist() {
		return disponibilidadDist;
	}

	public void setDisponibilidadDist(int disponibilidadDist) {
		this.disponibilidadDist = disponibilidadDist;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getPorcentajeCantidad() {
		return this.porcentajeCantidad;
	}

	public void setPorcentajeCantidad(double porcentajeCantidad) {
		this.porcentajeCantidad = porcentajeCantidad;
	}

	public double getPorcentajeValor() {
		return this.porcentajeValor;
	}

	public void setPorcentajeValor(double porcentajeValor) {
		this.porcentajeValor = porcentajeValor;
	}

	public static long getSerialVersionUID() {
		return 1L;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecioAfiliado() {
		return this.precioAfiliado;
	}

	public void setPrecioAfiliado(BigDecimal precioAfiliado) {
		this.precioAfiliado = precioAfiliado;
	}

	public BigDecimal getPrecioDistribuidor() {
		return this.precioDistribuidor;
	}

	public void setPrecioDistribuidor(BigDecimal precioDistribuidor) {
		this.precioDistribuidor = precioDistribuidor;
	}

	public BigDecimal getPrecioPublico() {
		return this.precioPublico;
	}

	public void setPrecioPublico(BigDecimal precioPublico) {
		this.precioPublico = precioPublico;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public String getCodigoTipo() {
		return this.codigoTipo;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.ProductoDTO
 */
package co.com.multinivel.shared.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ConsumoDTO {
	private String codigoPedido;
	private String codigoConsumo;
	private BigDecimal totalPedido;
	private Date fecha;
	private String pedido;
	private String codigoProducto;
	private String nombreProducto;
	private BigDecimal valorUnitario;
	private int cantidad;
	private String nombreAfiliado;
	private String apellidoAfiliado;
	private String cedulaAfiliado;
	private String nombreDistribuidor;
	private String cedulaDistribuidor;
	private String telefono;
	private String ciudadEmpresario;
	private String periodo;
	private BigDecimal totalProducto;
	private double totalDistribuidor;

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCodigoPedido() {
		return this.codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public BigDecimal getTotalPedido() {
		return this.totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public String getPedido() {
		return this.pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getNombreAfiliado() {
		return this.nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getCedulaAfiliado() {
		return this.cedulaAfiliado;
	}

	public void setCedulaAfiliado(String cedulaAfiliado) {
		this.cedulaAfiliado = cedulaAfiliado;
	}

	public String getNombreDistribuidor() {
		return this.nombreDistribuidor;
	}

	public void setNombreDistribuidor(String nombreDistribuidor) {
		this.nombreDistribuidor = nombreDistribuidor;
	}

	public String getCedulaDistribuidor() {
		return this.cedulaDistribuidor;
	}

	public void setCedulaDistribuidor(String cedulaDistribuidor) {
		this.cedulaDistribuidor = cedulaDistribuidor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoProducto() {
		return this.codigoProducto;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorUnitario() {
		return this.valorUnitario;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setCiudadEmpresario(String ciudadEmpresario) {
		this.ciudadEmpresario = ciudadEmpresario;
	}

	public String getCiudadEmpresario() {
		return this.ciudadEmpresario;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setTotalProducto(BigDecimal totalProducto) {
		this.totalProducto = totalProducto;
	}

	public BigDecimal getTotalProducto() {
		return this.totalProducto;
	}

	public void setCodigoConsumo(String codigoConsumo) {
		this.codigoConsumo = codigoConsumo;
	}

	public String getCodigoConsumo() {
		return this.codigoConsumo;
	}

	public void setTotalDistribuidor(double totalDistribuidor) {
		this.totalDistribuidor = totalDistribuidor;
	}

	public double getTotalDistribuidor() {
		return this.totalDistribuidor;
	}

	public void setApellidoAfiliado(String apellidoAfiliado) {
		this.apellidoAfiliado = apellidoAfiliado;
	}

	public String getApellidoAfiliado() {
		return this.apellidoAfiliado;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.ConsumoDTO
 */
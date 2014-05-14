package co.com.multinivel.shared.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PedidoDTO {
	private String codigoPedido;
	private BigDecimal totalPedido;
	private Date fecha;
	private String pedido;
	private String codigoProducto;
	private String nombreProducto;
	private BigDecimal valorUnitario;
	private int cantidad;
	private String nombreAfiliado;
	private String cedulaAfiliado;
	private String nombreDistribuidor;
	private String cedulaDistribuidor;
	private String telefono;
	private String ciudadEmpresario;
	private BigDecimal transporte;
	private String periodo;
	private BigDecimal totalProducto;
	private BigDecimal totalProductoAfiliado;

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		this.totalPedido = totalPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getPedido() {
		return pedido;
	}

	public void setPedido(String pedido) {
		this.pedido = pedido;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getCedulaAfiliado() {
		return cedulaAfiliado;
	}

	public void setCedulaAfiliado(String cedulaAfiliado) {
		this.cedulaAfiliado = cedulaAfiliado;
	}

	public String getNombreDistribuidor() {
		return nombreDistribuidor;
	}

	public void setNombreDistribuidor(String nombreDistribuidor) {
		this.nombreDistribuidor = nombreDistribuidor;
	}

	public String getCedulaDistribuidor() {
		return cedulaDistribuidor;
	}

	public void setCedulaDistribuidor(String cedulaDistribuidor) {
		this.cedulaDistribuidor = cedulaDistribuidor;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudadEmpresario() {
		return ciudadEmpresario;
	}

	public void setCiudadEmpresario(String ciudadEmpresario) {
		this.ciudadEmpresario = ciudadEmpresario;
	}

	public BigDecimal getTransporte() {
		return transporte;
	}

	public void setTransporte(BigDecimal transporte) {
		this.transporte = transporte;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public BigDecimal getTotalProducto() {
		return totalProducto;
	}

	public void setTotalProducto(BigDecimal totalProducto) {
		this.totalProducto = totalProducto;
	}

	public BigDecimal getTotalProductoAfiliado() {
		return totalProductoAfiliado;
	}

	public void setTotalProductoAfiliado(BigDecimal totalProductoAfiliado) {
		this.totalProductoAfiliado = totalProductoAfiliado;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.shared.dto.PedidoDTO
 */
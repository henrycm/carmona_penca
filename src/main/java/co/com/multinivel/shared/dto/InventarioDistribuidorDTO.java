package co.com.multinivel.shared.dto;

import java.math.BigDecimal;

public class InventarioDistribuidorDTO {
	private String distribuidor;
	private ProductoDTO producto;
	private BigDecimal valorTotalAfiliado;
	private BigDecimal valorTotalDistribuidor;

	public String getDistribuidor() {
		return distribuidor;
	}

	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public BigDecimal getValorTotalAfiliado() {
		return valorTotalAfiliado;
	}

	public void setValorTotalAfiliado(BigDecimal valorTotalAfiliado) {
		this.valorTotalAfiliado = valorTotalAfiliado;
	}

	public BigDecimal getValorTotalDistribuidor() {
		return valorTotalDistribuidor;
	}

	public void setValorTotalDistribuidor(BigDecimal valorTotalDistribuidor) {
		this.valorTotalDistribuidor = valorTotalDistribuidor;
	}
}

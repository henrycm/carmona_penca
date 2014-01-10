package co.com.multinivel.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_productos")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String codigo;
	@Column(name = "nombre_producto")
	private String nombreProducto;
	@Column(name = "precio_afiliado")
	private BigDecimal precioAfiliado;
	private String tipo;

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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.model.Producto
 * 
 * 
 */
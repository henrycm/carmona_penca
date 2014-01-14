package co.com.multinivel.helper;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.model.Producto;

public class ProductoHelper {
	public static Producto cargarEntidad(HttpServletRequest request) {
		Producto producto = new Producto();
		producto.setCodigo(request.getParameter("codigo"));
		if ((request.getParameter("precioAfiliado") == null)
				|| ("".equals(request.getParameter("precioAfiliado")))) {
			producto.setPrecioAfiliado(new BigDecimal(0));
		} else {
			producto.setPrecioAfiliado(new BigDecimal(Integer.parseInt(request
					.getParameter("precioAfiliado"))));
		}
		producto.setNombreProducto(request.getParameter("nombre"));
		producto.setTipo(request.getParameter("tipo"));

		return producto;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.helper.ProductoHelper
 */
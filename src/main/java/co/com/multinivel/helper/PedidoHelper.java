package co.com.multinivel.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.model.DetallePedido;
import co.com.multinivel.model.Pedido;

public class PedidoHelper {
	public static Pedido cargarEntidadPreliminar(HttpServletRequest request) throws Exception {
		Pedido pedido = new Pedido();
		try {
			String[] productos = request.getParameterValues("producto");
			Enumeration eForma = null;

			String usuario = request.getParameter("distribuidor");
			if (UsuarioHelper.getRol() == '2') {
				usuario = UsuarioHelper.getUsuario();
			}
			pedido.setAfiliado(usuario);
			pedido.setFecha(new Date());
			long totalProducto = 0L;
			long totalPedido = 0L;

			HashSet<DetallePedido> pedidos = new HashSet();
			for (int i = 0; i < productos.length; i++) {
				eForma = request.getParameterNames();
				while (eForma.hasMoreElements()) {
					String nombreParametro = (String) eForma.nextElement();
					if (nombreParametro.indexOf("cantidad_" + productos[i] + "_") != -1) {
						if (request.getParameter(nombreParametro) != null) {
							if (!"".equals(request.getParameter(nombreParametro))) {
								String[] arrayDatos = nombreParametro.split("_");

								String codigoProducto = productos[i];
								int valorProducto = Integer.parseInt(arrayDatos[2]);

								int cantidad = Integer.parseInt(request
										.getParameter(nombreParametro));
								BigDecimal valor = new BigDecimal(arrayDatos[2]);

								String nombreProducto = arrayDatos[3];
								totalProducto = valorProducto * cantidad;
								totalPedido += totalProducto;
								DetallePedido dp = new DetallePedido();

								dp.setCantidad(cantidad);
								dp.setTotalProducto(new BigDecimal(totalProducto));
								dp.setValorUnitario(valor);
								dp.setCodigoProducto(codigoProducto);
								dp.setNombreProducto(nombreProducto);

								pedidos.add(dp);
							}
						}
					}
				}
			}
			pedido.setTDetPedidos(pedidos);
			pedido.setTotalPedido(new BigDecimal(totalPedido));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pedido;
	}

	public static Pedido cargarEntidad(HttpServletRequest request) throws Exception {
		Pedido pedido = new Pedido();
		try {
			String[] productos = request.getParameterValues("codigoProducto");
			String[] valorUnitario = request.getParameterValues("valorUnitario");
			String[] cantidades = request.getParameterValues("cantidad");
			HashSet<DetallePedido> pedidos = new HashSet();

			String usuario = request.getParameter("cedula");
			if (UsuarioHelper.getRol() == '2') {
				usuario = UsuarioHelper.getUsuario();
			}
			pedido.setDistribuidor(usuario);
			pedido.setAfiliado(request.getParameter("cedula"));
			pedido.setFecha(new Date());
			pedido.setTransporte(new BigDecimal(request.getParameter("transporte")));
			long totalProducto = 0L;
			long totalPedido = 0L;
			totalPedido = Integer.parseInt(request.getParameter("totalPedido"));
			pedido.setTotalPedido(new BigDecimal(totalPedido));
			for (int j = 0; j < productos.length; j++) {
				String codigoProducto = productos[j];
				int valorProducto = Integer.parseInt(valorUnitario[j]);
				BigDecimal valor = new BigDecimal(valorProducto);
				int cantidad = Integer.parseInt(cantidades[j]);
				totalProducto = valorProducto * cantidad;

				DetallePedido dp = new DetallePedido();

				dp.setCantidad(cantidad);
				dp.setCodigoProducto(codigoProducto);
				dp.setTotalProducto(new BigDecimal(totalProducto));
				dp.setValorUnitario(valor);
				dp.setPedido(pedido);
				pedidos.add(dp);
			}
			pedido.setTDetPedidos(pedidos);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pedido;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.helper.PedidoHelper
 */
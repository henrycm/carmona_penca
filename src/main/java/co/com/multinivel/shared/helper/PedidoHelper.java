package co.com.multinivel.shared.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.backend.model.DetallePedido;
import co.com.multinivel.backend.model.Pedido;

public class PedidoHelper {
	public static Pedido cargarEntidadPreliminar(HttpServletRequest request) throws Exception {
		Pedido pedido = new Pedido();
		try {
			String[] productos = request.getParameterValues("producto");
			Enumeration<?> eForma = null;

			String usuario = request.getParameter("distribuidor");
			if (UsuarioHelper.getRol() == '2') {
				usuario = UsuarioHelper.getUsuario();
			}
			pedido.setAfiliado(usuario);
			pedido.setFecha(new Date());
			long totalProducto = 0L;
			long totalPedido = 0L;

			HashSet<DetallePedido> pedidos = new HashSet<DetallePedido>();
			for (int i = 0; i < productos.length; i++) {
				eForma = request.getParameterNames();
				while (eForma.hasMoreElements()) {
					String nombreParametro = (String) eForma.nextElement();
					if (nombreParametro.indexOf("cantidad_" + productos[i] + "_") != -1) {
						if (request.getParameter(nombreParametro) != null) {
							if (!"".equals(request.getParameter(nombreParametro))) {
								String[] arrayDatos = nombreParametro.split("_");

								String codigoProducto = productos[i];
								Double valorProducto = Double.parseDouble(arrayDatos[2]);

								BigDecimal valorAfiliado = new BigDecimal(arrayDatos[4]);

								int cantidad = Integer.parseInt(request.getParameter(nombreParametro));
								BigDecimal valor = new BigDecimal(arrayDatos[2]);

								String nombreProducto = arrayDatos[3];
								totalProducto = valorProducto.intValue() * cantidad;
								totalPedido += totalProducto;
								DetallePedido dp = new DetallePedido();

								dp.setCantidad(cantidad);
								dp.setTotalProducto(new BigDecimal(totalProducto));
								dp.setValorUnitario(valor);
								dp.setCodigoProducto(codigoProducto);
								dp.setNombreProducto(nombreProducto);
								dp.setValorUnitarioAfiliado(valorAfiliado);

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
			String[] cantidades = request.getParameterValues("cantidad");
			String[] productos = request.getParameterValues("codigoProducto");
			String[] valorUnitario = request.getParameterValues("valorUnitario");
			String[] valorUnitarioAfiliado = request.getParameterValues("valorUnitarioAfiliado");

			HashSet<DetallePedido> pedidos = new HashSet<DetallePedido>();

			String usuario = request.getParameter("cedula");
			if (UsuarioHelper.getRol() == '2') {
				usuario = UsuarioHelper.getUsuario();
			}
			pedido.setDistribuidor(usuario);
			pedido.setAfiliado(request.getParameter("cedula"));
			pedido.setFecha(new Date());
			pedido.setTransporte(new BigDecimal(request.getParameter("transporte")));
			long totalProductoAfiliado = 0L;
			long totalProducto = 0L;
			long totalPedido = 0L;
			totalPedido = Integer.parseInt(request.getParameter("totalPedido"));
			pedido.setTotalPedido(new BigDecimal(totalPedido));
			for (int j = 0; j < productos.length; j++) {
				String codigoProducto = productos[j];
				Double valorProducto = Double.parseDouble(valorUnitario[j]);
				Double valorProductoAfiliado = Double.parseDouble(valorUnitarioAfiliado[j]);
				BigDecimal valor = new BigDecimal(valorProducto);
				int cantidad = Integer.parseInt(cantidades[j]);
				totalProducto = valorProducto.intValue() * cantidad;
				totalProductoAfiliado = valorProductoAfiliado.intValue() * cantidad;

				DetallePedido dp = new DetallePedido();

				dp.setCantidad(cantidad);
				dp.setCodigoProducto(codigoProducto);
				dp.setTotalProducto(new BigDecimal(totalProducto));
				dp.setTotalProductoAfiliado(new BigDecimal(totalProductoAfiliado));
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
 * Qualified Name: co.com.multinivel.shared.helper.PedidoHelper
 */
package co.com.multinivel.shared.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.DetConsumo;

public class ConsumoHelper {
	public static Consumo cargarEntidadPreliminar(HttpServletRequest request) throws Exception {
		Consumo pedido = new Consumo();
		try {
			String[] productos = request.getParameterValues("producto");
			String usuario = UsuarioHelper.getUsuario();
			pedido.setAfiliado(usuario);
			pedido.setFecha(new Date());
			long totalProducto = 0L;
			long totalConsumo = 0L;

			List<DetConsumo> pedidos = new ArrayList<DetConsumo>();
			for (int i = 0; i < productos.length; i++) {
				String[] arrayDatos = request.getParameter("datos_" + productos[i]).split("_");
				String codigoProducto = productos[i];
				Double valorProducto = Double.parseDouble(arrayDatos[0]);

				int cantidad = Integer.parseInt(request.getParameter("cantidad_" + productos[i]));
				BigDecimal valor = new BigDecimal(valorProducto);

				String nombreProducto = arrayDatos[1];
				totalProducto = valorProducto.intValue() * cantidad;
				totalConsumo += totalProducto;

				DetConsumo dp = new DetConsumo();
				dp.setCantidad(cantidad);
				dp.setTotalProducto(new BigDecimal(totalProducto));
				dp.setValorUnitario(valor);
				dp.setNombreProducto(nombreProducto);
				dp.setCodigoProducto(codigoProducto);

				pedidos.add(dp);
			}
			pedido.setTDetConsumos(pedidos);
			pedido.setTotalpedido(new BigDecimal(totalConsumo));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pedido;
	}

	@SuppressWarnings("deprecation")
	public static Consumo cargarEntidad(HttpServletRequest request) throws Exception {
		Consumo pedido = new Consumo();
		try {
			String[] productos = request.getParameterValues("codigoProducto");
			String[] valorUnitario = request.getParameterValues("valorUnitario");
			String[] cantidades = request.getParameterValues("cantidad");
			List<DetConsumo> pedidos = new ArrayList<DetConsumo>();

			String usuario = UsuarioHelper.getUsuario();
			if (UsuarioHelper.getRol() == '1') {
				usuario = request.getParameter("distribuidor");
			}
			pedido.setDistribuidor(usuario);
			pedido.setAfiliado(request.getParameter("cedula"));
			pedido.setFecha(new Date(request.getParameter("fechaConsumo")));
			long totalProducto = 0L;
			long totalConsumo = 0L;
			totalConsumo = Integer.parseInt(request.getParameter("totalPedido"));
			pedido.setTotalpedido(new BigDecimal(totalConsumo));
			for (int j = 0; j < productos.length; j++) {
				String codigoProducto = productos[j];
				Double valorProducto = Double.parseDouble(valorUnitario[j]);
				BigDecimal valor = new BigDecimal(valorProducto);
				int cantidad = Integer.parseInt(cantidades[j]);
				totalProducto = valorProducto.intValue() * cantidad;

				DetConsumo dp = new DetConsumo();

				dp.setCantidad(cantidad);
				dp.setCodigoProducto(codigoProducto);
				dp.setTotalProducto(new BigDecimal(totalProducto));
				dp.setValorUnitario(valor);
				dp.setConsumo(pedido);
				pedidos.add(dp);
			}
			pedido.setTDetConsumos(pedidos);
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
 * Qualified Name: co.com.multinivel.shared.helper.ConsumoHelper
 */
package co.com.multinivel.consumo;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.ConsumoHelper;
import co.com.multinivel.model.Consumo;
import co.com.multinivel.model.Pedido;
import co.com.multinivel.service.ConsumoService;
import co.com.multinivel.service.PedidoService;
import co.com.multinivel.util.RecursosEnum;

public class RealizarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ConsumoService consumoService;
	@EJB
	private PedidoService pedidoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			Consumo pedido = ConsumoHelper.cargarEntidad(request);

			recurso = RecursosEnum.FW_INGRESO_CONSUMO_EXITO.getRecurso();
			switch (accion) {
			case 'I':
				if (!this.consumoService.ingresar(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					request.setAttribute("codigoConsumo",
							Integer.valueOf(this.consumoService.ultimoConsumo(pedido)));
				}
				break;
			case 'A':
				int saldoAfiliado = this.consumoService.consultarSaldoPorPeriodoDeAfiliados(pedido)
						.intValue();

				Pedido pedidoDistribuidor = new Pedido();
				pedidoDistribuidor.setDistribuidor(pedido.getDistribuidor());
				pedidoDistribuidor.setFecha(new Date());
				int saldoDistribuidor = this.pedidoService
						.consultarSaldoPorPeriodoDistribuidor(pedidoDistribuidor) == null ? 0
						: this.pedidoService.consultarSaldoPorPeriodoDistribuidor(
								pedidoDistribuidor).intValue();

				int totalConsumo = pedido.getTotalpedido() == null ? 0 : pedido.getTotalpedido()
						.intValue();
				if (totalConsumo + saldoAfiliado < saldoDistribuidor) {
					if (!this.consumoService.ingresar(pedido)) {
						request.setAttribute("error", "Datos incompletos");
						recurso = RecursosEnum.FW_INGRESO_CONSUMO_ERROR.getRecurso();
					} else {
						request.setAttribute("codigoConsumo",
								Integer.valueOf(this.consumoService.ultimoConsumo(pedido)));
					}
				} else {
					request.setAttribute(
							"error",
							"El saldo del distribuidor es menor al registrado por el afiliado, el distribuidor debe realizar otro pedido.");
					recurso = RecursosEnum.FW_INGRESO_CONSUMO_ERROR.getRecurso();
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_CONSUMO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.consumo.RealizarConsumo
 * 
 * 
 */
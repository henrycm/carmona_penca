package co.com.multinivel.frontend.pedido;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.backend.service.PedidoService;
import co.com.multinivel.shared.helper.PedidoHelper;
import co.com.multinivel.shared.util.CorreoUtil;
import co.com.multinivel.shared.util.RecursosEnum;

public class RealizarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ConsumoService consumoService;
	@Autowired
	private AfiliadoService afiliadoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			Pedido pedido = PedidoHelper.cargarEntidad(request);

			recurso = RecursosEnum.FW_INGRESO_PEDIDO_EXITO.getRecurso();
			switch (accion) {
			case 'I':
				if (!this.pedidoService.ingresarPedido(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					Afiliado distribuidorPedido = this.afiliadoService.consultar(pedido.getAfiliado());
					CorreoUtil.enviarCorreo(
							"ENVIO PEDIDO N." + pedido.getCodigoPedido(),
							"Se realizo el pedido para el distribuidor: " + pedido.getAfiliado() + " - " + distribuidorPedido.getNombre() + " "
									+ distribuidorPedido.getApellido() + " por valor de: " + pedido.getTotalPedido() + " periodo: "
									+ pedido.getFecha());
					request.setAttribute("codigoPedido", Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
				}
				break;
			case 'A':
				Consumo consumo = new Consumo();
				consumo.setAfiliado(pedido.getAfiliado());
				consumo.setDistribuidor(pedido.getDistribuidor());

				if (!this.pedidoService.ingresarPedido(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					Afiliado distribuidorPedido = this.afiliadoService.consultar(pedido.getAfiliado());
					CorreoUtil.enviarCorreo(
							"REGISTRO PEDIDO N." + pedido.getCodigoPedido(),
							"Se registro el consumo para el afiliado: " + pedido.getAfiliado() + " - " + distribuidorPedido.getNombre() + " "
									+ distribuidorPedido.getApellido() + " por valor de: " + pedido.getTotalPedido() + " periodo: "
									+ pedido.getFecha());

					request.setAttribute("codigoPedido", Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
				}

				break;
			case 'Q':
				if (!this.pedidoService.ingresarPedido(pedido)) {
					request.setAttribute("error", "Datos incompletos");
					recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
				} else {
					Afiliado distribuidorPedido = this.afiliadoService.consultar(pedido.getAfiliado());
					CorreoUtil.enviarCorreo(
							"ENVIO PEDIDO N." + pedido.getCodigoPedido(),
							"Se realizo el pedido para el distribuidor: " + pedido.getAfiliado() + " - " + distribuidorPedido.getNombre() + " "
									+ distribuidorPedido.getApellido() + " por valor de: " + pedido.getTotalPedido() + " periodo: "
									+ pedido.getFecha());
					request.setAttribute("codigoPedido", Integer.valueOf(this.pedidoService.ultimoPedido(pedido)));
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_PEDIDO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);

		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.pedido.RealizarPedido
 */
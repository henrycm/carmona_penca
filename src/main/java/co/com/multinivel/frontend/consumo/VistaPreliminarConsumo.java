package co.com.multinivel.frontend.consumo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.DetConsumo;
import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.backend.service.ParametroService;
import co.com.multinivel.backend.service.SaldoPedidoDistribuidorService;
import co.com.multinivel.shared.helper.ConsumoHelper;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class VistaPreliminarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ParametroService parametroService;
	@Autowired
	private SaldoPedidoDistribuidorService saldoPedidoDistristribuidorService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Consumo consumo = ConsumoHelper.cargarEntidadPreliminar(request);
			String recurso = RecursosEnum.FW_PRELIMINAR_CONSUMO.getRecurso();

			List<DetConsumo> listaDet = consumo.getTDetConsumos();
			request.setAttribute("listaConsumo", listaDet);
			request.setAttribute("totalConsumo", consumo.getTotalpedido());
			request.setAttribute("ciudad", request.getParameter("ciudad"));
			request.setAttribute("telefono", request.getParameter("telefono"));
			request.setAttribute("nombre", request.getParameter("nombre"));
			if ((request.getParameter("documento") == null) || ("".equals(request.getParameter("documento")))) {
				request.setAttribute("cedula", UsuarioHelper.getUsuario());
			} else {
				request.setAttribute("cedula", request.getParameter("documento"));
			}
			request.setAttribute("accion", request.getParameter("accion"));
			request.setAttribute("distribuidor", request.getParameter("distribuidor"));
			Pedido Objpedido = new Pedido();
			if (UsuarioHelper.getRol() == '2') {
				Objpedido.setDistribuidor(UsuarioHelper.getUsuario());
			} else {
				Objpedido.setDistribuidor(request.getParameter("distribuidor"));
			}
			SaldoPedidoDistribuidor saldoDistribuidor = this.saldoPedidoDistristribuidorService.consultarSaldoDistribuidor(Objpedido
					.getDistribuidor());
			if (saldoDistribuidor != null) {
				Double descontarSaldoDistribuidor = Double.valueOf(saldoDistribuidor.getSaldo() - consumo.getTotalpedido().doubleValue());
				Double descontarSaldoAbonadoDistribuidor = Double.valueOf(saldoDistribuidor.getSaldoAbonado()
						- consumo.getTotalpedido().doubleValue());
				if (descontarSaldoAbonadoDistribuidor.doubleValue() >= 0.0D) {
					request.setAttribute("saldoAbonadoDistribuidor", descontarSaldoAbonadoDistribuidor);
				} else {
					request.setAttribute("mensajeSaldoAbonadoDistribuidor", "true");
				}
				if (descontarSaldoDistribuidor.doubleValue() >= 0.0D) {
					request.setAttribute("saldoDistribuidor", descontarSaldoDistribuidor);
				} else {
					request.setAttribute("mensajeSaldoDistribuidor", "true");
				}
			} else {
				request.setAttribute("mensajeSaldoAbonadoDistribuidor", "true");
				request.setAttribute("mensajeSaldoDistribuidor", "true");
			}
			request.setAttribute("transporte", "0");
			request.setAttribute("totalConsumoConTransporte", consumo.getTotalpedido().intValue());

			request.setAttribute("totalConsumo", consumo.getTotalpedido());

			request.setAttribute("fechaActual", request.getParameter("fechaConsumo"));

			RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.consumo.VistaPreliminarConsumo
 */
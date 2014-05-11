package co.com.multinivel.frontend.consumo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.backend.service.SaldoPedidoDistristribuidorService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class EliminarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ConsumoService consumoService;
	@Autowired
	private SaldoPedidoDistristribuidorService saldoPedidoDistristribuidorService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = RecursosEnum.FW_ELIMINACION_CONSUMO_EXITO.getRecurso();
		try {
			Consumo consumo = new Consumo();
			if (UsuarioHelper.getRol() == '2') {
				consumo.setDistribuidor(UsuarioHelper.getUsuario());
			} else {
				consumo.setDistribuidor(request.getParameter("distribuidor"));
			}
			int codigoConsumo = Integer.parseInt(request.getParameter("pedido"));

			consumo.setCodigoConsumo(codigoConsumo);
			consumo = this.consumoService.consultarConsumo(consumo);

			if (consumo != null) {
				boolean transaccion = this.consumoService.eliminar(consumo);
				if (transaccion) {
					double saldo;
					double saldoAbonos;
					boolean grabadoExito = Boolean.FALSE;
					SaldoPedidoDistribuidor spd = new SaldoPedidoDistribuidor();
					spd = this.saldoPedidoDistristribuidorService.consultarSaldoDistribuidor(consumo.getDistribuidor());
					if (spd == null) {
						spd = new SaldoPedidoDistribuidor();
						spd.setDistribuidor(consumo.getDistribuidor());
					}
					saldo = spd.getSaldo() + consumo.getTotalpedido().doubleValue();
					saldoAbonos = spd.getSaldoAbonado() + consumo.getTotalpedido().doubleValue();
					spd.setSaldo(saldo);
					spd.setSaldoAbonado(saldoAbonos);

					grabadoExito = this.saldoPedidoDistristribuidorService.guardar(spd);
					if (!grabadoExito) {
						request.setAttribute("error",
								"Se elimino el consumo; pero no se devolvio el valor del abono. Comuníquese con el Administrador.");
					}
				}
			} else {
				request.setAttribute("error", "Borrado no fue finalizado consulte con el administrador del sistema");
				recurso = RecursosEnum.FW_ELIMINACION_CONSUMO_ERROR.getRecurso();
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_ELIMINACION_CONSUMO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.consumo.EliminarConsumo
 */
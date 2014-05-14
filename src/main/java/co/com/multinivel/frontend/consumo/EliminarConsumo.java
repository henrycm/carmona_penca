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
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.backend.service.SaldoPedidoDistribuidorService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class EliminarConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ConsumoService consumoService;
	@Autowired
	private SaldoPedidoDistribuidorService saldoPedidoDistribuidorService;

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
				if (!transaccion) {
					request.setAttribute("error", "Borrado no fue finalizado consulte con el administrador del sistema");
					recurso = RecursosEnum.FW_ELIMINACION_CONSUMO_ERROR.getRecurso();
				}
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
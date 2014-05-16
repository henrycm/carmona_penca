package co.com.multinivel.frontend.consumo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.service.ConsumoService;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteConstanciaConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ConsumoService consumoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HashMap<String, Object> mapParametrosReporte = new HashMap<String, Object>();
			List<Object> listaConsumo = null;
			Consumo pedido = new Consumo();
			pedido.setCodigoConsumo(Integer.parseInt(request.getParameter("codigoConsumo")));
			listaConsumo = this.consumoService.consultar(pedido);
			mapParametrosReporte.put("totalAfiliados", listaConsumo.size());
			if ((listaConsumo != null) && (listaConsumo.size() > 0)) {
				mapParametrosReporte.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));
				mapParametrosReporte.put("codigoPedido", request.getParameter("codigoConsumo"));
				mapParametrosReporte.put("tituloCompania", "ALOE DE COLOMBIA");

				GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "CONSUMO_" + UsuarioHelper.getUsuario() + "_"
						+ request.getParameter("codigoConsumo") + ".pdf", RecursosEnum.FW_JASPER_REPORTE_CONSUMO.getRecurso(), mapParametrosReporte,
						listaConsumo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.consumo.ReporteConstanciaConsumo
 */
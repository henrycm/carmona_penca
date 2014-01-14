package co.com.multinivel.afiliado;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.dto.AfiliadoDTO;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidor;
import co.com.multinivel.model.CantidadAfiliacionesDistribuidorPK;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.BancoService;
import co.com.multinivel.service.CantidadAfiliacionesDistribuidorService;
import co.com.multinivel.service.DepartamentoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private BancoService bancoService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private CantidadAfiliacionesDistribuidorService cantidaAfiliacionesDistribuidorService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String recurso = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);

			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);

			String periodo = cadenaFecha;

			request.setAttribute("periodo", periodo);

			request.setAttribute("tipoAfiliado", UsuarioHelper.getRol());

			List<AfiliadoDTO> listaAfiliado = null;
			switch (accion) {
			case 'I':
				listaAfiliado = this.afiliadoService.buscarDistribuidor(UsuarioHelper.getUsuario(),
						null);
				if ((listaAfiliado != null) && (listaAfiliado.size() > 0)) {
					request.setAttribute("usuario", listaAfiliado.get(0));
				}
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos", this.departamentoService.listar());

				recurso = RecursosEnum.FW_INGRESO_AFILIADO.getRecurso();
				break;
			case 'C':
				recurso = RecursosEnum.FW_ACTUALIZACION_AFILIADO.getRecurso();
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos", this.departamentoService.listar());

				break;
			case 'L':
				recurso = RecursosEnum.FW_LISTAR_AFILIADO.getRecurso();
				break;
			case 'N':
				recurso = RecursosEnum.FW_LISTAR_AFILIADO_POR_NIVEL.getRecurso();
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());

				break;
			case 'D':
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos", this.departamentoService.listar());
				recurso = RecursosEnum.FW_ACTUALIZAR_AFILIADO_DISTRIBUIDOR.getRecurso();

				break;
			case 'E':
				recurso = RecursosEnum.FW_CONSULTAR_AFILIADO.getRecurso();

				break;
			case 'F':
				recurso = RecursosEnum.FW_LISTAR_DISTRIBUIDORES.getRecurso();
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());

				break;
			case 'P':
				recurso = RecursosEnum.FW_LISTAR_AFILIADOSXDISTRIBUIDORES.getRecurso();
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());

				break;
			case 'Z':
				recurso = RecursosEnum.FW_LISTAR_AFILIADOSXDISTRIBUIDORESPERIODO.getRecurso();

				break;
			case 'X':
				recurso = RecursosEnum.FW_BORRAR_AFILIADO.getRecurso();
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos", this.departamentoService.listar());
				break;
			case 'U':
				recurso = RecursosEnum.FW_CAMBIAR_DOCUMENTO_AFILIADO.getRecurso();

				break;
			case 'W':
				recurso = RecursosEnum.FW_VALIDAR_AFILIACIONES_DISTRIBUIDOR.getRecurso();
				int cantidad = request.getParameter("cantidad") == null ? 0 : Integer
						.parseInt(request.getParameter("cantidad"));
				if (cantidad > 0) {
					CantidadAfiliacionesDistribuidor cantidadAfiliacionesDistribuidor = new CantidadAfiliacionesDistribuidor();
					cantidadAfiliacionesDistribuidor.setCantidad(cantidad);
					CantidadAfiliacionesDistribuidorPK cantidadAfiliacionesDistribuidorPK = new CantidadAfiliacionesDistribuidorPK();
					cantidadAfiliacionesDistribuidorPK.setDistribuidor(request
							.getParameter("distribuidor"));
					cantidadAfiliacionesDistribuidorPK.setPeriodo(request.getParameter("periodo"));
					cantidadAfiliacionesDistribuidor.setId(cantidadAfiliacionesDistribuidorPK);
					boolean retorno = this.cantidaAfiliacionesDistribuidorService
							.ingresar(cantidadAfiliacionesDistribuidor);
					if (retorno) {
						request.setAttribute("mensaje",
								"se ingresaron las cantidades para el distribuidor exitosamente");
					} else {
						request.setAttribute("mensaje",
								"no se ingresaron las cantidas porque ya existen unas cantidades ingresadas para este distribuidor");
					}
				}
				request.setAttribute("periodo", periodo);
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
			}
			request.setAttribute("accion", request.getParameter("accion"));
		} catch (MultinivelServiceException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_AFILIADO_ERROR.getRecurso();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

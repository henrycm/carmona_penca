package co.com.multinivel.consumo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.ProductoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexConsumo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	ProductoService productoService;
	@Autowired
	private AfiliadoService afiliadoService;

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
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
		String cadenaFecha = formato.format(fechaActual);
		String periodo = cadenaFecha;
		request.setAttribute("periodo", periodo);
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter(
					"accion").charAt(0);
			request.setAttribute("fechaActual", new Date());
			switch (accion) {
			case 'I':
				recurso = RecursosEnum.FW_INGRESO_CONSUMO.getRecurso();
				request.setAttribute("afiliado",
						this.afiliadoService.consultar(UsuarioHelper.getUsuario()));
				request.setAttribute("listaAlimentos", this.productoService.listar("1"));
				request.setAttribute("listaPiel", this.productoService.listar("2"));
				request.setAttribute("listaCapilar", this.productoService.listar("3"));
				request.setAttribute("listaAseoPersonal", this.productoService.listar("4"));
				request.setAttribute("listaFisioterapia", this.productoService.listar("5"));
				request.setAttribute("listaAseoHogar", this.productoService.listar("6"));
				request.setAttribute("listaVeterinaria", this.productoService.listar("7"));
				request.setAttribute("listaExtractos", this.productoService.listar("8"));

				break;
			case 'A':
				recurso = RecursosEnum.FW_INGRESO_CONSUMO_AFILIADO.getRecurso();

				request.setAttribute("afiliado",
						this.afiliadoService.consultar(request.getParameter("cedula")));
				request.setAttribute("fechaConsumo", request.getParameter("fechaConsumo"));
				request.setAttribute("distribuidor", request.getParameter("distribuidor"));

				request.setAttribute("listaAlimentos", this.productoService.listar("1"));
				request.setAttribute("listaPiel", this.productoService.listar("2"));
				request.setAttribute("listaCapilar", this.productoService.listar("3"));
				request.setAttribute("listaAseoPersonal", this.productoService.listar("4"));
				request.setAttribute("listaFisioterapia", this.productoService.listar("5"));
				request.setAttribute("listaAseoHogar", this.productoService.listar("6"));
				request.setAttribute("listaVeterinaria", this.productoService.listar("7"));
				request.setAttribute("listaExtractos", this.productoService.listar("8"));

				break;
			case 'L':
				recurso = RecursosEnum.FW_VISTA_REPORTES_LISTA_CONSUMOS.getRecurso();
				break;
			case 'Z':
				recurso = RecursosEnum.FW_VISTA_REPORTES_LISTA_CONSUMOS_PRODUCTO.getRecurso();
				break;
			case 'N':
				recurso = RecursosEnum.FW_VISTA_REPORTES_LISTA_CONSUMOS_AFILIADO.getRecurso();
				break;
			case 'B':
				request.setAttribute("distribuidor", request.getParameter("distribuidor"));

				recurso = RecursosEnum.FW_BUSCAR_AFILIADO_CONSUMO.getRecurso();

				break;
			case 'C':
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				recurso = RecursosEnum.FW_LISTAR_CONSUMOS_TOTALES_DISTRIBUIDOR.getRecurso();
				break;
			case 'D':
				recurso = RecursosEnum.FW_LISTAR_CONSUMOS_TOTALES.getRecurso();
				break;
			case 'E':
				request.setAttribute("distribuidor", request.getParameter("distribuidor"));

				recurso = RecursosEnum.FW_LISTAR_CONSUMO_ELIMINAR.getRecurso();
				break;
			case 'X':
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				request.setAttribute("accion", "E");

				recurso = RecursosEnum.FW_LISTAR_DISTRIBUIDOR_ELIMINAR_CONSUMO.getRecurso();

				break;
			case 'Y':
				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				request.setAttribute("accion", "B");

				recurso = RecursosEnum.FW_LISTAR_DISTRIBUIDOR_REGISTRAR_CONSUMO.getRecurso();

				break;
			case 'F':
			case 'G':
			case 'H':
			case 'J':
			case 'K':
			case 'M':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			default:
				recurso = RecursosEnum.FW_CONSULTAR_CONSUMO.getRecurso();
			}
			request.setAttribute("accion", request.getParameter("accion"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.consumo.IndexConsumo
 */
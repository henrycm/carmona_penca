package co.com.multinivel.pedido;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.multinivel.helper.UsuarioHelper;
import co.com.multinivel.service.AfiliadoService;
import co.com.multinivel.service.ProductoService;
import co.com.multinivel.util.RecursosEnum;

public class IndexPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	ProductoService productoService;
	@EJB
	private AfiliadoService afiliadoService;

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
			request.setAttribute("fechaActual", new Date());
			Date fechaActual = new Date();
			SimpleDateFormat formato = new SimpleDateFormat("MM/yyyy");
			String cadenaFecha = formato.format(fechaActual);
			String periodo = cadenaFecha;
			request.setAttribute("periodo", periodo);
			switch (accion) {
			case 'I':
				recurso = RecursosEnum.FW_INGRESO_PEDIDO.getRecurso();
				if (UsuarioHelper.getRol() == '2') {
					request.setAttribute("afiliado",
							this.afiliadoService.consultar(UsuarioHelper.getUsuario()));
				} else {
					request.setAttribute("afiliado",
							this.afiliadoService.consultar(request.getParameter("distribuidor")));
				}
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
				recurso = RecursosEnum.FW_INGRESO_PEDIDO_AFILIADO.getRecurso();

				request.setAttribute("afiliado",
						this.afiliadoService.consultar(request.getParameter("cedula")));

				request.setAttribute("listaAlimentos", this.productoService.listar("1"));
				request.setAttribute("listaPiel", this.productoService.listar("2"));
				request.setAttribute("listaCapilar", this.productoService.listar("3"));
				request.setAttribute("listaAseoPersonal", this.productoService.listar("4"));
				request.setAttribute("listaFisioterapia", this.productoService.listar("5"));
				request.setAttribute("listaAseoHogar", this.productoService.listar("6"));
				request.setAttribute("listaVeterinaria", this.productoService.listar("7"));
				request.setAttribute("listaExtractos", this.productoService.listar("8"));

				break;
			case 'Q':
				recurso = RecursosEnum.FW_NUEVO_INGRESO_PEDIDO.getRecurso();
				if (UsuarioHelper.getRol() == '2') {
					request.setAttribute("afiliado",
							this.afiliadoService.consultar(UsuarioHelper.getUsuario()));
				} else {
					request.setAttribute("afiliado",
							this.afiliadoService.consultar(request.getParameter("distribuidor")));
				}
				request.setAttribute("listaAlimentos", this.productoService.listar("1"));
				request.setAttribute("listaPiel", this.productoService.listar("2"));
				request.setAttribute("listaCapilar", this.productoService.listar("3"));
				request.setAttribute("listaAseoPersonal", this.productoService.listar("4"));
				request.setAttribute("listaFisioterapia", this.productoService.listar("5"));
				request.setAttribute("listaAseoHogar", this.productoService.listar("6"));
				request.setAttribute("listaVeterinaria", this.productoService.listar("7"));
				request.setAttribute("listaExtractos", this.productoService.listar("8"));

				break;
			case 'B':
				recurso = RecursosEnum.FW_BUSCAR_AFILIADO_PEDIDO.getRecurso();
				break;
			case 'L':
				recurso = RecursosEnum.FW_VISTA_REPORTES_LISTA_PEDIDO.getRecurso();
				break;
			case 'E':
				recurso = RecursosEnum.FW_LISTAR_PEDIDO_ELIMINAR.getRecurso();
				break;
			case 'C':
				request.setAttribute("accion", "I");

				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				recurso = RecursosEnum.FW_LISTAR_DISTRIBUIDORES_PEDIDO.getRecurso();
				break;
			case 'S':
				request.setAttribute("accion", "Q");

				request.setAttribute("listaDistribuidores",
						this.afiliadoService.listarDistribuidores());
				recurso = RecursosEnum.FW_LISTAR_DISTRIBUIDORES_PEDIDO_NUEVO.getRecurso();
				break;
			case 'D':
			case 'F':
			case 'G':
			case 'H':
			case 'J':
			case 'K':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'R':
			default:
				recurso = RecursosEnum.FW_CONSULTAR_PEDIDO.getRecurso();
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
 * Qualified Name: co.com.multinivel.pedido.IndexPedido
 * 
 * 
 */
package co.com.multinivel.frontend.afiliado;

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

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.GroupAuthority;
import co.com.multinivel.backend.model.GroupMember;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.BancoService;
import co.com.multinivel.backend.service.CantidadAfiliacionesDistribuidorService;
import co.com.multinivel.backend.service.DepartamentoService;
import co.com.multinivel.backend.service.RedService;
import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.backend.service.UsuarioService;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.helper.AfiliadoHelper;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.CorreoUtil;
import co.com.multinivel.shared.util.RecursosEnum;

public class AfiliadoFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private BancoService bancoService;
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RolService rolService;
	@Autowired
	private RedService redService;
	@Autowired
	private CantidadAfiliacionesDistribuidorService cantidaAfiliacionesDistribuidorService;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String recurso = null;
		Afiliado afiliado = null;

		List<Afiliado> lista = null;
		List<AfiliadoDTO> listaPorNivel = null;
		Afiliado afiliadoConsulta = null;
		GroupAuthority rol = null;
		GroupMember rolPorUsuario = null;
		try {
			char accion = request.getParameter("accion") == null ? '*'
					: request.getParameter("accion").charAt(0);

			recurso = RecursosEnum.FW_INDEX_AFILIADO.getRecurso();
			switch (accion) {
			case 'E':
				recurso = RecursosEnum.FW_CONSULTAR_AFILIADO.getRecurso();
				String nomFiltro = request.getParameter("nomFiltro");
				String filtro = request.getParameter("filtro");
				if (filtro != null && nomFiltro != null) {
					lista = this.afiliadoService.buscar(nomFiltro, filtro);
					request.setAttribute("listaAfiliados", lista);
					System.out.println("LISTA>>>>>>" + lista.size());
				}
				else
				{
					afiliadoConsulta = this.afiliadoService.consultar(request
							.getParameter("codigoEmpresario"));
					request.setAttribute("afiliado", afiliadoConsulta);
					request.setAttribute("banco", this.bancoService
							.consultar(afiliadoConsulta.getBanco()));
					if ((afiliadoConsulta != null)
							&& (afiliadoConsulta.getCedula() != null)) {
						request.setAttribute("patrocinador", this.afiliadoService
								.consultar(afiliadoConsulta.getCedulaPapa()));
						List<AfiliadoDTO> listaAfiliado = this.afiliadoService
								.buscarDistribuidor(
										afiliadoConsulta.getCedulaDistribuidor(),
										null);
						if ((listaAfiliado != null) && (listaAfiliado.size() > 0)) {
							request.setAttribute("distribuidor",
									listaAfiliado.get(0));
						}
					} else {
						request.setAttribute("noExisteAfiliado",
								Boolean.valueOf(true));
					}
				}
				request.setAttribute("accion", "E");

				break;
			case 'D':
				if ("A".equals(request.getParameter("actualizar"))) {
					afiliadoConsulta = new Afiliado();
					afiliadoConsulta.setCedula(request
							.getParameter("codigoEmpresario"));
					this.afiliadoService
							.actualizarAfiliadoADistribuidor(afiliadoConsulta);
					User usuario = this.usuarioService.consultar(request
							.getParameter("codigoEmpresario"));
					rol = this.rolService.consultar("2");
					if (rol != null) {
						rolPorUsuario = new GroupMember();
						rolPorUsuario.setUser(usuario);
						rolPorUsuario.setGroupAuthority(rol);
						this.rolService.actualizarRolUsuario(rolPorUsuario);
					}
					recurso = RecursosEnum.FW_ACTUALIZAR_AFILIADO_DISTRIBUIDOR_EXITO
							.getRecurso();
				} else {
					afiliadoConsulta = this.afiliadoService.consultar(request
							.getParameter("codigoEmpresario")
							+ "-"
							+ request.getParameter("letra"));
					recurso = RecursosEnum.FW_ACTUALIZAR_AFILIADO_DISTRIBUIDOR
							.getRecurso();
					request.setAttribute("afiliado", afiliadoConsulta);
					request.setAttribute("banco", this.bancoService
							.consultar(afiliadoConsulta.getBanco()));
					if ((afiliadoConsulta != null)
							&& (afiliadoConsulta.getCedula() != null)) {
						request.setAttribute("patrocinador",
								this.afiliadoService.consultar(afiliadoConsulta
										.getCedulaPapa()));
						List<AfiliadoDTO> listaAfiliado = this.afiliadoService
								.buscarDistribuidor(afiliadoConsulta
										.getCedulaDistribuidor(), null);
						if ((listaAfiliado != null)
								&& (listaAfiliado.size() > 0)) {
							request.setAttribute("distribuidor",
									listaAfiliado.get(0));
						}
					} else {
						request.setAttribute("noExisteAfiliado",
								Boolean.valueOf(true));
					}
				}
				request.setAttribute("accion", "D");
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos",
						this.departamentoService.listar());
				break;
			case 'A':
				afiliado = AfiliadoHelper.cargarEntidad(request);
				this.afiliadoService.actualizar(afiliado);
				request.setAttribute("afiliado", afiliado);
				recurso = RecursosEnum.FW_INGRESO_AFILIADO_EXITO.getRecurso();
				request.setAttribute("actualizo", Boolean.valueOf(true));

				break;
			case 'I':
				afiliado = AfiliadoHelper.cargarEntidad(request);
				afiliado.setIdAfiliacionDistribuidor(this.afiliadoService
						.consultarIdDistribuidor(afiliado
								.getCedulaDistribuidor()));
				this.afiliadoService.ingresar(afiliado);
				User usuario = UsuarioHelper.cargarEntidad(afiliado, request);
				this.usuarioService.ingresar(usuario);
				CorreoUtil.enviarCorreo(
						"INSCRIPCION AFILIADO",
						"Se ha realizado la inscripción de   un nuevo afiliado:"
								+

								afiliado.getCedula() + " - "
								+ afiliado.getNombre() + " "
								+ afiliado.getApellido()
								+ " para el patrocinador:"
								+ afiliado.getCedulaPapa());

				rol = this.rolService.consultar(request.getParameter("rol"));
				if (rol != null) {
					rolPorUsuario = new GroupMember();
					rolPorUsuario.setUser(usuario);
					rolPorUsuario.setGroupAuthority(rol);
					this.rolService.guardarRolUsuario(rolPorUsuario);
				}
				recurso = RecursosEnum.FW_INGRESO_AFILIADO_EXITO.getRecurso();
				request.setAttribute("afiliado", afiliado);
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'C':
				afiliadoConsulta = this.afiliadoService.consultar(request
						.getParameter("codigoEmpresario")
						+ "-"
						+ request.getParameter("letra"));
				recurso = RecursosEnum.FW_ACTUALIZACION_AFILIADO.getRecurso();
				request.setAttribute("distribuidores", afiliadoService.listarDistribuidores());
				request.setAttribute("afiliado", afiliadoConsulta);
				request.setAttribute("banco", this.bancoService
						.consultar(afiliadoConsulta.getBanco()));
				if ((afiliadoConsulta != null)
						&& (afiliadoConsulta.getCedula() != null)) {
					request.setAttribute("patrocinador", this.afiliadoService
							.consultar(afiliadoConsulta.getCedulaPapa()));
					List<AfiliadoDTO> listaAfiliado = this.afiliadoService
							.buscarDistribuidor(
									afiliadoConsulta.getCedulaDistribuidor(),
									null);
					if ((listaAfiliado != null) && (listaAfiliado.size() > 0)) {
						request.setAttribute("distribuidor",
								listaAfiliado.get(0));
					}
					request.setAttribute("listaRedes", this.redService.listar());

					request.setAttribute("accion", "A");
				} else {
					request.setAttribute("accion", "C");
				}
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos",
						this.departamentoService.listar());
				request.setAttribute("ingreso", Boolean.valueOf(true));

				break;
			case 'B':
				afiliado = AfiliadoHelper.cargarEntidad(request);
				this.afiliadoService.borrar(afiliado);
				request.setAttribute("retiro", Boolean.valueOf(true));

				break;
			case 'L':
				lista = this.afiliadoService.listar();
				request.setAttribute("listaAfiliados", lista);
				break;
			case 'N':
				request.setAttribute("listaAfiliados", listaPorNivel);
				request.setAttribute("listaRedes", this.redService.listar());

				recurso = RecursosEnum.FW_LISTAR_AFILIADO_POR_NIVEL
						.getRecurso();

				break;
			case 'U':
				String documentoActual = request
						.getParameter("codigoEmpresario");
				String documentoNuevo = request
						.getParameter("nuevoCodigoEmpresario");

				boolean retorno = this.afiliadoService.cambiarDocumento(
						documentoActual, documentoNuevo);
				if (retorno) {
					request.setAttribute("mensaje",
							"el cambio de documento se realizo con exito");
				} else {
					request.setAttribute("mensaje",
							"el cambio de documento no se realizo por favor verifique con el administrador");
				}
				recurso = RecursosEnum.FW_CAMBIAR_DOCUMENTO_AFILIADO
						.getRecurso();

				break;
			case 'X':
				recurso = RecursosEnum.FW_BORRAR_AFILIADO_EXITO.getRecurso();
				if (request.getParameter("codigoEmpresario") != null) {
					if (request.getParameter("borrar") != null) {
						afiliadoConsulta = new Afiliado();
						afiliadoConsulta.setCedula(request
								.getParameter("codigoEmpresario"));
						if (this.afiliadoService.borrar(afiliadoConsulta)) {
							request.setAttribute("mensaje",
									"el afiliado ha sido eliminado con exito");
							break;
						}
						request.setAttribute(
								"mensaje",
								"el afiliado tiene asociados personas en su red. Por favor borre sus asociados o comuniquese con el administrador");
						break;
					}
				}
				afiliadoConsulta = this.afiliadoService.consultar(request
						.getParameter("codigoEmpresario")
						+ "-"
						+ request.getParameter("letra"));
				recurso = RecursosEnum.FW_BORRAR_AFILIADO.getRecurso();
				request.setAttribute("afiliado", afiliadoConsulta);
				request.setAttribute("banco", this.bancoService
						.consultar(afiliadoConsulta.getBanco()));
				if ((afiliadoConsulta != null)
						&& (afiliadoConsulta.getCedula() != null)) {
					request.setAttribute("patrocinador", this.afiliadoService
							.consultar(afiliadoConsulta.getCedulaPapa()));
					List<AfiliadoDTO> listaAfiliado = this.afiliadoService
							.buscarDistribuidor(
									afiliadoConsulta.getCedulaDistribuidor(),
									null);
					if ((listaAfiliado != null) && (listaAfiliado.size() > 0)) {
						request.setAttribute("distribuidor",
								listaAfiliado.get(0));
					}
					request.setAttribute("listaRedes", this.redService.listar());

					request.setAttribute("accion", "X");
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_AFILIADO_ERROR.getRecurso();
		}
		rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}

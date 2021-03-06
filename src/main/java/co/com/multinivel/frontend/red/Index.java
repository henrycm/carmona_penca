package co.com.multinivel.frontend.red;

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
import co.com.multinivel.backend.model.Red;
import co.com.multinivel.backend.model.User;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.BancoService;
import co.com.multinivel.backend.service.DepartamentoService;
import co.com.multinivel.backend.service.RedService;
import co.com.multinivel.backend.service.RolService;
import co.com.multinivel.backend.service.UsuarioService;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.helper.AfiliadoHelper;
import co.com.multinivel.shared.helper.RedHelper;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

public class Index extends HttpServlet {
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

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recurso = null;
		Afiliado afiliado = null;
		GroupAuthority rol = null;
		GroupMember rolPorUsuario = null;
		Red red = null;
		try {
			char accion = request.getParameter("accion") == null ? '*' : request.getParameter("accion").charAt(0);
			List<AfiliadoDTO> listaAfiliado = null;
			switch (accion) {
			case 'V':
				recurso = RecursosEnum.FW_INGRESO_RED.getRecurso();
				request.setAttribute("listaBancos", this.bancoService.listar());
				request.setAttribute("listaDepartamentos", this.departamentoService.listar());
				listaAfiliado = this.afiliadoService.buscarDistribuidor(UsuarioHelper.getUsuario(), null);
				if ((listaAfiliado != null) && (listaAfiliado.size() > 0)) {
					request.setAttribute("usuario", listaAfiliado.get(0));
				}
				request.setAttribute("accion", "I");
				break;
			case 'I':
				red = RedHelper.cargarEntidad(request);
				afiliado = AfiliadoHelper.cargarEntidad(request);
				if (this.redService.ingresar(red)) {
					this.afiliadoService.ingresar(afiliado);
					User usuario = UsuarioHelper.cargarEntidad(afiliado);
					this.usuarioService.ingresar(usuario);
					rol = this.rolService.consultar(request.getParameter("rol"));
					if (rol != null) {
						rolPorUsuario = new GroupMember();
						rolPorUsuario.setUser(usuario);
						rolPorUsuario.setGroupAuthority(rol);
						this.rolService.guardarRolUsuario(rolPorUsuario);
					}
					recurso = RecursosEnum.FW_INGRESO_RED_EXITO.getRecurso();

					request.setAttribute("ingreso", Boolean.valueOf(true));
				} else {
					recurso = RecursosEnum.FW_INGRESO_RED_ERROR.getRecurso();
				}
				request.setAttribute("afiliado", afiliado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			recurso = RecursosEnum.FW_INGRESO_RED_ERROR.getRecurso();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(recurso);
		rd.forward(request, response);
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.frontend.red.Index
 */
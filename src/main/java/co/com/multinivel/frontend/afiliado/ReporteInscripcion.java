package co.com.multinivel.frontend.afiliado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import co.com.multinivel.backend.model.Banco;
import co.com.multinivel.backend.model.Departamento;
import co.com.multinivel.backend.model.Red;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.BancoService;
import co.com.multinivel.backend.service.DepartamentoService;
import co.com.multinivel.backend.service.RedService;
import co.com.multinivel.shared.util.GenerarReporte;
import co.com.multinivel.shared.util.RecursosEnum;
import co.com.multinivel.shared.util.RutasUtil;

public class ReporteInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private RedService redService;
	@Autowired
	private BancoService bancoService;
	@Autowired
	private DepartamentoService departamentoService;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String documento = request.getParameter("documento") == null ? "0" : request.getParameter("documento");

			map.put("titulo", "Inscripcion Afiliado");
			map.put("rutaImagenes", RutasUtil.getRutaImagenes(getServletContext()));

			List<Object> lista = new ArrayList<Object>();

			Afiliado afiliado = this.afiliadoService.consultar(documento);
			lista.add(afiliado);
			if (afiliado != null) {
				map.put("cuidad", afiliado.getCiudad());
				map.put("nombre", afiliado.getNombre());
				map.put("apellido", afiliado.getApellido());
				map.put("codigoEmpresario", afiliado.getCedula());
				map.put("barrio", afiliado.getBarrio());
				map.put("fechaNacimiento", afiliado.getFechaNacimiento());
				map.put("tipoDocumento", afiliado.getTipoDocumento());
				map.put("numeroIdentificacion", afiliado.getCedula());
				map.put("ciudadIdentificacion", afiliado.getCiudad());
				map.put("ciudadNacimiento", afiliado.getCiudad());
				Departamento departamentoNacimiento = this.departamentoService.consultar(afiliado.getDepartamento());
				map.put("departamentoNacimiento", departamentoNacimiento.getDescripcion());
				map.put("profesion", afiliado.getOcupacion());
				map.put("estadoCivil", afiliado.getEstadoCivil());
				map.put("nombreConyugue", afiliado.getNombreConyugue());
				map.put("documentoConyugue", afiliado.getDocumentoConyugue());
				map.put("direccion", afiliado.getDireccion());
				map.put("ciudadResidencia", afiliado.getCiudadResidencia());
				Departamento departamento = this.departamentoService.consultar(afiliado.getDepartamentoResidencia());
				map.put("departamentoResidencia", departamento.getDescripcion());
				map.put("telefono", afiliado.getTelefono());
				map.put("celular", afiliado.getCelular());
				map.put("email", afiliado.getEmail());
				Red red = this.redService.consultar(afiliado.getRed());
				map.put("sedeInscripcion", red.getNombre());
				map.put("numeroCuenta", afiliado.getCuentaNro());
				map.put("consecutivo", Integer.valueOf(afiliado.getIdAfiliacionDistribuidor()));

				Banco banco = this.bancoService.consultar(afiliado.getBanco());

				map.put("entidadBancaria", banco.getDescripcion());
				map.put("tipoCuenta", afiliado.getTipoCuenta());
				map.put("nombreTitular", afiliado.getNombreTitularCta());
				map.put("documentoTitular", afiliado.getTitularCuenta());
				Afiliado datosPadre = this.afiliadoService.consultar(afiliado.getCedulaPapa());
				map.put("nombrePatrocinador", datosPadre.getNombre() + " " + datosPadre.getApellido());
				map.put("numeroEmpresario", afiliado.getCedulaPapa());

				GenerarReporte.exportarPDF(request, response, getServletConfig().getServletContext(), "Reporte_ConstanciaAfiliacion.pdf",
						RecursosEnum.FW_JASPER_AFLIADO.getRecurso(), map, lista);
			} else {
				request.setAttribute("error", "No se encuentra el Afiliado");
				rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			rd = getServletContext().getRequestDispatcher(RecursosEnum.FW_ERROR.getRecurso());
			rd.forward(request, response);
		}
	}
}

package co.com.multinivel.shared.helper;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.shared.dto.Nodo;
import co.com.multinivel.shared.util.FechasUtil;

public class AfiliadoHelper {
	public static Afiliado cargarEntidad(HttpServletRequest request) {
		Afiliado afiliado = new Afiliado();
		if ("I".equals(request.getParameter("accion"))) {
			afiliado.setCedula(request.getParameter("codigoEmpresario") + "-"
					+ request.getParameter("letra"));
		} else {
			afiliado.setCedula(request.getParameter("codigoEmpresario"));
		}
		afiliado.setNombre(request.getParameter("nombre").toUpperCase());
		afiliado.setApellido(request.getParameter("apellido").toUpperCase());

		String tmp = request.getParameter("fechaNacimiento");
		afiliado.setFechaNacimiento(FechasUtil.parse(tmp));
		afiliado.setActivo("si");
		afiliado.setTipoDocumento(request.getParameter("tipoDocumento"));
		afiliado.setCiudad(request.getParameter("ciudadNacimiento").toUpperCase());
		afiliado.setDepartamento(request.getParameter("departamento"));
		afiliado.setOcupacion(request.getParameter("profesion") == null ? "" : request
				.getParameter("profesion").toUpperCase());
		afiliado.setEstadoCivil(request.getParameter("estadoCivil"));
		afiliado.setNombreConyugue(request.getParameter("nombreConyugue") == null ? "" : request
				.getParameter("nombreConyugue").toUpperCase());
		afiliado.setDocumentoConyugue(request.getParameter("documentoConyugue"));
		afiliado.setDireccion(request.getParameter("direccion") == null ? "" : request
				.getParameter("direccion").toUpperCase());
		afiliado.setBarrio(request.getParameter("barrio") == null ? "" : request.getParameter(
				"barrio").toUpperCase());
		afiliado.setCiudadResidencia(request.getParameter("ciudadResidencia") == null ? ""
				: request.getParameter("ciudadResidencia").toUpperCase());
		afiliado.setDepartamentoResidencia(request.getParameter("departamentoResidencia"));

		afiliado.setTelefono(request.getParameter("telefono"));

		afiliado.setCelular(request.getParameter("celular"));
		afiliado.setEmail(request.getParameter("email"));
		afiliado.setRed(request.getParameter("red"));

		afiliado.setCuentaNro(request.getParameter("numeroCuenta"));
		afiliado.setBanco(request.getParameter("entidadBancaria"));
		afiliado.setTipoCuenta(request.getParameter("tipoCuenta"));
		if (request.getParameter("documentoTitular") != null) {
			afiliado.setTitularCuenta(request.getParameter("documentoTitular"));
			afiliado.setNombreTitularCta(request.getParameter("nombreTitular").toUpperCase());
		} else {
			afiliado.setTitularCuenta(request.getParameter("numeroIdentificacion"));
			afiliado.setNombreTitularCta(request.getParameter("nombre").toUpperCase());
		}
		if (request.getParameter("numeroEmpresario") != null) {
			afiliado.setCedulaPapa(request.getParameter("numeroEmpresario"));
		} else {
			afiliado.setCedulaPapa(request.getParameter("codigoEmpresario"));
		}
		if (request.getParameter("numeroDistribuidor") != null) {
			afiliado.setCedulaDistribuidor(request.getParameter("numeroDistribuidor"));
			afiliado.setCedulaDistribuidorPago(request.getParameter("numeroDistribuidor"));
		} else {
			afiliado.setCedulaDistribuidor(UsuarioHelper.getUsuario());
			afiliado.setCedulaDistribuidorPago(UsuarioHelper.getUsuario());
		}
		afiliado.setRed("1");

		afiliado.setUsuarioIngreso(UsuarioHelper.getUsuario());
		afiliado.setFechaIngreso(new Date());
		afiliado.setTipoAfiliado(request.getParameter("rol"));

		return afiliado;
	}

	public static String generarArbol(List<Nodo> listaArbol) {
		String arbol = "var TREE_ITEMS = [";
		for (Nodo nodo : listaArbol) {
			if ((nodo.getHijos() != null) && (nodo.getHijos().size() > 0)) {
				arbol = arbol + "['" + nodo.getCedula() + "-" + nodo.getNombre() + "',0,";
				List<Nodo> hijos = nodo.getHijos();
				for (Nodo nodohijo : hijos) {
					arbol = arbol + "['" + nodohijo.getCedula() + "-" + nodohijo.getNombre()
							+ "'],";
				}
				arbol = arbol.substring(0, arbol.length() - 1);
				arbol = arbol + "],";
			} else {
				arbol = arbol + "['" + nodo.getCedula() + "-" + nodo.getNombre() + "'],";
			}
		}
		arbol = arbol.substring(0, arbol.length() - 1);
		arbol = arbol + "];";
		return arbol;
	}

	public static String imprimirDatos(List<Nodo> listaArbol) {
		String retorno = new String("");
		if ((listaArbol != null) && (listaArbol.size() > 0)) {
			Nodo raiz = (Nodo) listaArbol.get(0);
			System.err.println(raiz.getHijos().size());
			Matcher mat = null;
			Pattern pat = null;
			String cadena = new String("");
			String miArbol = "var TREE_ITEMS = [";
			if (raiz.getHijos().size() > 0) {
				retorno = generarHoja(raiz, cadena, 1);

				pat = Pattern.compile(",]");
				mat = pat.matcher(retorno);
				retorno = mat.replaceAll("]");

				retorno = retorno.substring(0, retorno.length() - 1);
			}
			retorno = miArbol + retorno + "];";
		}
		return retorno;
	}

	public static String generarHoja(Nodo nodo, String cadena, int contador) {
		try {
			System.err.println(nodo.getCedula());
			if (nodo.getHijos().size() == 0) {
				cadena = cadena + "[' " + nodo.getNivel() + "." + contador + " -- "
						+ nodo.getCedula() + " -- " + nodo.getNombre() + " -- "
						+ nodo.getTelefono() + " -- " + nodo.getEmail() + " -- "
						+ nodo.getDireccion() + "'],";

				System.err.println(cadena.toString());
			} else {
				List<Nodo> hijos = nodo.getHijos();
				int i = 0;
				cadena = cadena + "['" + nodo.getNivel() + "." + contador + " -- "
						+ nodo.getCedula() + " -- " + nodo.getNombre() + " -- "
						+ nodo.getTelefono() + " -- " + nodo.getEmail() + " -- "
						+ nodo.getDireccion() + "',0,";
				for (Nodo nodohijo : hijos) {
					i++;
					cadena = generarHoja(nodohijo, cadena, i);
				}
				cadena = cadena + "],";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadena;
	}

	public static String generarHijos(Nodo nodo, String cadena, int contador) {
		try {
			System.err.println(nodo.getCedula());
			if (nodo.getHijos().size() == 0) {
				cadena = cadena + "[' " + nodo.getNivel() + "." + contador + " -- "
						+ nodo.getCedula() + " -- " + nodo.getNombre() + " -- "
						+ nodo.getTelefono() + " -- " + nodo.getEmail() + " -- "
						+ nodo.getDireccion() + "'],";

				System.err.println(cadena.toString());
			} else {
				List<Nodo> hijos = nodo.getHijos();
				int i = 0;
				cadena = cadena + "['" + nodo.getNivel() + "." + contador + " -- "
						+ nodo.getCedula() + " -- " + nodo.getNombre() + " -- "
						+ nodo.getTelefono() + " -- " + nodo.getEmail() + " -- "
						+ nodo.getDireccion() + "',0,";
				for (Nodo nodohijo : hijos) {
					i++;
					cadena = generarHoja(nodohijo, cadena, i);
				}
				cadena = cadena + "],";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cadena;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.shared.helper.AfiliadoHelper
 */
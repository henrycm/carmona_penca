package co.com.multinivel.shared.helper;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.backend.model.Jerarquia;
import co.com.multinivel.backend.model.JerarquiaPK;

public class JerarquiaHelper {
	public static Jerarquia cargarEntidad(HttpServletRequest request) {
		Jerarquia jerarquia = new Jerarquia();
		JerarquiaPK jerarquiaPK = new JerarquiaPK();
		jerarquiaPK.setCedulaHijo(request.getParameter("codigoEmpresario") + "-"
				+ request.getParameter("letra"));
		jerarquiaPK.setCedulaPadre(request.getParameter("numeroEmpresario"));
		jerarquia.setId(jerarquiaPK);
		jerarquia.setFechaIngreso(new Date());
		int nivel = request.getParameter("nivel") != null ? Integer.parseInt(request
				.getParameter("nivel")) + 1 : 0;
		jerarquia.setNivel(new BigDecimal(nivel));
		jerarquia.setRed(request.getParameter("red"));
		jerarquia.setUsuarioIngreso(UsuarioHelper.getUsuario());
		return jerarquia;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.shared.helper.JerarquiaHelper
 */
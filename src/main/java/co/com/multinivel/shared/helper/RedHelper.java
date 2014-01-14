package co.com.multinivel.shared.helper;

import javax.servlet.http.HttpServletRequest;

import co.com.multinivel.backend.model.Red;

public class RedHelper {
	public static Red cargarEntidad(HttpServletRequest request) {
		Red red = new Red();
		red.setCodigo(request.getParameter("red"));
		red.setNombre(request.getParameter("nombreRed"));
		return red;
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.shared.helper.RedHelper
 */
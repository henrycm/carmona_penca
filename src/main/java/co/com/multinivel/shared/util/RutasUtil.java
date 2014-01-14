package co.com.multinivel.shared.util;

import javax.servlet.ServletContext;

public class RutasUtil {

	public static String getRutaImagenes(ServletContext ctx) {
		return ctx.getRealPath("/") + "images/";
	}

	public static String getRutaReportes(ServletContext ctx) {
		return ctx.getRealPath("/") + "reportes/";
	}
}

package co.com.multinivel.shared.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechasUtil {
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static int getDias(Date inicio, Date fin) {
		int retorno = (int) ((fin.getTime() - inicio.getTime()) / (1000 * 60 * 60 * 24));
		return retorno;
	}

	public static Date parse(String fecha) {
		try {
			return sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String format(Date d) {
		return sdf.format(d);
	}
}

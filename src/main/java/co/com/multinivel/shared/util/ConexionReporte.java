package co.com.multinivel.shared.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionReporte {
	public static Connection getConexion() {
		Connection conexion = null;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			conexion = DriverManager.getConnection(
					ParametrosEnum.URL_DATABASE.getValor(),
					ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexion;
	}

	public static void cerrarConexion(Connection conexion) {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location:
 * D:\Dllo\multinivel\multinivelEAR.ear\multinivel.war\WEB-INF\classes\
 * 
 * Qualified Name: co.com.multinivel.shared.util.ConexionReporte
 */
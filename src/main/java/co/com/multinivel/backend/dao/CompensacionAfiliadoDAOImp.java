package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.dto.ReporteConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.ParametrosEnum;

@Repository
@Transactional
public class CompensacionAfiliadoDAOImp implements CompensacionAfiliadoDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ParametroDAO parametroDAO;

	public List<Object> comisionTotalPorDistribuidorPeriodo(String periodo) throws MultinivelDAOException {
		List<Object> lista = new ArrayList<Object>();
		try {

			String sql = "SELECT periodo, distribuidor, nom_distribuidor, Sum(consumo)Consumo, "
					+ " Sum(comision)Comision, Sum(comisionproducto)ComisionProducto, "
					+ " Sum(comisiondinero)ComisionDinero FROM (SELECT c.periodo, c.distribuidor, "
					+ " d.nombre + ' ' + d.apellido Nom_Distribuidor, c.papa, Sum(c.consumoafiliado)Consumo, Sum(c.comision)Comision, "
					+ " CASE WHEN Sum(c.comision) >= p.valor THEN p.valor ELSE Sum(c.comision) END ComisionProducto, "
					+ " CASE WHEN Sum(c.comision) > p.valor THEN Sum(c.comision) - p.valor "
					+ " ELSE 0 END ComisionDinero FROM   t_comision_afiliado_periodo c INNER JOIN t_afiliados d "
					+ " ON c.distribuidor = d.cedula INNER JOIN t_parametros p "
					+ " ON p.nombre_parametro = 'CONSUMO_MINIMO_ABRIR_RED' WHERE  c.periodo = ? "
					+ " GROUP  BY c.periodo, c.distribuidor, d.nombre + ' ' + d.apellido, "
					+ " c.papa, p.valor)Comision GROUP  BY periodo, distribuidor, "
					+ " nom_distribuidor ORDER  BY nom_distribuidor, distribuidor ASC ";

			Query q = this.entityManager.createNativeQuery(sql).setParameter(1, periodo);

			for (Object obj : q.getResultList()) {
				Object[] objectArray = (Object[]) obj;

				ReporteConsumoDTO dto = new ReporteConsumoDTO();
				dto.setDistribuidor((String) objectArray[1]);
				dto.setNom_distribuidor((String) objectArray[2]);
				dto.setConsumoAfiliado(((BigDecimal) objectArray[3]).doubleValue());
				dto.setComision(((BigDecimal) objectArray[4]).doubleValue());
				dto.setComisionProducto(((BigDecimal) objectArray[5]).doubleValue());
				dto.setComisionDinero(((BigDecimal) objectArray[6]).doubleValue());
				lista.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error en el reporte de consumo", getClass());
		}
		return lista;
	}

	public List<Object> consultar(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		List<Object> listaCompensacion = new ArrayList<Object>();
		try {
			String sql = "SELECT afiliado,nivel,consumo,comision,periodo FROM t_compensacion_afiliado_periodo t where periodo=? and afiliado=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				CompensacionAfiliadoDTO compensacionAfiliadoDTOTemp = new CompensacionAfiliadoDTO();
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedula = (String) objectArray[0];
				int nivel = ((Integer) objectArray[1]).intValue();

				double consumo = ((BigDecimal) objectArray[2]).doubleValue();
				double comision = ((BigDecimal) objectArray[3]).doubleValue();
				String periodo = (String) objectArray[4];
				compensacionAfiliadoDTOTemp.setAfiliado(cedula);
				compensacionAfiliadoDTOTemp.setPeriodo(periodo);
				compensacionAfiliadoDTOTemp.setConsumo(consumo);
				compensacionAfiliadoDTOTemp.setComisionTotal(comision);
				compensacionAfiliadoDTOTemp.setNivel(nivel);

				listaCompensacion.add(compensacionAfiliadoDTOTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return listaCompensacion;
	}

	public double totalConsumo(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		double comision = 0.0D;
		try {
			String sql = "SELECT sum(comision) FROM t_compensacion_afiliado_periodo t where periodo=? and afiliado=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				BigDecimal total = (BigDecimal) result.get(0);
				comision = total.doubleValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return comision;
	}

	public List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		List<Object> listaCompensacion = new ArrayList<Object>();
		try {
			String sql = " SELECT  C.AFILIADO, CONCAT (A.NOMBRE ,' ',IF(A.APELLIDO IS NULL,'',A.APELLIDO)), SUM(C.COMISION), C.PERIODO   FROM T_COMPENSACION_AFILIADO_PERIODO C INNER JOIN T_AFILIADOS A  ON C.AFILIADO=A.CEDULA LEFT JOIN T_AFILIADOS D  ON D.CEDULA=A.CEDULADISTRIBUIDORPAGO  WHERE C.PERIODO= ?  AND A.CEDULADISTRIBUIDORPAGO= ? AND C.COMISION >0  GROUP BY A.CEDULADISTRIBUIDORPAGO,D.NOMBRE,C.AFILIADO, A.NOMBRE,C.PERIODO";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				CompensacionAfiliadoDTO compensacionAfiliadoDTOTemp = new CompensacionAfiliadoDTO();
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaAfiliado = (String) objectArray[0];
				String nombreAfiliado = (String) objectArray[1];
				double comision = ((BigDecimal) objectArray[2]).doubleValue();
				String periodo = (String) objectArray[3];

				compensacionAfiliadoDTOTemp.setAfiliado(cedulaAfiliado);
				compensacionAfiliadoDTOTemp.setNombreAfiliado(nombreAfiliado);
				compensacionAfiliadoDTOTemp.setPeriodo(periodo);

				double consumoMinimo = 0.0D;
				double consumoProducto = 0.0D;
				double consumoDinero = 0.0D;
				if (comision > 0.0D) {
					Parametro pconsumoMinimo = this.parametroDAO.obtenerValor("CONSUMO_MINIMO_ABRIR_RED");
					consumoMinimo = Double.parseDouble(pconsumoMinimo.getValor());
					consumoProducto = comision - consumoMinimo;
					if (consumoProducto < 0.0D) {
						consumoProducto = comision;
					} else {
						consumoDinero = consumoProducto;
						consumoProducto = consumoMinimo;
					}
				}
				compensacionAfiliadoDTOTemp.setComisionProducto(consumoProducto);
				compensacionAfiliadoDTOTemp.setComisionDinero(consumoDinero);
				compensacionAfiliadoDTOTemp.setComisionTotal(comision);
				listaCompensacion.add(compensacionAfiliadoDTOTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return listaCompensacion;
	}

	public int liquidar(String distribuidor, String periodo) throws MultinivelDAOException {
		int param1 = 0;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command1 = "{call Sp_Liquidar(?,?)}";
			CallableStatement cstmt1 = conexion.prepareCall(command1);
			cstmt1.setString(1, distribuidor);
			cstmt1.setString(2, periodo);
			cstmt1.execute();

			cstmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return param1;
	}

	public void calcularArbol(String cedula, String tipoUsuario) throws MultinivelDAOException {
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command = "{call Sp_Arbol(?,?)}";
			CallableStatement cstmt = conexion.prepareCall(command);
			cstmt.setString(1, cedula);
			cstmt.setString(2, tipoUsuario);
			cstmt.execute();

			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error al calcular el arbol", getClass());
		}
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.CompensacionAfiliadoDAOImp
 */
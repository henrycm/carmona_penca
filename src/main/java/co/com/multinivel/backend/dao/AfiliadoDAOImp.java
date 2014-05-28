package co.com.multinivel.backend.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.dto.AfiliadosNivel;
import co.com.multinivel.shared.dto.Nodo;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.FechasUtil;
import co.com.multinivel.shared.util.ParametrosEnum;

@Repository
@Transactional
public class AfiliadoDAOImp implements AfiliadoDAO {
	private static Logger log = Logger.getLogger(AfiliadoDAOImp.class);

	@PersistenceContext
	private EntityManager entityManager;

	public Afiliado consultar(String codigo) throws MultinivelDAOException {
		Afiliado afiliado = null;
		try {
			String sql = " SELECT a.cedula, a.nombre,a.apellido,  a.red, a.direccion,a.barrio,a.telefono,a.celular,a.fecha_nacimiento,a.departamento,a.ciudad, a.departamentoResidencia,a.ciudadResidencia,a.email,a.estadocivil,a.ocupacion,a.cedula_Papa, a.tipo_cuenta,a.cuenta_nro,a.titular_cuenta,a.nombretitularcta,a.banco,a.documentoConyugue,a.nombreConyugue, a.tipoAfiliado,a.cedulaDistribuidor,a.cedulaDistribuidorPago,tipoDocumento,a.idAfiliacionDist, a.Activo from t_afiliados a  where cedula='"
					+ codigo + "' ";

			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedula = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				String apellido = (String) objectArray[2];
				String red = (String) objectArray[3];
				String direccion = (String) objectArray[4];
				String barrio = (String) objectArray[5];
				String telefono = (String) objectArray[6];
				String celular = (String) objectArray[7];
				String tmp = (String) objectArray[8];
				Date fechaNacimiento = FechasUtil.parse(tmp);
				String departamento = (String) objectArray[9];
				String ciudad = (String) objectArray[10];
				String departamentoResidencia = (String) objectArray[11];
				String ciudadResidencia = (String) objectArray[12];
				String email = (String) objectArray[13];
				String estadoCivil = (String) objectArray[14];
				String ocupacion = (String) objectArray[15];
				String cedulaPapa = (String) objectArray[16];
				String tipoCta = (String) objectArray[17];
				String numeroCuenta = (String) objectArray[18];
				String titularCta = (String) objectArray[19];
				String nombreTitularCta = (String) objectArray[20];
				String banco = (String) objectArray[21];
				String documentoConyugue = (String) objectArray[22];
				String nombreConyugue = (String) objectArray[23];
				String tipoAfiliado = objectArray[24].toString();
				String distribuidor = objectArray[25].toString();
				String distribuidorPago = objectArray[26].toString();
				String tipoDocumento = objectArray[27].toString();
				int consecutivoAfiliaciondis = ((Integer) objectArray[28]).intValue();
				String activo = objectArray[29].toString();

				afiliado = new Afiliado();
				afiliado.setCedula(cedula);
				afiliado.setNombre(nombre);
				afiliado.setApellido(apellido);
				afiliado.setRed(red);
				afiliado.setBanco(banco);
				afiliado.setBarrio(barrio);
				afiliado.setCedulaDistribuidor(distribuidor);
				afiliado.setCedulaDistribuidorPago(distribuidorPago);
				afiliado.setCedulaPapa(cedulaPapa);
				afiliado.setCelular(celular);
				afiliado.setCiudad(ciudad);
				afiliado.setCiudadResidencia(ciudadResidencia);
				afiliado.setCuentaNro(numeroCuenta);
				afiliado.setDepartamento(departamento);
				afiliado.setDepartamentoResidencia(departamentoResidencia);
				afiliado.setDireccion(direccion);
				afiliado.setDocumentoConyugue(documentoConyugue);
				afiliado.setEmail(email);
				afiliado.setEstadoCivil(estadoCivil);
				afiliado.setFechaNacimiento(fechaNacimiento);
				afiliado.setNombreConyugue(nombreConyugue);
				afiliado.setOcupacion(ocupacion);
				afiliado.setTelefono(telefono);
				afiliado.setTipoAfiliado(tipoAfiliado);
				afiliado.setTipoCuenta(tipoCta);
				afiliado.setTipoDocumento(tipoDocumento);
				afiliado.setTitularCuenta(titularCta);
				afiliado.setNombreTitularCta(nombreTitularCta);
				afiliado.setIdAfiliacionDistribuidor(consecutivoAfiliaciondis);
				afiliado.setActivo(activo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error consultando el afiliado -" + e.getMessage(),
					getClass());
		}
		return afiliado;
	}

	public Afiliado consultar(String codigo, String pnombre, String papellido)
			throws MultinivelDAOException {
		Afiliado afiliado = null;
		try {
			String sql = " SELECT a.cedula, a.nombre,a.apellido, a.nivel, a.red, a.direccion,a.barrio,a.telefono,a.celular,a.fecha_nacimiento,a.departamento,a.ciudad, a.departamentoResidencia,a.ciudadResidencia,a.email,a.estadocivil,a.ocupacion,a.cedula_Papa, a.tipo_cuenta,a.cuenta_nro,a.titular_cuenta,a.nombretitularcta,a.banco,a.documentoConyugue,a.nombreConyugue, a.tipoAfiliado,a.cedulaDistribuidor,a.cedulaDistribuidorPago,tipoDocumento from t_afiliados a  where UPPER(activo) ='SI' ";
			if ((codigo != null) && ("".equals(codigo))) {
				sql = sql + "and a.cedula='" + codigo + "' ";
			}
			if ((pnombre != null) && ("".equals(pnombre))) {
				sql = sql + "and a.nombre='" + codigo + "' ";
			}
			if ((papellido != null) && ("".equals(papellido))) {
				sql = sql + "and a.apellido='" + codigo + "' ";
			}
			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedula = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				String apellido = (String) objectArray[2];
				String red = (String) objectArray[4];
				String direccion = (String) objectArray[5];
				String barrio = (String) objectArray[6];
				String telefono = (String) objectArray[7];
				String celular = (String) objectArray[8];
				String tmp = (String) objectArray[9];
				Date fechaNacimiento = FechasUtil.parse(tmp);
				String departamento = (String) objectArray[10];
				String ciudad = (String) objectArray[11];
				String departamentoResidencia = (String) objectArray[12];
				String ciudadResidencia = (String) objectArray[13];
				String email = (String) objectArray[14];
				String estadoCivil = (String) objectArray[15];
				String ocupacion = (String) objectArray[16];
				String cedulaPapa = (String) objectArray[17];
				String tipoCta = (String) objectArray[18];
				String numeroCuenta = (String) objectArray[19];
				String nombreTitularCta = (String) objectArray[21];
				String titularCta = (String) objectArray[20];
				String banco = (String) objectArray[22];
				String documentoConyugue = (String) objectArray[23];
				String nombreConyugue = (String) objectArray[24];
				String tipoAfiliado = (String) objectArray[25];
				String distribuidor = (String) objectArray[26];
				String distribuidorPago = (String) objectArray[27];
				String tipoDocumento = (String) objectArray[28];

				afiliado = new Afiliado();
				afiliado.setCedula(cedula);
				afiliado.setNombre(nombre);
				afiliado.setApellido(apellido);
				afiliado.setRed(red);
				afiliado.setBanco(banco);
				afiliado.setBarrio(barrio);
				afiliado.setCedulaDistribuidor(distribuidor);
				afiliado.setCedulaDistribuidorPago(distribuidorPago);
				afiliado.setCedulaPapa(cedulaPapa);
				afiliado.setCelular(celular);
				afiliado.setCiudad(ciudad);
				afiliado.setCiudadResidencia(ciudadResidencia);
				afiliado.setCuentaNro(numeroCuenta);
				afiliado.setDepartamento(departamento);
				afiliado.setDepartamentoResidencia(departamentoResidencia);
				afiliado.setDireccion(direccion);
				afiliado.setDocumentoConyugue(documentoConyugue);
				afiliado.setEmail(email);
				afiliado.setEstadoCivil(estadoCivil);
				afiliado.setFechaNacimiento(fechaNacimiento);
				afiliado.setNombreConyugue(nombreConyugue);
				afiliado.setOcupacion(ocupacion);
				afiliado.setTelefono(telefono);
				afiliado.setTipoAfiliado(tipoAfiliado);
				afiliado.setTipoCuenta(tipoCta);
				afiliado.setTipoDocumento(tipoDocumento);
				afiliado.setTitularCuenta(titularCta);
				afiliado.setNombreTitularCta(nombreTitularCta);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error consultando el afiliado -" + e.getMessage(),
					getClass());
		}
		return afiliado;
	}

	public boolean eliminar(Afiliado afiliado) throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		int returnId = 0;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(
					ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command = "{call Sp_EliminarAfiliado(?,?)}";
			CallableStatement cstmt = conexion.prepareCall(command);
			cstmt.setString(1, afiliado.getCedula());
			cstmt.registerOutParameter(2, Types.NUMERIC);
			cstmt.execute();

			returnId = cstmt.getInt(2);
			retorno = returnId == 0 ? Boolean.TRUE : Boolean.FALSE;

			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar el cambio de documento:"
					+ e.getMessage(), getClass());
		}
		return retorno;
	}

	public void ingresar(Afiliado afiliado) throws MultinivelDAOException {
		try {
			Afiliado afiliadoConsultado = consultar(afiliado.getCedula());
			if (afiliadoConsultado == null) {
				this.entityManager.persist(afiliado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el afiliado -" + e.getMessage(),
					getClass());
		}
	}

	public void actualizar(Afiliado afiliado) throws MultinivelDAOException {
		try {
			Afiliado afiliadoConsultado = entityManager.find(Afiliado.class, afiliado.getCedula());
			/**
			 * Se corrige para no actualizar la fecha de creación. JOHECAMA.
			 * 2014-04-09
			 */
			afiliado.setFechaIngreso(afiliadoConsultado.getFechaIngreso());
			if (afiliadoConsultado != null) {
				this.entityManager.merge(afiliado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error actualizando el afiliado -" + e.getMessage(),
					getClass());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Afiliado> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Afiliado");
		List<Afiliado> lista = query.getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<AfiliadoDTO> buscar(String codigo, String nombre, String cedulaDistribuidor)
			throws MultinivelDAOException {
		List<AfiliadoDTO> lista = null;
		try {
			String sql = " SELECT a.cedula, a.nombre,a.apellido,  a.red from t_afiliados a  where UPPER(activo) ='SI'  ";
			if ((cedulaDistribuidor != null) && (!cedulaDistribuidor.equals(""))) {
				sql = sql + " and a.cedulaDistribuidor='" + cedulaDistribuidor + "'";
			}
			if ((codigo != null) && (!codigo.equals(""))) {
				sql = sql + " and a.cedula = '" + codigo + "'";
			}
			if ((nombre != null) && (!nombre.equals(""))) {
				sql = sql + " and  a.nombre +' '+ a.apellido LIKE '%" + nombre + "%'";
			}
			Query q = this.entityManager.createNativeQuery(sql);

			List<Afiliado> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<AfiliadoDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaPadre = (String) objectArray[0];
				String nombrePadre = (String) objectArray[1];
				String apellidoPadre = (String) objectArray[2];
				String red = (String) objectArray[3];

				AfiliadoDTO a = new AfiliadoDTO();
				a.setCedula(cedulaPadre);
				a.setNombre(nombrePadre);
				a.setApellido(apellidoPadre);
				a.setRed(red);
				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}

	public List<Object> listarPorNivel(String red) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = "SELECT p.nombre + ' ' + p.apellido Nombre_Padre, "
					+ "       h.nombre + ' ' + h.apellido Nombre_Hijo, "
					+ "       p.cedula                    Cedula_Padre, "
					+ "       h.cedula                    Cedula_Hijo "
					+ "FROM   t_afiliados p " + "       INNER JOIN t_afiliados h "
					+ "               ON p.cedula = h.cedula_papa "
					+ "WHERE  h.ceduladistribuidor = ? ";

			Query q = this.entityManager.createNativeQuery(sql);

			q.setParameter(1, red);
			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList<Object>();
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String nombrePadre = (String) objectArray[0];
					String nombreHijo = (String) objectArray[1];
					String cedulaPadre = (String) objectArray[2];

					String cedulaHijo = (String) objectArray[3];
					AfiliadosNivel an = new AfiliadosNivel();
					an.setCedula(cedulaHijo);
					an.setCedulaPadre(cedulaPadre);
					an.setNombrePadre(nombrePadre);
					an.setNombre(nombreHijo);
					lista.add(an);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return lista;
	}

	public List<AfiliadoDTO> buscarDistribuidor(String codigo, String nombre)
			throws MultinivelDAOException {
		List<AfiliadoDTO> lista = null;
		try {
			String sql = "SELECT a.cedula, \n" + "       a.nombre, \n" + "       a.apellido, \n"
					+ "       a.tipo_cuenta, \n"
					+ "       a.cuenta_nro, \n" + "       a.banco, \n"
					+ "       COALESCE(b.nm_afiliados, 0), \n"
					+ "       COALESCE(c.cantidad, 0)nm_cuota_afiliados \n"
					+ "FROM   t_afiliados a \n"
					+ "       LEFT JOIN (SELECT ceduladistribuidor, \n"
					+ "                         Count(*)nm_afiliados \n"
					+ "                  FROM   t_afiliados \n"
					+ "                  GROUP  BY ceduladistribuidor)b \n"
					+ "              ON a.cedula = b.ceduladistribuidor \n"
					+ "       LEFT JOIN t_cantidad_afiliaciones_distribuidor c \n"
					+ "              ON a.cedula = c.distribuidor \n" + "WHERE  a.activo = 'si' \n"
					+ "       AND a.tipoafiliado = '2'";
			if ((codigo != null) && (!"".equals(codigo))) {
				sql = sql + " AND  a.cedula ='" + codigo + "'";
			}
			if ((nombre != null) && (!"".equals(nombre))) {
				sql = sql + " AND  a.nombre like '%" + nombre + "%'";
			}
			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<AfiliadoDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaPadre = (String) objectArray[0];
				String nombrePadre = (String) objectArray[1];
				String apellidoPadre = (String) objectArray[2];
				String tipoCta = (String) objectArray[3];
				String numeroCta = (String) objectArray[4];
				String banco = (String) objectArray[5];

				AfiliadoDTO a = new AfiliadoDTO();
				a.setCedula(cedulaPadre);
				a.setNombre(nombrePadre);
				a.setApellido(apellidoPadre);
				a.setTipoCuenta(tipoCta);
				a.setCuentaNro(numeroCta);
				a.setBanco(banco);
				a.setNumeroAfiliados(Integer.parseInt(objectArray[6].toString()));
				a.setNmAfiliadosPermitidos(Integer.parseInt(objectArray[7].toString()));

				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}

	public List<AfiliadoDTO> listarDistribuidores() throws MultinivelDAOException {
		List<AfiliadoDTO> lista = null;
		try {
			String sql = "SELECT a.cedula, \n" + "       a.nombre, \n" + "       a.apellido, \n"
					+ "       a.tipo_cuenta, \n"
					+ "       a.cuenta_nro, \n" + "       a.banco, \n"
					+ "       COALESCE(b.nm_afiliados, 0), \n"
					+ "       COALESCE(c.cantidad, 0)nm_cuota_afiliados \n"
					+ "FROM   t_afiliados a \n"
					+ "       LEFT JOIN (SELECT ceduladistribuidor, \n"
					+ "                         Count(*)nm_afiliados \n"
					+ "                  FROM   t_afiliados \n"
					+ "                  GROUP  BY ceduladistribuidor)b \n"
					+ "              ON a.cedula = b.ceduladistribuidor \n"
					+ "       LEFT JOIN t_cantidad_afiliaciones_distribuidor c \n"
					+ "              ON a.cedula = c.distribuidor \n" + "WHERE  a.activo = 'si' \n"
					+ "       AND a.tipoafiliado = '2' \n"
					+ "       AND upper(a.activo) = 'SI' \n" + "ORDER BY a.nombre";

			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<AfiliadoDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaPadre = (String) objectArray[0];
				String nombrePadre = (String) objectArray[1];
				String apellidoPadre = (String) objectArray[2];
				String tipoCta = (String) objectArray[3];
				String numeroCta = (String) objectArray[4];
				String banco = (String) objectArray[5];

				AfiliadoDTO a = new AfiliadoDTO();
				a.setCedula(cedulaPadre);
				a.setNombre(nombrePadre);
				a.setApellido(apellidoPadre);
				a.setTipoCuenta(tipoCta);
				a.setCuentaNro(numeroCta);
				a.setBanco(banco);
				a.setNumeroAfiliados(Integer.parseInt(objectArray[6].toString()));
				a.setNmAfiliadosPermitidos(Integer.parseInt(objectArray[7].toString()));

				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}

	public void actualizarAfiliadoADistribuidor(Afiliado afiliado) throws MultinivelDAOException {
		try {
			Afiliado afiliadoConsultado = consultar(afiliado.getCedula());
			if (afiliadoConsultado != null) {
				Query rs = this.entityManager
						.createNativeQuery("UPDATE  T_AFILIADOS  SET CEDULADISTRIBUIDOR=?,CEDULADISTRIBUIDORPAGO=?, TIPOAFILIADO='2' WHERE CEDULA=?");

				rs.setParameter(1, afiliado.getCedula());
				rs.setParameter(2, afiliado.getCedula());
				rs.setParameter(3, afiliado.getCedula());

				rs.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error actualizando el afiliado -" + e.getMessage(),
					getClass());
		}
	}

	public List<Object> listaAfiliadosPorDistribuidor(String periodo) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = " SELECT  a.cedula,CONCAT (A.NOMBRE ,' ',IF(A.APELLIDO IS NULL,'',A.APELLIDO)), count(t.cedula)  FROM t_afiliados t,t_afiliados a where t.cedulaDistribuidor =a.cedula and DATE_FORMAT(t.FECHAingreso,'%m/%Y')=?group by a.cedula order by t.fechaingreso, a.nombre";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, periodo);

			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<Object>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaDistribuidor = (String) objectArray[0];
				String nombreDistribuidor = (String) objectArray[1];
				BigInteger numeroAfiliados = (BigInteger) objectArray[2];

				AfiliadoDTO a = new AfiliadoDTO();
				a.setCedulaDistribuidor(cedulaDistribuidor);
				a.setNombreDistribuidor(nombreDistribuidor);
				a.setNumeroAfiliados(numeroAfiliados.intValue());

				lista.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}

	public boolean cambiarDocumento(String documentoActual, String documentoNuevo)
			throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		int param1 = 0;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(
					ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command1 = "{call  SP_ACTUALIZAR_PADRE(?,?,?)}";
			CallableStatement cstmt1 = conexion.prepareCall(command1);
			cstmt1.setString(1, documentoActual);
			cstmt1.setString(2, documentoNuevo);
			cstmt1.registerOutParameter(3, 4);

			cstmt1.execute();
			param1 = cstmt1.getInt(3);
			if (param1 == 1) {
				retorno = Boolean.TRUE;
			}
			cstmt1.close();
		} catch (Exception e) {
			e.printStackTrace();

			throw new MultinivelDAOException("error al realizar el cambio de documento:"
					+ e.getMessage(), getClass());
		}
		return retorno;
	}

	public int contarAfiliacionesPorPeriodoDistribuidor(String cedulaDistribuidor, String periodo)
			throws MultinivelDAOException {
		int s = 0;
		int registros = 0;
		try {
			String sql = " SELECT count(*)   from t_afiliados a  where UPPER(activo) ='SI'   and a.cedulaDistribuidor='"
					+

					cedulaDistribuidor
					+ "'"
					+ " and DATE_FORMAT(a.FECHAingreso,'%m/%Y')='"
					+ periodo + "'";

			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				BigInteger objectArray = (BigInteger) obj;
				registros = objectArray.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return registros;
	}

	public int consultarIdDistribuidor(String distribuidor) throws MultinivelDAOException {
		int retorno = 0;
		int s = 0;
		try {
			String sql = " SELECT Coalesce(max(idafiliaciondist)+1,1)  FROM t_afiliados where ceduladistribuidor='"
					+

					distribuidor + "'";

			Query q = this.entityManager.createNativeQuery(sql);

			List<?> result = q.getResultList();
			s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				retorno = Integer.parseInt(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al consultar el consecutivo de distribuidor",
					getClass());
		}
		return retorno;
	}

	public List<Nodo> ArbolAfiliado(String afiliado) throws MultinivelDAOException {
		List<Nodo> lista = null;
		try {
			lista = new ArrayList<Nodo>();

			StringBuffer sql = new StringBuffer();

			sql.append("    SELECT CONCAT(A.NOMBRE,' ', A.APELLIDO)NOMBRE_AFILIADO,AFI.NIVEL,afi.CEDULA,afi.CEDULA_PAPA,AFI.TELEFONO,AFI.EMAIL,AFI.DIRECCION,AFI.CELULAR ");
			sql.append("   FROM  ");
			sql.append("   (SELECT H.CEDULA,'0' CEDULA_PAPA, 0 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
			sql.append("\t\t\t\t \tFROM T_AFILIADOS H ");
			sql.append("\t\t\t\t   WHERE H.CEDULA='" + afiliado + "' ");

			sql.append("        UNION ALL ");

			sql.append("       SELECT P.CEDULA,P.CEDULA_PAPA, 1 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
			sql.append("\t\t\t \tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \tON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t   AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t   UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 2 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR FROM ");
			sql.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t \tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \tON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t   AND H.CEDULA='" + afiliado + "')NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \tON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 3 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t        )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t        ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 4 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 5 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t  FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t  ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 6 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");
			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 7 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL6 ");
			sql.append("\t\t\t INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t UNION ALL ");

			sql.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 8 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("             FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");
			sql.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 9 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append(" \t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");
			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 10 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("        (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL9 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("      UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 11 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL9 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL10 ");
			sql.append("\t\t\t INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 12 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL9 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL10 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL11 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 13 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA  ");
			sql.append("\t\t\t )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL9 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL10 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("       )NIVEL11 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  )NIVEL12 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 14 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA  ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P  ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA  ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t      )NIVEL1 INNER JOIN T_AFILIADOS A  ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A  ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL9 ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL10 ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL11 ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("       ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  )NIVEL12 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL13 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  UNION ALL ");

			sql.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 15 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t        AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  ) NIVEL4 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL6 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL7 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL8 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL9 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL10 ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("       ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t )NIVEL11 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t )NIVEL12 ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("\t\t\t  )NIVEL13 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("\t\t\t  )NIVEL14 ");
			sql.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
			sql.append("\t\t\t  ON NIVEL14.CEDULA_PAPA=A.CEDULA_PAPA)AFI ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("       ON A.CEDULA =AFI.CEDULA ");
			Query q = this.entityManager.createNativeQuery(sql.toString());

			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String nombre = (String) objectArray[0];
					int nivel = ((BigInteger) objectArray[1]).intValue();
					String cedulaAfiliado = (String) objectArray[2];
					String cedulaPadre = (String) objectArray[3];
					String telefono = (String) objectArray[4];
					String email = (String) objectArray[5];
					String direccion = (String) objectArray[6];
					String celular = (String) objectArray[7];

					Nodo nodo = new Nodo();
					nodo.setCedula(cedulaAfiliado);
					nodo.setCedulaPadre(cedulaPadre);
					nodo.setNombre(nombre);
					nodo.setNivel(nivel);
					nodo.setTelefono(telefono == null ? celular : telefono);
					nodo.setDireccion(direccion);
					nodo.setEmail(email);

					buscarPadre(cedulaPadre, nodo, lista);
					lista.add(nodo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return lista;
	}

	public List<Nodo> generarHijosAfiliado(String afiliado) throws MultinivelDAOException {
		List<Nodo> lista = null;
		try {
			lista = new ArrayList<Nodo>();

			StringBuffer sql = new StringBuffer();

			sql.append("    SELECT CONCAT(A.NOMBRE,' ', A.APELLIDO)NOMBRE_AFILIADO,AFI.NIVEL,afi.CEDULA,afi.CEDULA_PAPA,AFI.TELEFONO,AFI.EMAIL,AFI.DIRECCION,AFI.CELULAR ");
			sql.append("   FROM  ");
			sql.append("   ( SELECT H.CEDULA,'0' CEDULA_PAPA, 0 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
			sql.append("\t\t\t\t \tFROM T_AFILIADOS H ");
			sql.append("\t\t\t\t   WHERE H.CEDULA='" + afiliado + "' ");

			sql.append("        UNION ALL ");
			sql.append("       SELECT P.CEDULA,P.CEDULA_PAPA, 1 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
			sql.append("\t\t\t \tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t\t\t \tON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("\t\t\t   AND H.CEDULA='" + afiliado + "' ");
			sql.append("\t)AFI ");
			sql.append("       INNER JOIN T_AFILIADOS A ");
			sql.append("       ON A.CEDULA =AFI.CEDULA ");
			Query q = this.entityManager.createNativeQuery(sql.toString());

			System.err.println(sql.toString());

			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String nombre = (String) objectArray[0];
					int nivel = ((BigInteger) objectArray[1]).intValue();
					String cedulaAfiliado = (String) objectArray[2];
					String cedulaPadre = (String) objectArray[3];
					String telefono = (String) objectArray[4];
					String email = (String) objectArray[5];
					String direccion = (String) objectArray[6];
					String celular = (String) objectArray[7];

					Nodo nodo = new Nodo();
					nodo.setCedula(cedulaAfiliado);
					nodo.setCedulaPadre(cedulaPadre);
					nodo.setNombre(nombre);
					nodo.setNivel(nivel);
					nodo.setTelefono(telefono == null ? celular : telefono);
					nodo.setDireccion(direccion);
					nodo.setEmail(email);

					buscarPadre(cedulaPadre, nodo, lista);
					lista.add(nodo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return lista;
	}

	private void buscarPadre(String cedulaPadre, Nodo nodoHijo, List<Nodo> lista) {
		Iterator<Nodo> itr = lista.iterator();
		boolean encontro = Boolean.FALSE;
		Nodo nodoRetorno = null;
		while ((itr.hasNext()) && (!encontro)) {
			nodoRetorno = (Nodo) itr.next();
			if (cedulaPadre.equals(nodoRetorno.getCedula())) {
				if (nodoRetorno.getHijos() != null) {
					nodoRetorno.getHijos().add(nodoHijo);
				}
			}
		}
	}

	public List<Object> listarAfiliadosDistribuidorPorPeriodo(String periodo)
			throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = "SELECT a.cedula, "
					+ "       a.nombre + ' ' + a.apellido Nombre_Afiliado, "
					+ "       a.fechaingreso, "
					+ "       a.ceduladistribuidor, "
					+ "       d.nombre + ' ' + d.apellido NombreDistribuidor "
					+ "FROM   t_afiliados a "
					+ "       INNER JOIN t_afiliados d "
					+ "               ON a.ceduladistribuidor = d.cedula "
					+ "                  AND d.tipoafiliado = '2' "
					+ "WHERE  RIGHT('00'+Cast(Month(a.fechaingreso) AS VARCHAR(2)), 2) "
					+ "       + '/' " + "       + Cast(Year(a.fechaingreso) AS VARCHAR(4)) = ? ";

			Query q = this.entityManager.createNativeQuery(sql);

			q.setParameter(1, periodo);
			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList<Object>();
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String cedulaAfiliado = (String) objectArray[0];
					String nombreAfiliado = (String) objectArray[1];
					String tmp = (String) objectArray[2];
					Date fechaIngreso = FechasUtil.parse(tmp);
					String cedulaDistribuidor = (String) objectArray[3];
					String nombreDistribuidor = (String) objectArray[4];
					Afiliado afiliado = new Afiliado();
					afiliado.setNombre(nombreAfiliado);
					afiliado.setCedula(cedulaAfiliado);
					afiliado.setFechaIngreso(fechaIngreso);
					afiliado.setCedulaDistribuidor(cedulaDistribuidor);
					afiliado.setCedulaDistribuidorPago(nombreDistribuidor);
					lista.add(afiliado);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException(
					"error al listar los afiliados por distribuidor periodo", getClass());
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Afiliado> buscar(String nomFiltro, String filtro) throws MultinivelDAOException {
		String query = "from Afiliado a where a." + nomFiltro + " like :filtro order by a.nombre";
		Query q = entityManager.createQuery(query);
		q.setParameter("filtro", "%" + filtro + "%");
		return q.getResultList();
	}

	public void cambiarDistribuidor(String dist_ant, String dist_nuevo)
			throws MultinivelDAOException {
		String query = "update Afiliado a set a.cedulaDistribuidor = :dist_nuevo, a.cedulaDistribuidorPago = :dist_nuevo "
				+
				"where a.cedulaDistribuidor = :dist_ant";
		Query q = entityManager.createQuery(query);
		q.setParameter("dist_ant", dist_ant);
		q.setParameter("dist_nuevo", dist_nuevo);
		int nmregistros = q.executeUpdate();
		log.debug("Registros actualizados: " + nmregistros);
	}
}
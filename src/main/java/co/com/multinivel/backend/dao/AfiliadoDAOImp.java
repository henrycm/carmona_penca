package co.com.multinivel.backend.dao;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.shared.dto.AfiliadoDTO;
import co.com.multinivel.shared.dto.AfiliadosNivel;
import co.com.multinivel.shared.dto.Nodo;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.ParametrosEnum;

@Component
public class AfiliadoDAOImp implements AfiliadoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public Afiliado consultar(String codigo) throws MultinivelDAOException {
		Afiliado afiliado = null;
		int filtros = 0;
		try {
			String sql = " SELECT a.cedula, a.nombre,a.apellido,  a.red, a.direccion,a.barrio,a.telefono,a.celular,a.fecha_nacimiento,a.departamento,a.ciudad, a.departamentoResidencia,a.ciudadResidencia,a.email,a.estadocivil,a.ocupacion,a.cedula_Papa, a.tipo_cuenta,a.cuenta_nro,a.titular_cuenta,a.nombretitularcta,a.banco,a.documentoConyugue,a.nombreConyugue, a.tipoAfiliado,a.cedulaDistribuidor,a.cedulaDistribuidorPago,tipoDocumento,a.idAfiliacionDist from t_afiliados a  where UPPER(activo) ='SI' and cedula='"
					+

					codigo + "' ";

			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
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
				String fechaNacimiento = (String) objectArray[8];
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
				String tipoAfiliado = (String) objectArray[24];
				String distribuidor = (String) objectArray[25];
				String distribuidorPago = (String) objectArray[26];
				String tipoDocumento = (String) objectArray[27];
				int consecutivoAfiliaciondis = ((Integer) objectArray[28]).intValue();

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
		int filtros = 0;
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

			List result = q.getResultList();
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
				String fechaNacimiento = (String) objectArray[9];
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
		boolean retorno = false;
		try {
			String sql = " SELECT a.cedula    FROM t_afiliados a   where a.cedula_papa=? ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, afiliado.getCedula());

			List result = q.getResultList();
			int s = result.size();
			System.err.println("s:" + s);
			if (s <= 0) {
				System.err.println("cedula:" + afiliado.getCedula());

				Afiliado afiliadoConsultado = consultar(afiliado.getCedula());
				System.err.println("afiliadoconsultado:" + afiliadoConsultado);
				if (afiliadoConsultado != null) {
					Query rs = this.entityManager
							.createNativeQuery("DELETE FROM T_AFILIADOS   WHERE CEDULA=? ");

					rs.setParameter(1, afiliado.getCedula());
					rs.executeUpdate();
					retorno = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error borrando el afiliado -" + e.getMessage(),
					getClass());
		}
		return retorno;
	}

	public void ingresar(Afiliado afiliado) throws MultinivelDAOException {
		EntityTransaction tx = null;
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
			Afiliado afiliadoConsultado = consultar(afiliado.getCedula());
			if (afiliadoConsultado != null) {
				this.entityManager.merge(afiliado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error actualizando el afiliado -" + e.getMessage(),
					getClass());
		}
	}

	public List<Afiliado> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Afiliado");
		List<Afiliado> lista = query.getResultList();
		return lista;
	}

	public List<AfiliadoDTO> buscar(String codigo, String nombre, String cedulaDistribuidor)
			throws MultinivelDAOException {
		List<AfiliadoDTO> lista = null;
		int filtros = 0;
		try {
			String sql = " SELECT a.cedula, a.nombre,a.apellido,  a.red from t_afiliados a  where UPPER(activo) ='SI'  ";
			if ((cedulaDistribuidor != null) && (!cedulaDistribuidor.equals(""))) {
				sql = sql + " and a.cedulaDistribuidor='" + cedulaDistribuidor + "'";
			}
			if ((codigo != null) && (!codigo.equals(""))) {
				sql = sql + " and a.cedula = '" + codigo + "'";
			}
			if ((nombre != null) && (!nombre.equals(""))) {
				sql = sql + " and  CONCAT(a.nombre,' ',a.apellido) LIKE '%" + nombre + "%'";
			}
			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
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
			String sql = "SELECT X.NOMBRE_PADRE,X.NOMBRE_HIJO,X.CEDULA_PADRE, X.CEDULA_HIJO     FROM (  SELECT CONCAT (P.NOMBRE ,' ',IF(P.APELLIDO IS NULL,'',P.APELLIDO)) NOMBRE_PADRE, CONCAT (H.NOMBRE ,' ',IF(H.APELLIDO IS NULL,'',H.APELLIDO))  NOMBRE_HIJO, P.CEDULA CEDULA_PADRE, H.CEDULA CEDULA_HIJO   FROM T_AFILIADOS H,   T_AFILIADOS P WHERE  P.CEDULA = H.CEDULA_PAPA  AND H.CEDULADISTRIBUIDOR =?) X ";

			Query q = this.entityManager.createNativeQuery(sql);

			q.setParameter(1, red);
			List result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList();
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
		int filtros = 0;
		try {
			String sql = " SELECT DISTINCT b.cedula, b.nombre,b.apellido,b.tipo_cuenta,b.cuenta_nro,b.banco    FROM t_afiliados a,t_afiliados b   where b.cedula= a.ceduladistribuidor AND a.tipoAfiliado='2' ";
			if ((codigo != null) && (!"".equals(codigo))) {
				sql = sql + " AND  a.cedulaDistribuidor ='" + codigo + "'";
			}
			if ((nombre != null) && (!"".equals(nombre))) {
				sql = sql + " AND  a.nombre like '%" + nombre + "%'";
			}
			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
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
		int filtros = 0;
		try {
			String sql = " SELECT DISTINCT b.cedula, b.nombre,b.apellido,b.tipo_cuenta,b.cuenta_nro,b.banco    FROM t_afiliados a,t_afiliados b   where b.cedula= a.ceduladistribuidor AND a.tipoAfiliado='2' order by b.nombre";

			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
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

	public boolean validarNivelesRed(Afiliado afiliado, String Distribuidor)
			throws MultinivelDAOException {
		boolean retorno = false;
		try {
			String sql = " SELECT a.cedula  from t_afiliados a  where    a.cedula = '" +

			afiliado.getCedula() + "'";

			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaPadre = (String) objectArray[0];
				retorno = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return retorno;
	}

	public List<Object> listaAfiliadosPorDistribuidor(String periodo) throws MultinivelDAOException {
		List<Object> lista = null;
		int filtros = 0;
		try {
			String sql = " SELECT  a.cedula,CONCAT (A.NOMBRE ,' ',IF(A.APELLIDO IS NULL,'',A.APELLIDO)), count(t.cedula)  FROM multinivel.t_afiliados t,multinivel.t_afiliados a where t.cedulaDistribuidor =a.cedula and DATE_FORMAT(t.FECHAingreso,'%m/%Y')=?group by a.cedula order by t.fechaingreso, a.nombre";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, periodo);

			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
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
		boolean retorno = false;
		int param1 = 0;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(
					ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			System.err.println(documentoActual);
			System.err.println(documentoNuevo);
			String command1 = "{call  SP_ACTUALIZAR_PADRE(?,?,?)}";
			CallableStatement cstmt1 = conexion.prepareCall(command1);
			cstmt1.setString(1, documentoActual);
			cstmt1.setString(2, documentoNuevo);
			cstmt1.registerOutParameter(3, 4);

			cstmt1.execute();
			param1 = cstmt1.getInt(3);
			if (param1 == 1) {
				retorno = true;
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

			List result = q.getResultList();
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
			String sql = " SELECT max(idafiliaciondist)+1  FROM multinivel.t_afiliados where ceduladistribuidor='"
					+

					distribuidor + "'";

			Query q = this.entityManager.createNativeQuery(sql);

			List result = q.getResultList();
			s = result.size();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				BigInteger objectArray = (BigInteger) obj;
				retorno = objectArray.intValue();
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
			lista = new ArrayList();

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

			List result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				double total = 0.0D;
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
			lista = new ArrayList();

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

			List result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				double total = 0.0D;
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
		boolean encontro = false;
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
			String sql = " select a.cedula ,CONCAT(A.NOMBRE,' ', A.APELLIDO)NOMBRE_AFILIADO, a.fechaIngreso,a.cedulaDistribuidor,d.nombreDistribuidor   from t_afiliados a ,  ( SELECT DISTINCT CONCAT(A.NOMBRE,' ', A.APELLIDO)nombreDistribuidor ,a.cedula     FROM t_afiliados a,t_afiliados b    where b.cedula= a.ceduladistribuidor AND a.tipoAfiliado='2' order by b.nombre)d  where d.cedula =a.cedulaDistribuidor  and DATE_FORMAT(FECHAINGRESO,'%m/%Y')=?  order by nombreDistribuidor ";

			Query q = this.entityManager.createNativeQuery(sql);

			q.setParameter(1, periodo);
			List result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				lista = new ArrayList();
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String cedulaAfiliado = (String) objectArray[0];
					String nombreAfiliado = (String) objectArray[1];
					Date fechaIngreso = (Date) objectArray[2];
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
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.AfiliadoDAOImp
 */
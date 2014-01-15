package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.dto.AfiliadoConsumo;
import co.com.multinivel.shared.dto.AfiliadosNivel;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class ConsumoDAOImp implements ConsumoDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	ParametroDAO parametroDAO;
	@EJB
	AfiliadoDAO afiliadoDAO;

	public boolean ingresar(Consumo consumo) throws MultinivelDAOException {
		try {
			this.entityManager.persist(consumo);
			Query rs1 = this.entityManager
					.createNativeQuery(" UPDATE t_saldos_pedido_distribuidor SET saldo=(saldo - ?) WHERE  distribuidor=? ");
			long saldo2 = consumo.getTotalpedido().longValue();
			rs1.setParameter(1, Long.valueOf(saldo2));
			rs1.setParameter(2, consumo.getDistribuidor());
			rs1.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Object> consultar(Consumo pConsumo) throws MultinivelDAOException {
		List<Object> lista = null;
		int filtros = 0;
		try {
			String sql = "SELECT  X.afiliado,  X.distribuidor,     X.totalPedido,    X.codigo_Consumo,    X.codigo_producto,    X.nombre_producto,   X.valorUnitario,   X.cantidad,    X.nombre_afiliado,    X.nombre_distribuidor,  X.telefono, X.ciudad,X.fecha FROM     ( SELECT  p.afiliado,  p.distribuidor,   p.totalPedido,  p.codigo_Consumo,  dp.codigo_producto,  pr.nombre_producto, dp.valorUnitario, dp.cantidad,  a.nombre nombre_afiliado,  d.nombre nombre_distribuidor,a.telefono telefono,a.ciudad ciudad,p.fecha  from  t_consumos p,  t_det_consumos dp,  t_productos pr,  t_afiliados a,  t_afiliados d  where   pr.codigo = dp.codigo_producto  and dp.codigo_consumo = p.codigo_consumo  and a.cedula = p.afiliado  and d.cedula = p.distribuidor  and p.codigo_consumo=? )X";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, Integer.valueOf(pConsumo.getCodigoConsumo()));
			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String afiliado = (String) objectArray[0];
				String distribuidor = (String) objectArray[1];
				BigDecimal totalConsumo = (BigDecimal) objectArray[2];
				String codigoConsumo = ((Integer) objectArray[3]).toString();
				String codigoProducto = (String) objectArray[4];
				String nombreProducto = (String) objectArray[5];
				BigDecimal valorUnitario = (BigDecimal) objectArray[6];
				int cantidad = Integer.parseInt(objectArray[7].toString());
				String nombreAfiliado = (String) objectArray[8];
				String nombreDistribuidor = (String) objectArray[9];
				String telefono = objectArray[10] == null ? "" : (String) objectArray[10];
				String ciudadEmpresario = (String) objectArray[11];
				String fecha = ((java.sql.Date) objectArray[12]).toString();

				ConsumoDTO p = new ConsumoDTO();
				p.setCedulaAfiliado(afiliado);
				p.setCedulaDistribuidor(distribuidor);
				p.setFecha(fecha);
				p.setNombreAfiliado(nombreAfiliado);
				p.setNombreDistribuidor(nombreDistribuidor);

				p.setCodigoPedido(codigoConsumo);
				p.setTotalPedido(totalConsumo);
				p.setCodigoProducto(codigoProducto);
				p.setNombreProducto(nombreProducto);
				p.setCantidad(cantidad);
				p.setValorUnitario(valorUnitario);
				p.setTelefono(telefono);
				p.setCiudadEmpresario(ciudadEmpresario);

				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return lista;
	}

	public int ultimoConsumo(Consumo pedido) throws MultinivelDAOException {
		int retorno = 0;
		try {
			String sql = " SELECT MAX(CODIGO_CONSUMO)  FROM T_CONSUMOS WHERE AFILIADO=? AND DISTRIBUIDOR=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, pedido.getAfiliado());
			q.setParameter(2, pedido.getDistribuidor());
			Integer result = (Integer) q.getSingleResult();
			retorno = result.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException(
					"error al realizar la consulta del ultimo pedido del afiliado", getClass());
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo pedido)
			throws MultinivelDAOException {
		BigDecimal totalConsumos = null;
		int filtros = 0;
		try {
			String sql = " SELECT SUM(X.TOTALPEDIDO)       FROM (SELECT       EXTRACT(YEAR_MONTH FROM p.FECHA)PERIODO, P.*        FROM t_CONSUMOS P   WHERE DISTRIBUIDOR=?  AND AFILIADO=? )X  WHERE X.PERIODO=  EXTRACT(YEAR_MONTH FROM NOW())";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, pedido.getDistribuidor());
			q.setParameter(2, pedido.getDistribuidor());

			totalConsumos = (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return totalConsumos;
	}

	public BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo pedido)
			throws MultinivelDAOException {
		BigDecimal totalConsumos = null;
		int filtros = 0;
		try {
			String sql = " SELECT SUM(X.TOTALPEDIDO)       FROM (SELECT       EXTRACT(YEAR_MONTH FROM p.FECHA)PERIODO, P.*        FROM t_CONSUMOS P   WHERE DISTRIBUIDOR=? )X  WHERE X.PERIODO=  EXTRACT(YEAR_MONTH FROM NOW())";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, pedido.getDistribuidor());

			totalConsumos = (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la consultar saldo de afiliados",
					getClass());
		}
		if (totalConsumos == null) {
			totalConsumos = new BigDecimal(0);
		}
		return totalConsumos;
	}

	public Consumo buscar(Consumo pedido) throws MultinivelDAOException {
		return (Consumo) this.entityManager.find(Consumo.class,
				Integer.valueOf(pedido.getCodigoConsumo()));
	}

	public List<Consumo> listar(Consumo pedido) throws MultinivelDAOException {
		List<Consumo> listaConsumo = new ArrayList();
		try {
			Query query = this.entityManager
					.createNativeQuery("select  p.codigo_Consumo,p.transporte,  p.totalConsumo,  p.distribuidor,  p.fecha   from t_Consumos p   where p.distribuidor=?  ");

			query.setParameter(1, pedido.getDistribuidor());
			List lista = query.getResultList();

			int s = lista.size();
			for (int i = 0; i < s; i++) {
				Object obj = lista.get(i);
				Object[] objectArray = (Object[]) obj;
				Integer codigoConsumo = (Integer) objectArray[0];
				BigDecimal transporte = (BigDecimal) objectArray[1];
				BigDecimal totalConsumo = (BigDecimal) objectArray[2];
				String distribuidor = (String) objectArray[3];
				java.util.Date fecha = (java.util.Date) objectArray[4];

				Consumo pedido2 = new Consumo();
				pedido2.setCodigoConsumo(codigoConsumo.intValue());
				pedido2.setDistribuidor(distribuidor);
				pedido2.setFecha(fecha);
				pedido2.setTotalpedido(totalConsumo);

				listaConsumo.add(pedido2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaConsumo;
	}

	public List<Object> calcularConsumosPeriodo(String periodo, String red)
			throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList();
		String sql = " SELECT c.codigo_consumo,c.totalpedido,c.fecha ,      CONCAT(a.nombre,' ',a.apellido),a.cedula  FROM  multinivel.t_afiliados a,  multinivel.t_consumos c  WHERE   c.afiliado=a.cedula  and c.Distribuidor=?  and Date_format(c.fecha,'%m/%Y')=?";

		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, red);
		query.setParameter(2, periodo);
		List lista = query.getResultList();

		int s = lista.size();
		double totalDistribuidor = 0.0D;
		for (int i = 0; i < s; i++) {
			Object obj = lista.get(i);
			Object[] objectArray = (Object[]) obj;
			Integer codigoPedido = (Integer) objectArray[0];
			BigDecimal totalPedido = (BigDecimal) objectArray[1];
			java.util.Date fecha = (java.util.Date) objectArray[2];
			String nombreAfiliado = (String) objectArray[3];
			String afiliado = (String) objectArray[4];

			totalDistribuidor += totalPedido.doubleValue();
			ConsumoDTO pedido2 = new ConsumoDTO();
			pedido2.setCodigoConsumo(codigoPedido.toString());
			pedido2.setFecha(fecha.toString());
			pedido2.setTotalPedido(totalPedido);
			pedido2.setFecha(fecha.toString());

			pedido2.setNombreAfiliado(nombreAfiliado);
			pedido2.setCedulaAfiliado(afiliado);
			pedido2.setTotalDistribuidor(totalDistribuidor);
			listaPedido.add(pedido2);
		}
		return listaPedido;
	}

	public List<Object> listarConsumosRed(Afiliado distribuidor, String periodo)
			throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			lista = new ArrayList();

			List<Object> listaAfiliadosDistribuidor = this.afiliadoDAO.listarPorNivel(distribuidor
					.getCedula());

			Parametro pconsumoMinimo = this.parametroDAO.obtenerValor("CONSUMO_MINIMO");
			double consumoMinimo = Double.parseDouble(pconsumoMinimo.getValor());
			double totDisDinero = 0.0D;
			double totDisProducto = 0.0D;
			String cedulaAnt = "";
			for (Iterator iterator = listaAfiliadosDistribuidor.iterator(); iterator.hasNext();) {
				AfiliadosNivel afiliado = (AfiliadosNivel) iterator.next();
				String cedula = afiliado.getCedula();

				String nombreAfiliadoComp = afiliado.getNombre();
				StringBuffer sql = new StringBuffer();
				sql.append("   SELECT H.NOMBRE_AFILIADO, H.AFILIADO,H.NIVEL, H.COMISION,H.DISTRIBUIDOR,H.CONSUMO, H.AFILIADOCOMISION ");
				sql.append("  FROM ( ");
				sql.append(" SELECT CONCAT(A.NOMBRE,' ', A.APELLIDO)NOMBRE_AFILIADO, CALC.AFILIADO,CALC.NIVEL, CALC.COMISION,CALC.DISTRIBUIDOR,CALC.CONSUMO,AFILIADOCOMISION ");
				sql.append(" FROM ");
				sql.append("(SELECT '" + cedula
						+ "' AFILIADOCOMISION,AFILIADO, DISTRIBUIDOR,NIVEL,CONSUMO, ");
				sql.append("\tROUND(CASE WHEN NIVEL=1 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL1'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN  ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL1'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t\tWHEN NIVEL=2 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL2'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL2'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t\tWHEN NIVEL=3 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL3'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL3'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t\tWHEN NIVEL=4 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL4'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL4'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t\tWHEN NIVEL=5 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL5'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL5'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t\tWHEN NIVEL>5 THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_OTROS'))/100) - ");
				sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
				sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_OTROS'))/100)* ");
				sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
				sql.append("\t  ELSE 0 END ");
				sql.append("\t  END,2)COMISION ");
				sql.append("  FROM ");
				sql.append(" (SELECT  CEDULA_PAPA PATROCINADOR,AFI.CEDULA AFILIADO, CEDULADISTRIBUIDOR DISTRIBUIDOR,NIVEL,VALOR, VALOR CONSUMO ");
				sql.append(" FROM ");
				sql.append(" (SELECT P.CEDULA,P.CEDULA_PAPA, 1 NIVEL, H.CEDULADISTRIBUIDOR ");
				sql.append("\tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\tON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("  AND H.CEDULA='" + cedula + "' ");
				sql.append("  UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 2 NIVEL,A.CEDULADISTRIBUIDOR FROM ");
				sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("\tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\tON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("  AND H.CEDULA='" + cedula + "')NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\tON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 3 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("       )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("       ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 4 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 5 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append(" FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append(" ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "'  ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append("INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 6 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ");
				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 7 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append("ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL6 ");
				sql.append("INNER JOIN T_AFILIADOS A ");
				sql.append("ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append("UNION ALL ");

				sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 8 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A  ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");
				sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 9 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(") NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");
				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 10 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");

				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 11 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL10 ");
				sql.append("INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");

				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 12 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL10 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL11 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");

				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 13 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(") NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL10 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL11 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" )NIVEL12 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");

				sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 14 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("     )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL10 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append("ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL11 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" )NIVEL12 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL13 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" UNION ALL ");

				sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 15 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
				sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
				sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
				sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
				sql.append("       AND H.CEDULA='" + cedula + "' ");
				sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
				sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
				sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" ) NIVEL4 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL6 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL7 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL8 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL9 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL10 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(")NIVEL11 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(")NIVEL12 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append("ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
				sql.append(" )NIVEL13 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

				sql.append(" )NIVEL14 ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON NIVEL14.CEDULA_PAPA=A.CEDULA_PAPA)AFI ");
				sql.append(" INNER JOIN ( ");
				sql.append("          SELECT P.CEDULA CEDULA, SUM(C.TOTALPEDIDO)VALOR FROM T_AFILIADOS P INNER JOIN T_CONSUMOS C ");
				sql.append("\t        ON P.CEDULA=C.AFILIADO ");
				sql.append("          WHERE P.ACTIVO='SI' AND DATE_FORMAT(C.FECHA,'%m/%Y')='"
						+ periodo + "' ");
				sql.append("\t        GROUP BY C.AFILIADO ");

				sql.append(" )ABRE_RED ");
				sql.append(" ON AFI.CEDULA=ABRE_RED.CEDULA ");
				sql.append(" ORDER BY NIVEL ASC ");
				sql.append(" )CONSUMXNIVEL ) CALC ");
				sql.append(" INNER JOIN T_AFILIADOS A ");
				sql.append(" ON A.CEDULA = CALC.AFILIADO ");
				sql.append(" )H INNER JOIN (SELECT '"
						+ cedula
						+ "' CEDULA, SUM(C.TOTALPEDIDO)VALOR FROM T_AFILIADOS P INNER JOIN T_CONSUMOS C ");
				sql.append("       ON P.CEDULA=C.AFILIADO ");
				sql.append("       WHERE P.ACTIVO='SI' AND DATE_FORMAT(C.FECHA,'%m/%Y')='"
						+ periodo + "' AND C.AFILIADO='" + cedula + "' ");
				sql.append("      GROUP BY C.AFILIADO ");
				sql.append("       HAVING SUM(C.TOTALPEDIDO)>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='CONSUMO_MINIMO')) A ");
				sql.append("        ON H.AFILIADOCOMISION =A.CEDULA ");

				Query q = this.entityManager.createNativeQuery(sql.toString());
				List result = q.getResultList();
				int s = result.size();
				if (s > 0) {
					double total = 0.0D;
					for (int i = 0; i < s; i++) {
						Object obj = result.get(i);
						Object[] objectArray = (Object[]) obj;

						String nombre = (String) objectArray[0];
						String cedulaAfiliado = (String) objectArray[1];
						int nivel = ((BigInteger) objectArray[2]).intValue();
						double comision = ((Double) objectArray[3]).doubleValue();
						String ceduladistribuidor = (String) objectArray[4];
						double consumoTotal = ((BigDecimal) objectArray[5]).doubleValue();
						total += comision;

						AfiliadoConsumo afiliadoConsumo = new AfiliadoConsumo();
						afiliadoConsumo.setNombre(nombre);
						afiliadoConsumo.setAfiliado(cedulaAfiliado);
						afiliadoConsumo.setNivel(nivel);
						afiliadoConsumo.setComision(comision);
						afiliadoConsumo.setConsumoTotal(consumoTotal);
						afiliadoConsumo.setAfiliadoCompensacion(cedula);
						afiliadoConsumo.setNombreAfiliadoCompensacion(nombreAfiliadoComp);
						afiliadoConsumo.setTotal(total);

						double consumoProducto = 0.0D;
						double consumoDinero = 0.0D;
						if (total > 0.0D) {
							consumoProducto = total - consumoMinimo;
							if (consumoProducto < 0.0D) {
								consumoProducto = total;
							} else {
								consumoDinero = consumoProducto;
								consumoProducto = consumoMinimo;
							}
						}
						if (i + 1 == s) {
							totDisDinero += consumoDinero;
							totDisProducto += consumoProducto;
							afiliadoConsumo.setComDistrDinero(totDisDinero);
							afiliadoConsumo.setComDistrProducto(totDisProducto);
						}
						afiliadoConsumo.setComisionDinero(consumoDinero);
						afiliadoConsumo.setComisionProducto(consumoProducto);

						lista.add(afiliadoConsumo);
					}
				}
				sql = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosPeriodo(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList();
		try {
			String sql = " select p.codigo_consumo,  p.totalPedido,  p.fecha,  t.codigo_producto,  r.nombre_producto,  t.valorUnitario,  t.cantidad,  t.totalProducto,  p.afiliado,(  SELECT CONCAT (a.NOMBRE ,' ',IF(a.APELLIDO IS NULL,'',a.APELLIDO))) NOMBRE_PADRE from t_consumos p inner join t_det_consumos t on  DATE_FORMAT(P.FECHA,'%m/%Y')='"
					+

					consumo.getPeriodo() + "' ";
			if (consumo.getCedulaDistribuidor() != null) {
				sql = sql + " and p.distribuidor='" + consumo.getCedulaDistribuidor() + "' ";
			} else {
				sql = sql + " and p.afiliado='" + consumo.getCedulaAfiliado() + "' ";
			}
			sql = sql
					+ " and  t.codigo_consumo=p.codigo_consumo inner join t_productos r  on r.codigo=t.codigo_producto inner join t_afiliados a ON a.cedula=p.afiliado ";

			Query query = this.entityManager.createNativeQuery(sql);

			double totalDistribuidor = 0.0D;
			if (query.getResultList() != null) {
				List<Object> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal totalPedido = (BigDecimal) objectArray[1];
					java.util.Date fecha = (java.util.Date) objectArray[2];
					String codigoProducto = (String) objectArray[3];
					String nombreProducto = (String) objectArray[4];
					BigDecimal valorUnitario = (BigDecimal) objectArray[5];
					Integer cantidad = (Integer) objectArray[6];
					BigDecimal totalProducto = (BigDecimal) objectArray[7];
					String cedulaAfiliado = (String) objectArray[8];
					String nombreAfiliado = (String) objectArray[9];

					totalDistribuidor += totalPedido.doubleValue();
					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha.toString());
					pedido2.setTotalPedido(totalPedido);
					pedido2.setCodigoProducto(codigoProducto);
					pedido2.setNombreProducto(nombreProducto);
					pedido2.setValorUnitario(valorUnitario);
					pedido2.setCantidad(cantidad.intValue());
					pedido2.setTotalProducto(totalProducto);
					pedido2.setNombreAfiliado(nombreAfiliado);
					pedido2.setCedulaAfiliado(cedulaAfiliado);
					pedido2.setTotalDistribuidor(totalDistribuidor);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		return listaPedido;
	}

	public boolean eliminar(Consumo pedido) throws MultinivelDAOException {
		try {
			Query rs3 = this.entityManager
					.createNativeQuery(" DELETE FROM T_DET_CONSUMOS  WHERE codigo_consumo=? ");
			rs3.setParameter(1, Integer.valueOf(pedido.getCodigoConsumo()));
			rs3.executeUpdate();

			Query rs2 = this.entityManager
					.createNativeQuery(" DELETE FROM T_CONSUMOS  WHERE codigo_consumo=? ");
			rs2.setParameter(1, Integer.valueOf(pedido.getCodigoConsumo()));
			rs2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO consumo)
			throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList();
		try {
			String sql = "SELECT p.codigo_consumo, \tp.totalPedido,p.fecha, p.afiliado,  (SELECT CONCAT (a.NOMBRE ,' ',IF(a.APELLIDO IS NULL,'',a.APELLIDO))) nombre  FROM t_consumos p INNER JOIN t_afiliados a   ON a.cedula=p.afiliado   AND DATE_FORMAT(P.FECHA,'%m/%Y')='"
					+

					consumo.getPeriodo()
					+ "' "
					+ " and  p.distribuidor='"
					+ consumo.getCedulaDistribuidor() + "' ";

			Query query = this.entityManager.createNativeQuery(sql);
			if (query.getResultList() != null) {
				List<Object> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal totalPedido = (BigDecimal) objectArray[1];
					java.util.Date fecha = (java.util.Date) objectArray[2];
					String cedulaAfiliado = (String) objectArray[3];
					String nombreAfiliado = (String) objectArray[4];

					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha.toString());
					pedido2.setTotalPedido(totalPedido);
					pedido2.setNombreAfiliado(nombreAfiliado);
					pedido2.setCedulaAfiliado(cedulaAfiliado);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		return listaPedido;
	}

	public List<Object> listarConsumosAfiliado(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList();
		try {
			String sql = "select distinct p.codigo_consumo,  p.totalPedido,  \tp.fecha,  \t p.afiliado,  (  SELECT CONCAT (a.NOMBRE ,' ',IF(a.APELLIDO IS NULL,'',a.APELLIDO))) NOMBRE_PADRE  \t from t_consumos p  \t  inner join t_afiliados a ON a.cedula=p.afiliado   and  a.nombre like '"
					+

					consumo.getNombreAfiliado()
					+ "%' "
					+ " union all "
					+ " select distinct p.codigo_consumo,"
					+ " p.totalPedido, "
					+ " p.fecha, "
					+ "  p.afiliado,"
					+ " (  SELECT CONCAT (a.NOMBRE ,' ',IF(a.APELLIDO IS NULL,'',a.APELLIDO))) NOMBRE_PADRE "
					+ " from t_consumos p \tinner join t_afiliados "
					+ " a ON a.cedula=p.afiliado  and a.nombre like '"
					+ consumo.getNombreAfiliado()
					+ "%' and a.apellido like '"
					+ consumo.getApellidoAfiliado() + "%' ";

			Query query = this.entityManager.createNativeQuery(sql);

			double totalDistribuidor = 0.0D;
			if (query.getResultList() != null) {
				List<Object> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal totalPedido = (BigDecimal) objectArray[1];
					java.util.Date fecha = (java.util.Date) objectArray[2];

					String cedulaAfiliado = (String) objectArray[3];
					String nombreAfiliado = (String) objectArray[4];

					totalDistribuidor += totalPedido.doubleValue();
					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha.toString());
					pedido2.setTotalPedido(totalPedido);
					pedido2.setNombreAfiliado(nombreAfiliado);
					pedido2.setCedulaAfiliado(cedulaAfiliado);
					pedido2.setTotalDistribuidor(totalDistribuidor);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		System.err.println(listaPedido.size());
		return listaPedido;
	}

	public List<Object> listarConsumosProducto(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList();
		try {
			String sql = " select p.codigo,p.nombre_producto, x.valorunitario valorUnitario,x.totalProducto,x.cantidad  from  t_productos p,  (select round(sum( cantidad * valorunitario),0)totalProducto         ,codigo_producto, sum(cantidad) cantidad,valorunitario  \t\t\t\t  FROM t_consumos p INNER JOIN t_det_consumos a  \t\t\t\t  ON a.codigo_consumo=p.codigo_consumo  \t\t\t\t  AND DATE_FORMAT(P.FECHA,'%m/%Y')='"
					+

					consumo.getPeriodo()
					+ "' "
					+ " group by codigo_producto) x "
					+ " where p.codigo= x.codigo_producto " + " order by x.cantidad desc ";

			Query query = this.entityManager.createNativeQuery(sql);

			double totalDistribuidor = 0.0D;
			if (query.getResultList() != null) {
				List<Object> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					String codigoProducto = (String) objectArray[0];
					String nombreProducto = (String) objectArray[1];
					BigDecimal valorUnitario = (BigDecimal) objectArray[2];
					BigDecimal totalProducto = (BigDecimal) objectArray[3];
					BigDecimal cantidad = (BigDecimal) objectArray[4];

					ConsumoDTO pedido2 = new ConsumoDTO();

					pedido2.setCodigoProducto(codigoProducto);
					pedido2.setNombreProducto(nombreProducto);
					pedido2.setValorUnitario(valorUnitario);
					pedido2.setTotalProducto(totalProducto);
					pedido2.setCantidad(cantidad.intValue());

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando consumos por producto:"
					+ e.getMessage(), getClass());
		}
		System.err.println(listaPedido.size());
		return listaPedido;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ConsumoDAOImp
 */
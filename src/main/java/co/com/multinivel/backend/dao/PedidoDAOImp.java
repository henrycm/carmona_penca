package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Pedido;
import co.com.multinivel.shared.dto.PedidoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.FechasUtil;

@Repository
@Transactional
public class PedidoDAOImp implements PedidoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public boolean ingresarPedido(Pedido pedido) throws MultinivelDAOException {
		try {
			this.entityManager.persist(pedido);
			Query rs = this.entityManager.createNativeQuery("SELECT SALDO FROM t_saldos_pedido_distribuidor where  distribuidor=?");
			rs.setParameter(1, pedido.getDistribuidor());
			BigDecimal saldo;
			try {
				saldo = (BigDecimal) rs.getSingleResult();
			} catch (Exception e) {
				saldo = null;
			}
			if (saldo != null) {
				Query rs1 = this.entityManager.createNativeQuery(" UPDATE t_saldos_pedido_distribuidor SET saldo=? WHERE    distribuidor=? ");
				long saldo2 = (saldo.longValue() < 0L ? 0L : saldo.longValue()) + pedido.getTotalPedido().longValue();
				rs1.setParameter(1, Long.valueOf(saldo2));
				rs1.setParameter(2, pedido.getDistribuidor());
				rs1.executeUpdate();
			} else {
				Query rs1 = this.entityManager.createNativeQuery("INSERT INTO t_saldos_pedido_distribuidor(distribuidor,saldo)values(?,?)");
				long saldo2 = 0L + pedido.getTotalPedido().longValue();
				rs1.setParameter(2, Long.valueOf(saldo2));
				rs1.setParameter(1, pedido.getDistribuidor());
				rs1.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Object> consultar(Pedido pPedido) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = "SELECT  X.afiliado,  X.distribuidor,     X.totalPedido,    X.codigo_Pedido,    X.codigo_producto,    X.nombre_producto,   X.valorUnitario,   X.cantidad,    X.nombre_afiliado,    X.nombre_distribuidor,  X.telefono, X.ciudad,X.transporte,X.fecha FROM     ( SELECT  p.afiliado,  p.distribuidor,   p.totalPedido,  p.codigo_Pedido,  dp.codigo_producto,  pr.nombre_producto, dp.valorUnitario, dp.cantidad,  a.nombre nombre_afiliado,  d.nombre nombre_distribuidor,a.telefono telefono,a.ciudad ciudad,p.transporte,p.fecha  from  t_pedidos p,  t_det_pedidos dp,  t_productos pr,  t_afiliados a,  t_afiliados d  where   pr.codigo = dp.codigo_producto  and dp.codigo_pedido = p.codigo_pedido  and a.cedula = p.afiliado  and d.cedula = p.distribuidor  and p.codigo_pedido=? )X";
			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, Integer.valueOf(pPedido.getCodigoPedido()));
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<Object>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String afiliado = (String) objectArray[0];
				String distribuidor = (String) objectArray[1];
				BigDecimal totalPedido = (BigDecimal) objectArray[2];
				String codigoPedido = ((Integer) objectArray[3]).toString();
				String codigoProducto = (String) objectArray[4];
				String nombreProducto = (String) objectArray[5];
				BigDecimal valorUnitario = (BigDecimal) objectArray[6];
				int cantidad = Integer.parseInt(objectArray[7].toString());
				String nombreAfiliado = (String) objectArray[8];
				String nombreDistribuidor = (String) objectArray[9];
				String telefono = (String) objectArray[10];
				String ciudadEmpresario = (String) objectArray[11];
				BigDecimal transporte = (BigDecimal) objectArray[12];
				String tmp = (String) objectArray[13];
				Date fecha = FechasUtil.parse(tmp);

				PedidoDTO p = new PedidoDTO();
				p.setCedulaAfiliado(afiliado);
				p.setCedulaDistribuidor(distribuidor);
				p.setFecha(fecha);
				p.setNombreAfiliado(nombreAfiliado);
				p.setNombreDistribuidor(nombreDistribuidor);
				p.setTransporte(transporte);
				p.setPedido(codigoPedido);
				p.setTotalPedido(totalPedido);
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

	public int ultimoPedido(Pedido pedido) throws MultinivelDAOException {
		int retorno = 0;
		try {
			String sql = " SELECT MAX(CODIGO_PEDIDO)  FROM T_PEDIDOS WHERE AFILIADO=? AND DISTRIBUIDOR=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, pedido.getAfiliado());
			q.setParameter(2, pedido.getDistribuidor());
			Integer result = (Integer) q.getSingleResult();
			retorno = result.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la consulta del ultimo pedido del afiliado", getClass());
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Pedido pedido) throws MultinivelDAOException {
		BigDecimal saldo = new BigDecimal(0);
		try {
			Query rs = this.entityManager.createNativeQuery("SELECT SALDO FROM t_saldos_pedido_distribuidor where distribuidor=?");
			rs.setParameter(1, pedido.getDistribuidor());
			try {
				saldo = (BigDecimal) rs.getSingleResult();
			} catch (Exception e) {
				saldo = new BigDecimal(0);
			}
			return saldo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
	}

	public boolean actualizar(Pedido pedido) throws MultinivelDAOException {
		boolean retorno = false;
		try {
			Pedido pedidoConsultado = (Pedido) this.entityManager.find(Pedido.class, Integer.valueOf(pedido.getCodigoPedido()));
			pedidoConsultado.setTransporte(new BigDecimal(0));
			this.entityManager.merge(pedidoConsultado);
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public Pedido buscar(Pedido pedido) throws MultinivelDAOException {
		return (Pedido) this.entityManager.find(Pedido.class, Integer.valueOf(pedido.getCodigoPedido()));
	}

	public List<Pedido> listar(Pedido pedido) throws MultinivelDAOException {
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		try {
			String sql = "select  p.codigo_Pedido,p.transporte,  p.totalPedido,  p.distribuidor,  p.fecha   from t_Pedidos p   where  transporte <>0 ";
			if ((pedido.getDistribuidor() != null) && (!"".equals(pedido.getDistribuidor()))) {
				sql = sql + " and p.distribuidor='" + pedido.getDistribuidor() + "' ";
			}
			Query query = this.entityManager.createNativeQuery(sql);
			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal transporte = (BigDecimal) objectArray[1];
					BigDecimal totalPedido = (BigDecimal) objectArray[2];
					String distribuidor = (String) objectArray[3];
					String tmp = (String) objectArray[4];
					Date fecha = FechasUtil.parse(tmp);

					Pedido pedido2 = new Pedido();
					pedido2.setCodigoPedido(codigoPedido.intValue());
					pedido2.setTransporte(transporte);
					pedido2.setDistribuidor(distribuidor);
					pedido2.setFecha(fecha);
					pedido2.setTotalPedido(totalPedido);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		return listaPedido;
	}

	public List<Object> listarPorPeriodo(PedidoDTO pedido) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		try {
			String sql = " select    p.codigo_Pedido,                    p.transporte,  \t\t\t        p.totalPedido,  \t\t\t        p.fecha,  \t\t\t        t.codigo_producto,  \t\t\t        r.nombre_producto,  \t\t\t        t.valorUnitario,  \t\t\t        t.cantidad,  \t\t\t\t        t.totalProducto  \t\t\t\t        from t_Pedidos p  \t\t\t\t             inner join  \t\t\t\t             t_det_pedidos t  \t\t\t\t\t\t\t\t\t   on  DATE_FORMAT(P.FECHA,'%m/%Y')='"
					+

					pedido.getPeriodo()
					+ "' "
					+ " \t\t\t\t             and p.distribuidor='"
					+ pedido.getCedulaDistribuidor()
					+ "' "
					+ " \t\t\t\t             and  t.codigo_pedido=p.codigo_Pedido "
					+ " \t\t\t\t             inner join t_productos r "
					+ " \t\t\t\t                on r.codigo=t.codigo_producto ";

			Query query = this.entityManager.createNativeQuery(sql);
			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal transporte = (BigDecimal) objectArray[1];
					BigDecimal totalPedido = (BigDecimal) objectArray[2];
					String tmp = (String) objectArray[3];
					Date fecha = FechasUtil.parse(tmp);
					String codigoProducto = (String) objectArray[4];
					String nombreProducto = (String) objectArray[5];
					BigDecimal valorUnitario = (BigDecimal) objectArray[6];
					Integer cantidad = (Integer) objectArray[7];
					BigDecimal totalProducto = (BigDecimal) objectArray[8];

					PedidoDTO pedido2 = new PedidoDTO();
					pedido2.setCodigoPedido(codigoPedido.toString());
					pedido2.setTransporte(transporte);
					pedido2.setFecha(fecha);
					pedido2.setTotalPedido(totalPedido);
					pedido2.setCodigoProducto(codigoProducto);
					pedido2.setNombreProducto(nombreProducto);
					pedido2.setValorUnitario(valorUnitario);
					pedido2.setCantidad(cantidad.intValue());
					pedido2.setTotalProducto(totalProducto);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		return listaPedido;
	}

	public List<Object> listarPedidosAEliminar(PedidoDTO pedido) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		try {
			String sql = " select p.codigo_Pedido,p.totalPedido, p.fecha from t_Pedidos p where  DATE_FORMAT(P.FECHA,'%m/%Y')='"
					+ pedido.getPeriodo() + "' " + " and p.distribuidor='" + pedido.getCedulaDistribuidor() + "' ";

			Query query = this.entityManager.createNativeQuery(sql);
			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					Integer codigoPedido = (Integer) objectArray[0];
					BigDecimal totalPedido = (BigDecimal) objectArray[1];
					String tmp = (String) objectArray[2];
					Date fecha = FechasUtil.parse(tmp);

					PedidoDTO pedido2 = new PedidoDTO();
					pedido2.setCodigoPedido(codigoPedido.toString());
					pedido2.setFecha(fecha);
					pedido2.setTotalPedido(totalPedido);
					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando pedidos:" + e.getMessage(), getClass());
		}
		return listaPedido;
	}

	public boolean eliminarPedido(Pedido pedido) throws MultinivelDAOException {
		try {
			Query rs3 = this.entityManager.createNativeQuery(" DELETE FROM T_DET_PEDIDOS  WHERE codigo_pedido=? ");
			rs3.setParameter(1, Integer.valueOf(pedido.getCodigoPedido()));
			rs3.executeUpdate();

			Query rs2 = this.entityManager.createNativeQuery(" DELETE FROM T_PEDIDOS  WHERE codigo_pedido=? ");
			rs2.setParameter(1, Integer.valueOf(pedido.getCodigoPedido()));
			rs2.executeUpdate();

			Query rs = this.entityManager.createNativeQuery("SELECT SALDO FROM t_saldos_pedido_distribuidor where  distribuidor=?");
			rs.setParameter(1, pedido.getDistribuidor());
			BigDecimal saldo;
			try {
				saldo = (BigDecimal) rs.getSingleResult();
			} catch (Exception e) {
				saldo = new BigDecimal(0);
			}
			if ((saldo != null) && (saldo.longValue() > 0L)) {
				Query rs1 = this.entityManager.createNativeQuery(" UPDATE t_saldos_pedido_distribuidor SET saldo=? WHERE    distribuidor=? ");
				long saldo2 = saldo.longValue() - pedido.getTotalPedido().longValue();

				rs1.setParameter(1, Long.valueOf(saldo2));
				rs1.setParameter(2, pedido.getDistribuidor());
				rs1.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PedidoDAOImp
 */
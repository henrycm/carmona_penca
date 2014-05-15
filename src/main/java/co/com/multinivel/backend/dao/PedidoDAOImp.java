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
		boolean retorno = Boolean.FALSE;
		try {
			this.entityManager.persist(pedido);
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			return retorno;
		}
		return retorno;
	}

	public List<Object> consultar(Pedido pPedido) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = " Select Pd.Codigo_Pedido, Pd.Fecha, Pd.Distribuidor, Di.Nombre+' '+Di.Apellido NombreDistribuidor, "
					+ "Pd.Afiliado, Af.Nombre+' '+Af.Apellido NombreAfiliado, Af.Telefono, Af.Ciudad, Dp.Codigo_Producto, Pr.Nombre_Producto, "
					+ "Dp.ValorUnitario, Dp.Cantidad, Dp.TotalProducto, Dp.Cantidad*Pr.Precio_Afiliado TotalProductoAfiliado, Pd.TotalPedido, Pd.Transporte From T_Pedidos Pd "
					+ "Inner Join T_Det_Pedidos Dp On Pd.Codigo_Pedido=Dp.Codigo_Pedido Inner Join T_Productos Pr On Dp.Codigo_Producto=Pr.Codigo "
					+ "Inner Join T_Afiliados Af On Pd.Afiliado=Af.Cedula Inner Join T_Afiliados Di On Pd.Distribuidor=Di.Cedula "
					+ "Where Pd.Codigo_Pedido= ? ";
			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, Integer.valueOf(pPedido.getCodigoPedido()));
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<Object>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigoPedido = objectArray[0].toString();
				String tmp = (String) objectArray[1];
				Date fecha = FechasUtil.parse(tmp);
				String distribuidor = (String) objectArray[2];
				String nombreDistribuidor = (String) objectArray[3];
				String afiliado = (String) objectArray[4];
				String nombreAfiliado = (String) objectArray[5];
				String telefono = (String) objectArray[6];
				String ciudadEmpresario = (String) objectArray[7];
				String codigoProducto = (String) objectArray[8];
				String nombreProducto = (String) objectArray[9];
				BigDecimal valorUnitario = (BigDecimal) objectArray[10];
				int cantidad = Integer.parseInt(objectArray[11].toString());
				BigDecimal totalProducto = (BigDecimal) objectArray[12];
				BigDecimal totalProductoAfiliado = (BigDecimal) objectArray[13];
				BigDecimal totalPedido = (BigDecimal) objectArray[14];
				BigDecimal transporte = (BigDecimal) objectArray[15];

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
				p.setTotalProducto(totalProducto);
				p.setTotalProductoAfiliado(totalProductoAfiliado);
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

	public BigDecimal consultarValorTotalPedidosPeriodo(String periodo, String distribuidor) throws MultinivelDAOException {
		BigDecimal valor = new BigDecimal(0);
		try {
			Query rs = this.entityManager.createNativeQuery("Select IsNull(Sum(TotalPedido),0)TotalPedidos From T_Pedidos "
					+ "Where Right('00'+Cast(Month(Fecha) As Varchar(2)),2)+'/'+Cast(Year(Fecha) As Varchar(4)) = ? And Distribuidor = ?");
			rs.setParameter(1, periodo);
			rs.setParameter(1, distribuidor);
			try {
				valor = (BigDecimal) rs.getSingleResult();
			} catch (Exception e) {
				valor = new BigDecimal(0);
			}
			return valor;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
	}

	public boolean actualizar(Pedido pedido) throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		try {
			Pedido pedidoConsultado = (Pedido) this.entityManager.find(Pedido.class, Integer.valueOf(pedido.getCodigoPedido()));
			pedidoConsultado.setTransporte(new BigDecimal(0));
			this.entityManager.merge(pedidoConsultado);
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public List<Pedido> listar(Pedido pedido) throws MultinivelDAOException {
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		try {
			String sql = "Select p.codigo_Pedido,p.transporte, p.totalPedido, p.distribuidor, p.fecha From t_Pedidos p where transporte <> 0 ";
			if ((pedido.getDistribuidor() != null) && (!"".equals(pedido.getDistribuidor()))) {
				sql = sql + " and p.distribuidor='" + pedido.getDistribuidor() + "' ";
			}
			sql = sql + " Order By p.Distribuidor Asc ";
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
			String sql = " SELECT p.Codigo_Pedido, p.Transporte, p.TotalPedido, p.Fecha, d.Codigo_Producto, r.Nombre_Producto, d.ValorUnitario, d.Cantidad, d.TotalProducto "
					+ "FROM T_Pedidos p INNER JOIN T_Det_Pedidos d ON  p.Codigo_Pedido = d.Codigo_Pedido "
					+ "INNER JOIN t_productos r ON r.codigo = d.codigo_producto "
					+ "Where Right('00'+Cast(Month(p.Fecha) As Varchar(2)),2)+'/'+Cast(Year(p.Fecha) As Varchar(4))= ? AND p.distribuidor = ? ";

			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter(1, pedido.getPeriodo());
			query.setParameter(2, pedido.getCedulaDistribuidor());

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
			String sql = " select p.codigo_Pedido,p.totalPedido, p.fecha from t_Pedidos p where Right('00'+Cast(Month(Fecha) As Varchar(2)),2)+'/'+Cast(Year(Fecha) As Varchar(4))='"
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

			Query rs = this.entityManager.createNativeQuery("Select Saldo From T_Saldos_Pedido_Distribuidor Where Distribuidor = ?");
			rs.setParameter(1, pedido.getDistribuidor());
			BigDecimal saldo;
			try {
				saldo = (BigDecimal) rs.getSingleResult();
			} catch (Exception e) {
				saldo = new BigDecimal(0);
			}
			if ((saldo != null) && (saldo.longValue() > 0L)) {
				Query rs1 = this.entityManager.createNativeQuery(" Update T_Saldos_Pedido_Distribuidor Set Saldo = ? Where Distribuidor = ?");
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

	public Pedido buscar(Pedido pedido) throws MultinivelDAOException {
		return (Pedido) this.entityManager.find(Pedido.class, pedido.getCodigoPedido());
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PedidoDAOImp
 */
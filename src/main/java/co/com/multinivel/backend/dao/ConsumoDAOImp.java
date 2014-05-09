package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Afiliado;
import co.com.multinivel.backend.model.Consumo;
import co.com.multinivel.shared.dto.ConsumoDTO;
import co.com.multinivel.shared.dto.ReporteConsumoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.FechasUtil;

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
			Query rs1 = this.entityManager.createNativeQuery(" UPDATE t_saldos_pedido_distribuidor SET saldo=(saldo - ?) WHERE  distribuidor=? ");
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
		try {
			String sql = "SELECT  X.afiliado,  X.distribuidor,     X.totalPedido,    X.codigo_Consumo,    X.codigo_producto,    X.nombre_producto,   X.valorUnitario,   X.cantidad,    X.nombre_afiliado,    X.nombre_distribuidor,  X.telefono, X.ciudad,X.fecha FROM     ( SELECT  p.afiliado,  p.distribuidor,   p.totalPedido,  p.codigo_Consumo,  dp.codigo_producto,  pr.nombre_producto, dp.valorUnitario, dp.cantidad,  a.nombre nombre_afiliado,  d.nombre nombre_distribuidor,a.telefono telefono,a.ciudad ciudad,p.fecha  from  t_consumos p,  t_det_consumos dp,  t_productos pr,  t_afiliados a,  t_afiliados d  where   pr.codigo = dp.codigo_producto  and dp.codigo_consumo = p.codigo_consumo  and a.cedula = p.afiliado  and d.cedula = p.distribuidor  and p.codigo_consumo=? )X";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, Integer.valueOf(pConsumo.getCodigoConsumo()));
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<Object>();
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
				String tmp = (String) objectArray[12];
				Date fecha = FechasUtil.parse(tmp);

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
			throw new MultinivelDAOException("error al realizar la consulta del ultimo pedido del afiliado", getClass());
		}
		return retorno;
	}

	public BigDecimal consultarSaldoPorPeriodoDistribuidor(Consumo pedido) throws MultinivelDAOException {
		BigDecimal totalConsumos = null;
		try {
			String sql = "SELECT SUM(X.TOTALPEDIDO)  FROM (SELECT month(p.FECHA)PERIODO, P.*  FROM t_CONSUMOS P   WHERE DISTRIBUIDOR=? )X  WHERE X.PERIODO=  month(getdate())";

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

	public BigDecimal consultarSaldoPorPeriodoDeAfiliados(Consumo pedido) throws MultinivelDAOException {
		BigDecimal totalConsumos = null;
		try {
			String sql = "SELECT SUM(X.TOTALPEDIDO)  FROM (SELECT month(p.FECHA)PERIODO, P.*  FROM t_CONSUMOS P   WHERE DISTRIBUIDOR=? )X  WHERE X.PERIODO=  month(getdate())";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, pedido.getDistribuidor());

			totalConsumos = (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la consultar saldo de afiliados", getClass());
		}
		if (totalConsumos == null) {
			totalConsumos = new BigDecimal(0);
		}
		return totalConsumos;
	}

	public Consumo buscar(Consumo pedido) throws MultinivelDAOException {
		return (Consumo) this.entityManager.find(Consumo.class, Integer.valueOf(pedido.getCodigoConsumo()));
	}

	public List<Consumo> listar(Consumo pedido) throws MultinivelDAOException {
		List<Consumo> listaConsumo = new ArrayList<Consumo>();
		try {
			Query query = this.entityManager
					.createNativeQuery("select  p.codigo_Consumo,p.transporte,  p.totalConsumo,  p.distribuidor,  p.fecha   from t_Consumos p   where p.distribuidor=?  ");

			query.setParameter(1, pedido.getDistribuidor());
			List<?> lista = query.getResultList();

			int s = lista.size();
			for (int i = 0; i < s; i++) {
				Object obj = lista.get(i);
				Object[] objectArray = (Object[]) obj;
				Integer codigoConsumo = (Integer) objectArray[0];
				BigDecimal totalConsumo = (BigDecimal) objectArray[2];
				String distribuidor = (String) objectArray[3];
				String tmp = (String) objectArray[4];
				Date fecha = FechasUtil.parse(tmp);

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

	public List<Object> calcularConsumosPeriodo(String periodo, String distribuidor) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		String sql = " Select c.Afiliado, a.Nombre+' '+a.Apellido NombreAfiliado, c.Codigo_Consumo, c.Fecha, c.TotalPedido From T_Consumos c "
				+ "Inner Join T_Afiliados a On c.Afiliado=a.Cedula "
				+ "Where Right('00'+Cast(Month(c.Fecha) As Varchar(2)),2)+'/'+Cast(Year(c.Fecha) As Varchar(4)) = ? And c.Distribuidor = ? ";

		Query query = this.entityManager.createNativeQuery(sql);
		query.setParameter(1, periodo);
		query.setParameter(2, distribuidor);
		List<?> lista = query.getResultList();

		int s = lista.size();
		double totalDistribuidor = 0.0D;
		for (int i = 0; i < s; i++) {
			Object obj = lista.get(i);
			Object[] objectArray = (Object[]) obj;
			String afiliado = (String) objectArray[0];
			String nombreAfiliado = (String) objectArray[1];
			Integer codigoPedido = Integer.parseInt(objectArray[2].toString());
			String tmp = objectArray[3].toString();
			Date fecha = FechasUtil.parse(tmp);
			BigDecimal totalPedido = (BigDecimal) objectArray[4];

			totalDistribuidor += totalPedido.doubleValue();

			ConsumoDTO pedido2 = new ConsumoDTO();
			pedido2.setCodigoConsumo(codigoPedido.toString());
			pedido2.setFecha(fecha);
			pedido2.setTotalPedido(totalPedido);
			pedido2.setFecha(fecha);

			pedido2.setNombreAfiliado(nombreAfiliado);
			pedido2.setCedulaAfiliado(afiliado);
			pedido2.setTotalDistribuidor(totalDistribuidor);
			listaPedido.add(pedido2);
		}
		return listaPedido;
	}

	public List<Object> listarConsumosRed(Afiliado distribuidor, String periodo) throws MultinivelDAOException {
		List<Object> lista = new ArrayList<Object>();
		try {

			String sql = " Select c.Periodo, c.Distribuidor, d.Nombre+' '+d.Apellido Nom_Distribuidor, c.Papa,\n"
					+ "p.Nombre+' '+p.Apellido Nom_Patrocinador, c.Afiliado, h.Nombre+' '+h.Apellido Nom_Afiliado, \n"
					+ "c.Nivel, c.ConsumoAfiliado, c.Comision From T_Comision_Afiliado_Periodo c\n"
					+ "Inner Join T_Afiliados d On c.Distribuidor=d.Cedula Inner Join T_Afiliados p On c.Papa=p.Cedula\n"
					+ "Inner Join T_Afiliados h On c.Afiliado=h.Cedula Where c.Periodo = ? And c.Distribuidor = ?\n"
					+ "Order By p.Cedula, Nom_Patrocinador, c.Nivel, Nom_Afiliado Asc ";

			String cedula_distribuidor = distribuidor.getCedula();

			Query q = this.entityManager.createNativeQuery(sql).setParameter(1, periodo).setParameter(2, cedula_distribuidor);

			for (Object obj : q.getResultList()) {
				Object[] objectArray = (Object[]) obj;

				ReporteConsumoDTO dto = new ReporteConsumoDTO();
				dto.setDistribuidor((String) objectArray[1]);
				dto.setNom_distribuidor((String) objectArray[2]);
				dto.setPapa((String) objectArray[3]);
				dto.setNom_patrocinador((String) objectArray[4]);
				dto.setAfiliado((String) objectArray[5]);
				dto.setNom_afiliado((String) objectArray[6]);
				dto.setNivel((Integer) objectArray[7]);
				dto.setConsumoAfiliado(((BigDecimal) objectArray[8]).doubleValue());
				dto.setComision(((BigDecimal) objectArray[9]).doubleValue());
				lista.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error en el reporte de consumo", getClass());
		}
		return lista;
	}

	public List<Object> listarConsumosPeriodo(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		String cedula = null;
		try {
			cedula = consumo.getCedulaDistribuidor() != null ? consumo.getCedulaDistribuidor() : consumo.getCedulaAfiliado();

			StringBuffer sql = new StringBuffer();

			sql.append(" Select c.Afiliado, a.Nombre +' '+a.Apellido NombreAfiliado, "
					+ "c.Fecha, c.Codigo_Consumo, d.Codigo_Producto, p.Nombre_Producto, "
					+ "d.Cantidad, d.ValorUnitario, d.TotalProducto, c.TotalPedido From T_Consumos c "
					+ "Inner Join T_Det_Consumos d On c.Codigo_Consumo=d.Codigo_Consumo "
					+ "Inner Join T_Productos p On p.Codigo=d.Codigo_Producto "
					+ "Inner Join T_Afiliados a On c.Afiliado=a.Cedula Where Right('00'+Cast(Month(c.Fecha) As Varchar(2)),2)+'/'+Cast(Year(c.Fecha) As Varchar(4)) = ? ");
			if (consumo.getCedulaDistribuidor() != null) {
				sql.append(" And c.Distribuidor = ?");
			} else {
				sql.append(" And c.Afiliado = ?");
			}

			Query query = this.entityManager.createNativeQuery(sql.toString());
			query.setParameter(1, consumo.getPeriodo());
			query.setParameter(2, cedula);

			System.err.println(sql);

			double totalDistribuidor = 0.0D;
			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;

					String cedulaAfiliado = objectArray[0].toString();
					String nombreAfiliado = objectArray[1].toString();
					String tmp = (String) objectArray[2];
					Date fecha = FechasUtil.parse(tmp);
					Integer codigoPedido = Integer.parseInt(objectArray[3].toString());
					String codigoProducto = objectArray[4].toString();
					String nombreProducto = objectArray[5].toString();
					Integer cantidad = Integer.parseInt(objectArray[6].toString());
					BigDecimal valorUnitario = (BigDecimal) objectArray[7];
					BigDecimal totalProducto = (BigDecimal) objectArray[8];
					BigDecimal totalPedido = (BigDecimal) objectArray[9];

					totalDistribuidor += totalPedido.doubleValue();

					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha);
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
			Query rs3 = this.entityManager.createNativeQuery(" DELETE FROM T_DET_CONSUMOS  WHERE codigo_consumo=? ");
			rs3.setParameter(1, Integer.valueOf(pedido.getCodigoConsumo()));
			rs3.executeUpdate();

			Query rs2 = this.entityManager.createNativeQuery(" DELETE FROM T_CONSUMOS  WHERE codigo_consumo=? ");
			rs2.setParameter(1, Integer.valueOf(pedido.getCodigoConsumo()));
			rs2.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public List<Object> listarConsumosPeriodoAEliminar(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		try {
			String sql = " Select c.Afiliado, a.Nombre+' '+a.Apellido NombreAfiliado, c.Fecha, c.Codigo_Consumo, c.TotalPedido "
					+ "From T_Consumos c Inner Join T_Afiliados a On c.Afiliado=a.Cedula "
					+ "Where Right('00'+Cast(Month(c.Fecha) As Varchar(2)),2)+'/'+Cast(Year(c.Fecha) As Varchar(4))= ? And c.Distribuidor = ? ";

			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter(1, consumo.getPeriodo());
			query.setParameter(2, consumo.getCedulaDistribuidor());

			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					String cedulaAfiliado = objectArray[0].toString();
					String nombreAfiliado = objectArray[1].toString();
					String tmp = (String) objectArray[2];
					Date fecha = FechasUtil.parse(tmp);
					Integer codigoPedido = (Integer) objectArray[3];
					BigDecimal totalPedido = (BigDecimal) objectArray[4];

					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha);
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
		List<Object> listaPedido = new ArrayList<Object>();
		try {
			String sql = " Select Distinct c.Codigo_Consumo,  c.TotalPedido, c.Fecha, c.Afiliado, a.NOMBRE+' '+a.APELLIDO NombreAfiliado "
					+ "From T_Consumos c Inner Join T_Afiliados a On a.Cedula=c.Afiliado Where (a.Nombre like '" + consumo.getNombreAfiliado()
					+ "%') And (a.Nombre like '" + consumo.getNombreAfiliado() + "%' And a.Apellido like '" + consumo.getApellidoAfiliado() + "%')";

			Query query = this.entityManager.createNativeQuery(sql);

			double totalDistribuidor = 0.0D;
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
					String cedulaAfiliado = (String) objectArray[3];
					String nombreAfiliado = (String) objectArray[4];

					totalDistribuidor += totalPedido.doubleValue();

					ConsumoDTO pedido2 = new ConsumoDTO();
					pedido2.setCodigoConsumo(codigoPedido.toString());
					pedido2.setFecha(fecha);
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
		return listaPedido;
	}

	public List<Object> listarConsumosProducto(ConsumoDTO consumo) throws MultinivelDAOException {
		List<Object> listaPedido = new ArrayList<Object>();
		try {
			String sql = " Select d.Codigo_Producto, p.Nombre_Producto, d.ValorUnitario, Sum(d.Cantidad)Cantidad, SUM(d.Cantidad*d.ValorUnitario)ValorTotal "
					+ "From T_Consumos h Inner Join T_Det_Consumos d On h.Codigo_Consumo=d.Codigo_Consumo Inner Join T_Productos p On d.Codigo_Producto=p.Codigo "
					+ "Where Right('00'+Cast(Month(h.Fecha) As Varchar(2)),2)+'/'+Cast(Year(h.Fecha) As Varchar(4))= ? "
					+ "Group By d.Codigo_Producto, p.Nombre_Producto, d.ValorUnitario Order By Sum(d.Cantidad) Desc ";

			Query query = this.entityManager.createNativeQuery(sql);
			query.setParameter(1, consumo.getPeriodo());

			if (query.getResultList() != null) {
				List<?> lista = query.getResultList();

				int s = lista.size();
				for (int i = 0; i < s; i++) {
					Object obj = lista.get(i);
					Object[] objectArray = (Object[]) obj;
					String codigoProducto = (String) objectArray[0];
					String nombreProducto = (String) objectArray[1];
					BigDecimal valorUnitario = (BigDecimal) objectArray[2];
					int cantidad = Integer.parseInt(objectArray[3].toString());
					BigDecimal totalProducto = (BigDecimal) objectArray[4];

					ConsumoDTO pedido2 = new ConsumoDTO();

					pedido2.setCodigoProducto(codigoProducto);
					pedido2.setNombreProducto(nombreProducto);
					pedido2.setValorUnitario(valorUnitario);
					pedido2.setCantidad(cantidad);
					pedido2.setTotalProducto(totalProducto);

					listaPedido.add(pedido2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error listando consumos por producto:" + e.getMessage(), getClass());
		}
		System.err.println(listaPedido.size());
		return listaPedido;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.ConsumoDAOImp
 */
package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.shared.dto.InventarioDistribuidorDTO;
import co.com.multinivel.shared.dto.ProductoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class InvDistribuidorDAOImpl implements InvDistribuidorDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<InventarioDistribuidorDTO> consultarInventarioDistribuidor(String distribuidor) throws MultinivelDAOException {
		List<InventarioDistribuidorDTO> lstInventarioDistribuidor = null;
		try {
			String sql = " Select Id.Distribuidor, Id.Cod_Producto, Pr.Nombre_Producto, Id.Cantidad, pr.Precio_Afiliado, "
					+ "Id.Valor_Total ValorTotalAfiliado, Cast((Pr.Precio_Afiliado)-(Pr.Precio_Afiliado * (Cast(p.Valor As Numeric(11,0))/100))As Numeric(11,0))Precio_Distribuidor, "
					+ "Cast((Pr.Precio_Afiliado)-(Pr.Precio_Afiliado * (Cast(p.Valor As Numeric(11,0))/100))As Numeric(11,0)) * id.Cantidad ValorTotalDistribuidor "
					+ "From T_Inventario_Distribuidor Id Inner Join T_Productos Pr On Id.Cod_Producto=Pr.Codigo Inner Join T_Parametros p On p.Nombre_Parametro='PORC_GAN_PDTO_PROVEE' "
					+ "Where Id.Distribuidor = ? Order By Id.Cantidad Desc, Pr.Nombre_Producto Asc ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, distribuidor);
			List<?> result = q.getResultList();
			int s = result.size();
			lstInventarioDistribuidor = new ArrayList<InventarioDistribuidorDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String Distribuidor = objectArray[0].toString();
				String codigoProducto = objectArray[1].toString();
				String nombreProducto = objectArray[2].toString();
				int cantidad = Integer.parseInt(objectArray[3].toString());
				BigDecimal precioAfiliado = (BigDecimal) objectArray[4];
				BigDecimal valTotalAfiliado = (BigDecimal) objectArray[5];
				BigDecimal precioDistribuidor = (BigDecimal) objectArray[6];
				BigDecimal valTotalDistribuidor = (BigDecimal) objectArray[7];

				ProductoDTO p = new ProductoDTO();
				InventarioDistribuidorDTO inv = new InventarioDistribuidorDTO();
				p.setCodigo(codigoProducto);
				p.setNombreProducto(nombreProducto);
				p.setCantidad(cantidad);
				p.setPrecioAfiliado(precioAfiliado);
				p.setPrecioDistribuidor(precioDistribuidor);

				inv.setDistribuidor(Distribuidor);
				inv.setProducto(p);
				inv.setValorTotalAfiliado(valTotalAfiliado);
				inv.setValorTotalDistribuidor(valTotalDistribuidor);

				lstInventarioDistribuidor.add(inv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstInventarioDistribuidor;
	}

}

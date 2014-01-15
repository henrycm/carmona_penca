package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Producto;
import co.com.multinivel.shared.dto.ProductoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class ProductoDAOImp implements ProductoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public Producto consultar(String codigo) throws MultinivelDAOException {
		return (Producto) this.entityManager.find(Producto.class, codigo);
	}

	public void eliminar(Producto producto) throws MultinivelDAOException {
		try {
			Producto productoConsultado = consultar(producto.getCodigo());
			if (productoConsultado != null) {
				this.entityManager.remove(productoConsultado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error borrando el producto -" + e.getMessage(),
					getClass());
		}
	}

	public void ingresar(Producto producto) throws MultinivelDAOException {
		try {
			Producto productoConsultado = consultar(producto.getCodigo());
			if (productoConsultado == null) {
				this.entityManager.persist(producto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el producto -" + e.getMessage(),
					getClass());
		}
	}

	public void actualizar(Producto producto) throws MultinivelDAOException {
		try {
			Producto productoConsultado = consultar(producto.getCodigo());
			if (productoConsultado != null) {
				this.entityManager.merge(producto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error actualizando el producto -" + e.getMessage(),
					getClass());
		}
	}

	public List<ProductoDTO> listar() throws MultinivelDAOException {
		List<ProductoDTO> lista = null;
		try {
			String sql = " SELECT  P.CODIGO,      P.NOMBRE_PRODUCTO,   TRUNCATE((P.PRECIO_AFILIADO)-(P.PRECIO_AFILIADO *  (CAST(A.VALOR AS DECIMAL(10,0))/100)),0),    P.PRECIO_AFILIADO,    T.TIPOPRODUCTO,P.TIPO      FROM T_PRODUCTOS P,    T_TIPOS_PRODUCTOS T,  (SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORC_GAN_PDTO_PROVEE')A    WHERE T.CODIGO  = P.TIPO ORDER BY T.TIPOPRODUCTO";

			Query q = this.entityManager.createNativeQuery(sql);
			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				BigDecimal precioDist = (BigDecimal) objectArray[2];
				BigDecimal precioAfil = (BigDecimal) objectArray[3];
				String tipo = (String) objectArray[4];
				String codigoTipo = (String) objectArray[5];
				ProductoDTO p = new ProductoDTO();
				p.setCodigo(codigo);

				p.setNombreProducto(nombre);
				p.setPrecioAfiliado(precioAfil);
				p.setPrecioDistribuidor(precioDist);
				p.setTipo(tipo);
				p.setCodigoTipo(codigoTipo);
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<ProductoDTO> listar(String tipoProducto) throws MultinivelDAOException {
		List<ProductoDTO> lista = null;
		try {
			String sql = "SELECT  P.CODIGO, P.NOMBRE_PRODUCTO, TRUNCATE((P.PRECIO_AFILIADO)-(P.PRECIO_AFILIADO *  (CAST(A.VALOR AS DECIMAL(10,2))/100)),0), P.PRECIO_AFILIADO, T.TIPOPRODUCTO,P.TIPO   FROM T_PRODUCTOS P,  T_TIPOS_PRODUCTOS T , (SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORC_GAN_PDTO_PROVEE')A   WHERE T.CODIGO= P.TIPO   AND T.CODIGO=?   ORDER BY T.TIPOPRODUCTO ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, tipoProducto);
			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				BigDecimal precioDist = (BigDecimal) objectArray[2];
				BigDecimal precioAfil = (BigDecimal) objectArray[3];
				String tipo = (String) objectArray[4];
				String codigoTipo = (String) objectArray[5];
				ProductoDTO p = new ProductoDTO();
				p.setCodigo(codigo);
				p.setNombreProducto(nombre);
				p.setPrecioAfiliado(precioAfil);
				p.setPrecioDistribuidor(precioDist);
				p.setTipo(tipo);
				p.setCodigoTipo(codigoTipo);
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<ProductoDTO> listarParaDistribuidor(String tipoProducto)
			throws MultinivelDAOException {
		List<ProductoDTO> lista = null;
		try {
			String sql = "SELECT  P.CODIGO,\t\t P.NOMBRE_PRODUCTO, TRUNCATE((P.PRECIO_AFILIADO)-(P.PRECIO_AFILIADO *  (CAST(A.VALOR AS DECIMAL(10,0))/100)),0),  P.PRECIO_AFILIADO,  T.TIPOPRODUCTO,P.TIPO   FROM T_PRODUCTOS P,  T_TIPOS_PRODUCTOS T, (SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORC_GAN_PDTO_PROVEE')A   WHERE T.CODIGO= P.TIPO   AND T.CODIGO=?   ORDER BY T.TIPOPRODUCTO ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, tipoProducto);
			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				BigDecimal precioDist = (BigDecimal) objectArray[2];
				BigDecimal precioAfil = (BigDecimal) objectArray[3];
				String tipo = (String) objectArray[4];
				String codigoTipo = (String) objectArray[5];
				ProductoDTO p = new ProductoDTO();
				p.setCodigo(codigo);
				p.setNombreProducto(nombre);
				p.setPrecioAfiliado(precioAfil);
				p.setPrecioDistribuidor(precioDist);
				p.setTipo(tipo);
				p.setCodigoTipo(codigoTipo);
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Object> listarProductoPorcentaje(String periodo) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			String sql = "SELECT X.CODIGO,X.PRODUCTO,X.CANTIDAD,X.VALOR,X.PORCCANT,X.PORCVAL     FROM  (SELECT TXP.CODIGO_CONSUMO,TXP.CODIGO,TXP.PRODUCTO,TXP.CANTIDAD,TXP.VALOR,ROUND((TXP.CANTIDAD/TOTAL.CANTIDADT)*100,2)PORCCANT,ROUND((TXP.VALOR/TOTAL.VALORT)*100,2)PORCVAL   FROM  (SELECT C.CODIGO_CONSUMO,C.CODIGO_PRODUCTO CODIGO, P.NOMBRE_PRODUCTO PRODUCTO,SUM(C.CANTIDAD)CANTIDAD,SUM(C.TOTALPRODUCTO)VALOR    FROM T_DET_CONSUMOS C INNER JOIN T_PRODUCTOS P    ON C.CODIGO_PRODUCTO=P.CODIGO  GROUP BY C.CODIGO_PRODUCTO, P.NOMBRE_PRODUCTO  ORDER BY SUM(C.CANTIDAD) DESC)TXP,  (SELECT SUM(CANTIDAD)CANTIDADT,SUM(TOTALPRODUCTO)VALORT FROM T_DET_CONSUMOS)TOTAL)X INNER JOIN T_CONSUMOS CM  ON CM.CODIGO_CONSUMO =X.CODIGO_CONSUMO AND  (DATE_FORMAT(CM.FECHA,'%m/%Y')=?) ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, periodo);
			List result = q.getResultList();
			int s = result.size();
			lista = new ArrayList();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				int cantidad = ((BigDecimal) objectArray[2]).intValue();
				long valor = ((BigDecimal) objectArray[3]).longValue();
				double porcentajeCantidad = ((BigDecimal) objectArray[4]).doubleValue();
				double porcentajeValor = ((BigDecimal) objectArray[5]).doubleValue();
				ProductoDTO p = new ProductoDTO();
				p.setCodigo(codigo);
				p.setNombreProducto(nombre);
				p.setCantidad(cantidad);
				p.setValor(valor);
				p.setPorcentajeCantidad(porcentajeCantidad);
				p.setPorcentajeValor(porcentajeValor);
				lista.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ProductoDAOImp
 */
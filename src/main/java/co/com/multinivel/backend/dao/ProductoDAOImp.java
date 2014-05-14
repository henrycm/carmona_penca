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
			throw new MultinivelDAOException("Error borrando el producto -" + e.getMessage(), getClass());
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
			throw new MultinivelDAOException("Error ingresando el producto -" + e.getMessage(), getClass());
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
			throw new MultinivelDAOException("Error actualizando el producto -" + e.getMessage(), getClass());
		}
	}

	public List<ProductoDTO> listar() throws MultinivelDAOException {
		List<ProductoDTO> lista = null;
		try {
			String sql = " Select  Pr.Codigo, Pr.Nombre_Producto, \n"
					+ "Cast(Round((Pr.Precio_Afiliado)-(Pr.Precio_Afiliado * (Cast(p.Valor As Numeric(11,2))/100)),0) As Numeric(11,0))PrecioDist,\n"
					+ "Cast(Round(Pr.Precio_Afiliado,0)As Numeric(11,0))PrecioAfiliado, t.TipoProducto, Pr.Tipo From T_Productos Pr\n"
					+ "Inner Join T_Tipos_Productos t On Pr.Tipo=t.Codigo\n"
					+ "Inner Join T_Parametros p On p.Nombre_Parametro='PORC_GAN_PDTO_PROVEE' Order By t.TipoProducto Asc ";

			Query q = this.entityManager.createNativeQuery(sql);
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<ProductoDTO>();
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
			String sql = " Select Pr.Codigo, Pr.Nombre_Producto, Pr.Precio_Afiliado, "
					+ "Cast((Pr.Precio_Afiliado)-(Pr.Precio_Afiliado * (Cast(p.Valor As Numeric(11,0))/100))As Numeric(11,0))PrecioDistribuidor, "
					+ "Pr.Tipo, Tp.TipoProducto From T_Productos Pr Inner Join T_Tipos_Productos Tp On Pr.Tipo=Tp.Codigo "
					+ "Inner Join T_Parametros p On p.Nombre_Parametro='PORC_GAN_PDTO_PROVEE' "
					+ "Where Tp.Codigo = ? Order By Tp.Codigo Asc, Pr.Nombre_Producto Asc ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, tipoProducto);
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<ProductoDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;

				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				BigDecimal precioAfil = (BigDecimal) objectArray[2];
				BigDecimal precioDist = (BigDecimal) objectArray[3];
				String codigoTipo = (String) objectArray[4];
				String tipo = (String) objectArray[5];

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

	public List<ProductoDTO> listarParaDistribuidor(String tipoProducto, String distribuidor) throws MultinivelDAOException {
		List<ProductoDTO> lista = null;
		try {
			String sql = " Select Pr.Codigo, Pr.Nombre_Producto, Pr.Precio_Afiliado, "
					+ "Cast((Pr.Precio_Afiliado)-(Pr.Precio_Afiliado * (Cast(p.Valor As Numeric(11,0))/100))As Numeric(11,0))PrecioDistribuidor, "
					+ "Pr.Tipo, Tp.TipoProducto, IsNull(Iv.Cantidad,0)Cantidad "
					+ "From T_Productos Pr Inner Join T_Tipos_Productos Tp On Pr.Tipo=Tp.Codigo "
					+ "Inner Join T_Parametros p On p.Nombre_Parametro='PORC_GAN_PDTO_PROVEE' "
					+ "Left Join T_Inventario_Distribuidor Iv On Pr.Codigo=Iv.Cod_Producto And Iv.Distribuidor = ? "
					+ "Where Tp.Codigo = ? Order By Tp.Codigo Asc, Pr.Nombre_Producto Asc ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, distribuidor);
			q.setParameter(2, tipoProducto);
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<ProductoDTO>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				BigDecimal precioAfil = (BigDecimal) objectArray[2];
				BigDecimal precioDist = (BigDecimal) objectArray[3];
				String codigoTipo = (String) objectArray[4];
				String tipo = (String) objectArray[5];
				int invDistribuidor = Integer.parseInt(objectArray[6].toString());

				ProductoDTO p = new ProductoDTO();
				p.setCodigo(codigo);
				p.setNombreProducto(nombre);
				p.setPrecioAfiliado(precioAfil);
				p.setPrecioDistribuidor(precioDist);
				p.setCodigoTipo(codigoTipo);
				p.setTipo(tipo);
				p.setDisponibilidadDist(invDistribuidor);

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
			String sql = " Select TotProPer.Codigo_Producto, TotProPer.Nombre_Producto, TotProPer.Cantidad, TotProPer.Valor, \n"
					+ "Cast(Cast(TotProPer.Cantidad As Numeric(11,2))/Cast(TotPer.CantidadTot As Numeric(11,2))*100 As Numeric(5,2)) PorcCant, \n"
					+ "Cast(Cast(TotProPer.Valor As Numeric(11,2))/Cast(TotPer.ValorTot As Numeric(11,2))*100 As Numeric(5,2)) PorcVal From\n"
					+ "(Select h.Fecha, d.Codigo_Producto, p.Nombre_Producto, Sum(d.Cantidad)Cantidad, Sum(d.TotalProducto)Valor \n"
					+ "From T_Consumos h Inner Join T_Det_Consumos d On h.Codigo_Consumo=d.Codigo_Consumo\n"
					+ "Left Join T_Productos p On d.Codigo_Producto=p.Codigo\n"
					+ "Group By h.Fecha, d.Codigo_Producto, p.Nombre_Producto)TotProPer Inner Join \n"
					+ "(Select h.Fecha, Sum(d.Cantidad)CantidadTot, Sum(d.TotalProducto)ValorTot From T_Consumos h\n"
					+ "Inner Join T_Det_Consumos d On h.Codigo_Consumo=d.Codigo_Consumo\n"
					+ "Group By h.Fecha)TotPer On TotProPer.Fecha=TotPer.Fecha\n"
					+ "Where Right('00'+Cast(Month(TotProPer.Fecha) As Varchar(2)),2)+'/'+Cast(Year(TotProPer.Fecha) As Varchar(4))=? \n"
					+ "Order by PorcCant Desc, PorcVal Desc ";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, periodo);
			List<?> result = q.getResultList();
			int s = result.size();
			lista = new ArrayList<Object>();
			for (int i = 0; i < s; i++) {
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String codigo = objectArray[0].toString();
				String nombre = objectArray[1].toString();
				int cantidad = Integer.parseInt(objectArray[2].toString());
				double valor = Double.parseDouble(objectArray[3].toString());
				double porcentajeCantidad = Double.parseDouble(objectArray[4].toString());
				double porcentajeValor = Double.parseDouble(objectArray[5].toString());
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
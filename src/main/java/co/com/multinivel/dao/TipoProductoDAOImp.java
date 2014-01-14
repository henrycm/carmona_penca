package co.com.multinivel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.TiposProducto;

@Component
public class TipoProductoDAOImp implements TipoProductoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<TiposProducto> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from TiposProducto");
		List<TiposProducto> lista = query.getResultList();
		return lista;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.TipoProductoDAOImp
 */
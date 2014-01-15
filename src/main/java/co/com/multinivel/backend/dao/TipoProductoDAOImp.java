package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.TiposProducto;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
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
 * Qualified Name: co.com.multinivel.backend.dao.TipoProductoDAOImp
 */
package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class ParametroDAOImp implements ParametroDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Parametro> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Parametro p Order By p.nombreParametro");
		List<Parametro> lista = query.getResultList();
		return lista;
	}

	public Parametro obtenerValor(String parametro) throws MultinivelDAOException {
		return (Parametro) this.entityManager.find(Parametro.class, parametro);
	}

	public boolean guardar(Parametro p) throws MultinivelDAOException {
		boolean retorno = Boolean.FALSE;
		try {
			this.entityManager.merge(p);
			entityManager.flush();
			retorno = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error ingresando el producto -" + e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.ParametroDAOImp
 */
package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Banco;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class BancoDAOImp implements BancoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Banco> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Banco");
		List<Banco> lista = query.getResultList();
		return lista;
	}

	public Banco consultar(String codigo) throws MultinivelDAOException {
		return (Banco) this.entityManager.find(Banco.class, codigo);
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.BancoDAOImp
 */
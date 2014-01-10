package co.com.multinivel.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.model.Banco;

@Stateless
@Local({ BancoDAO.class })
public class BancoDAOImp implements BancoDAO {
	@PersistenceContext(unitName = "multinivelUnit")
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
 * Qualified Name: co.com.multinivel.dao.BancoDAOImp
 * 
 * 
 */
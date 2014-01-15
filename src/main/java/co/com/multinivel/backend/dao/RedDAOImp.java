package co.com.multinivel.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Red;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class RedDAOImp implements RedDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Red> listar() throws MultinivelDAOException {
		Query query = this.entityManager.createQuery("from Red");
		List<Red> lista = query.getResultList();
		return lista;
	}

	public boolean ingresar(Red red) throws MultinivelDAOException {
		boolean retorno = false;
		try {
			Red redConsultado = consultar(red);
			if ((redConsultado == null) || (redConsultado.getCodigo() == null)) {
				this.entityManager.persist(red);
			}
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	private Red consultar(Red pRed) throws MultinivelDAOException {
		Red red = null;
		try {
			Query query = this.entityManager
					.createNativeQuery("select codigo,nombre from t_Redes r where r.codigo=? or r.nombre=? ");
			query.setParameter(1, pRed.getCodigo());
			query.setParameter(2, pRed.getNombre());
			List lista = query.getResultList();

			int s = lista.size();
			if (s > 0) {
				Object obj = lista.get(0);
				Object[] objectArray = (Object[]) obj;
				String codigo = (String) objectArray[0];
				String nombre = (String) objectArray[1];
				red = new Red();
				red.setCodigo(codigo);
				red.setNombre(nombre);
			}
		} catch (Exception localException) {
		}
		return red;
	}

	public Red consultar(String codigo) throws MultinivelDAOException {
		return (Red) this.entityManager.find(Red.class, codigo);
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.RedDAOImp
 */
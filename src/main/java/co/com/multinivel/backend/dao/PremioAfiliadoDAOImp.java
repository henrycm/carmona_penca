package co.com.multinivel.backend.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.PremioAfiliado;
import co.com.multinivel.shared.dto.PremioAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

@Repository
@Transactional
public class PremioAfiliadoDAOImp implements PremioAfiliadoDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Object> consultar(PremioAfiliado premioAfiliado) throws MultinivelDAOException {
		List<Object> listaPremios = new ArrayList();
		try {
			String sql = "SELECT afiliado,p.nombre,periodo FROM t_premios_afiliado_periodo t,t_premios p WHERE p.codigo=t.premio and t.periodo=? AND t.afiliado=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, premioAfiliado.getPeriodo());
			q.setParameter(2, premioAfiliado.getCedula());

			List result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				PremioAfiliadoDTO premioAfiliadoDto = new PremioAfiliadoDTO();
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedula = (String) objectArray[0];
				String nombrePremio = (String) objectArray[1];
				String periodo = (String) objectArray[2];
				premioAfiliadoDto.setAfiliado(cedula);
				premioAfiliadoDto.setNombrePremio(nombrePremio);
				premioAfiliadoDto.setPeriodo(periodo);
				listaPremios.add(premioAfiliadoDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return listaPremios;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.PremioAfiliadoDAOImp
 */
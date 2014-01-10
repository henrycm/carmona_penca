package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import co.com.multinivel.dao.PremioDAO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;
import co.com.multinivel.model.Premio;

@Stateless
@Local({ PremioService.class })
public class PremioServiceImpl implements PremioService {
	@EJB
	private PremioDAO premioDAO;

	public List<Premio> consultar(Premio premio) throws MultinivelServiceException {
		try {
			return this.premioDAO.consultar(premio);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.PremioServiceImpl
 * 
 * 
 */
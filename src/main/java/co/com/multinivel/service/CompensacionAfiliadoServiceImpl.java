package co.com.multinivel.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.dao.CompensacionAfiliadoDAO;
import co.com.multinivel.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.exception.MultinivelDAOException;
import co.com.multinivel.exception.MultinivelServiceException;

@Service
public class CompensacionAfiliadoServiceImpl implements CompensacionAfiliadoService {
	@EJB
	private CompensacionAfiliadoDAO compensacionAfiliadoDAO;

	public List<Object> consultar(CompensacionAfiliadoDTO compensacionAfiliadoDTO)
			throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.consultar(compensacionAfiliadoDTO);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public double getTotalConsumo(CompensacionAfiliadoDTO compensacionAfiliadoDTO)
			throws MultinivelServiceException {
		double retorno = 0.0D;
		try {
			retorno = this.compensacionAfiliadoDAO.totalConsumo(compensacionAfiliadoDTO);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO compensacionAfiliadoDTO)
			throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.listarPagoAfiliados(compensacionAfiliadoDTO);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public int liquidar(String distribuidor, String periodo) throws MultinivelServiceException {
		int retorno = -1;
		try {
			retorno = this.compensacionAfiliadoDAO.liquidar(distribuidor, periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> consultar(String cedula, String periodo) throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.consultar(cedula, periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.CompensacionAfiliadoServiceImpl
 */
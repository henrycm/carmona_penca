package co.com.multinivel.backend.service;

import java.util.List;

import javax.ejb.EJB;

import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.CompensacionAfiliadoDAO;
import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.exception.MultinivelServiceException;

@Service
public class CompensacionAfiliadoServiceImpl implements CompensacionAfiliadoService {
	@EJB
	private CompensacionAfiliadoDAO compensacionAfiliadoDAO;

	public List<Object> comisionAfiliadoPeriodo(String periodo, String cedula) throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.comisionAfiliadoPeriodo(periodo, cedula);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> comisionTotalPorDistribuidorPeriodo(String periodo) throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.comisionTotalPorDistribuidorPeriodo(periodo);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> consultar(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelServiceException {
		List<Object> retorno = null;
		try {
			retorno = this.compensacionAfiliadoDAO.consultar(compensacionAfiliadoDTO);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public double totalConsumo(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelServiceException {
		double retorno = 0.0D;
		try {
			retorno = this.compensacionAfiliadoDAO.totalConsumo(compensacionAfiliadoDTO);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
		return retorno;
	}

	public List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelServiceException {
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

	public void calcularArbol(String cedulaAfiliado, String cedulaDistribuidor) throws MultinivelServiceException {
		try {
			this.compensacionAfiliadoDAO.calcularArbol(cedulaAfiliado, cedulaDistribuidor);
		} catch (MultinivelDAOException e) {
			throw new MultinivelServiceException(e.getMessage(), getClass());
		}
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name:
 * co.com.multinivel.backend.service.CompensacionAfiliadoServiceImpl
 */
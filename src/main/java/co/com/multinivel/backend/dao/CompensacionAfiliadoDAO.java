package co.com.multinivel.backend.dao;

import java.util.List;

import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;

public abstract interface CompensacionAfiliadoDAO {
	public abstract List<Object> comisionAfiliadoPeriodo(String periodo, String cedula) throws MultinivelDAOException;

	public abstract List<Object> comisionTotalPorDistribuidorPeriodo(String periodo) throws MultinivelDAOException;

	public abstract List<Object> consultar(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelDAOException;

	public abstract double totalConsumo(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelDAOException;

	public abstract List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelDAOException;

	public abstract int liquidar(String distribuidor, String periodo) throws MultinivelDAOException;

	public void calcularArbol(String cedulaAfiliado, String cedulaDistribuidor) throws MultinivelDAOException;

}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.CompensacionAfiliadoDAO
 */
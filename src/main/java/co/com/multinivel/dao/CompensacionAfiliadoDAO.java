package co.com.multinivel.dao;

import java.util.List;

import co.com.multinivel.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.exception.MultinivelDAOException;

public abstract interface CompensacionAfiliadoDAO {
	public abstract List<Object> consultar(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO)
			throws MultinivelDAOException;

	public abstract List<Object> consultar(String paramString1, String paramString2)
			throws MultinivelDAOException;

	public abstract double totalConsumo(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO)
			throws MultinivelDAOException;

	public abstract List<Object> listarPagoAfiliados(
			CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelDAOException;

	public abstract int liquidar(String paramString1, String paramString2)
			throws MultinivelDAOException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.dao.CompensacionAfiliadoDAO
 */
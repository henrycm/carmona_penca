package co.com.multinivel.service;

import java.util.List;

import co.com.multinivel.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.exception.MultinivelServiceException;

public abstract interface CompensacionAfiliadoService {
	public abstract List<Object> consultar(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> consultar(String paramString1, String paramString2)
			throws MultinivelServiceException;

	public abstract double getTotalConsumo(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO)
			throws MultinivelServiceException;

	public abstract List<Object> listarPagoAfiliados(
			CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelServiceException;

	public abstract int liquidar(String paramString1, String paramString2)
			throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.service.CompensacionAfiliadoService
 */
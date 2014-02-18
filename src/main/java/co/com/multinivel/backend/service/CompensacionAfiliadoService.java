package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;

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

	public void calcularArbol(String cedula, String tipoUsuario) throws MultinivelServiceException;
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.CompensacionAfiliadoService
 */
package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;

public abstract interface CompensacionAfiliadoService {
	public abstract List<Object> comisionAfiliadoPeriodo(String periodo, String cedula) throws MultinivelServiceException;

	public abstract List<Object> comisionTotalPorDistribuidorPeriodo(String periodo) throws MultinivelServiceException;

	public abstract List<Object> consultar(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelServiceException;

	public abstract double totalConsumo(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelServiceException;

	public abstract List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO paramCompensacionAfiliadoDTO) throws MultinivelServiceException;

	public abstract int liquidar(String distribuidor, String periodo) throws MultinivelServiceException;

	public void calcularArbol(String cedulaAfiliado, String cedulaDistribuidor) throws MultinivelServiceException;

}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.service.CompensacionAfiliadoService
 */
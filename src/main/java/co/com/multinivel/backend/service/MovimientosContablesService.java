package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Mvtos_Cont_Distribuidor;

public interface MovimientosContablesService {

	public abstract List<Mvtos_Cont_Distribuidor> consultar(String distribuidor);

	public abstract void guardar(Mvtos_Cont_Distribuidor movimiento);

	public double consultarSaldo(String distribuidor);

}
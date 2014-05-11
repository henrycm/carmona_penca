package co.com.multinivel.backend.service;

import java.util.List;

import co.com.multinivel.backend.model.Abonos_Distribuidor;

public interface AbonosDistribuidorService {

	public abstract List<Abonos_Distribuidor> consultar(String distribuidor);

	public abstract void guardar(Abonos_Distribuidor movimiento);

}
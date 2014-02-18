package co.com.multinivel.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.multinivel.shared.exception.MultinivelServiceException;

public class ArbolTaskService implements Runnable {

	private CompensacionAfiliadoService service;
	private String _cedula;
	private String _tipoUsuario;

	private final Logger log = LoggerFactory.getLogger(this.getClass().toString());

	public ArbolTaskService(CompensacionAfiliadoService service, String cedula,
			String tipoUsuario) {
		super();
		this.service = service;
		_cedula = cedula;
		_tipoUsuario = tipoUsuario;
	}

	@Override
	public void run() {
		try {
			service.calcularArbol(_cedula, _tipoUsuario);
		} catch (MultinivelServiceException e) {
			log.error(e.getMessage());
		}
	}
}

package co.com.multinivel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.AbonosDistribuidorDAO;
import co.com.multinivel.backend.model.Abonos_Distribuidor;

@Service
public class AbonosDistribuidorServiceImpl implements AbonosDistribuidorService {
	@Autowired
	private AbonosDistribuidorDAO dao;

	@Override
	public List<Abonos_Distribuidor> consultar(String distribuidor) {
		return dao.buscar(distribuidor);
	}

	@Override
	public void guardar(Abonos_Distribuidor movimiento) {
		dao.save(movimiento);
	}
}

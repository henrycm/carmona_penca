package co.com.multinivel.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.multinivel.backend.dao.MovimientosContablesDAO;
import co.com.multinivel.backend.model.Mvtos_Cont_Distribuidor;

@Service
public class MovimientosContablesServiceImpl implements MovimientosContablesService {
	@Autowired
	private MovimientosContablesDAO dao;

	@Override
	public List<Mvtos_Cont_Distribuidor> consultar(String distribuidor)
	{
		return dao.buscar(distribuidor);
	}

	public double consultarSaldo(String distribuidor)
	{
		double total = 0;
		for (Mvtos_Cont_Distribuidor m : consultar(distribuidor)) {
			int tipo = m.getTipo() == 0 ? -1 : 1;
			total += m.getValor() * tipo;
		}
		return total;
	}

	@Override
	public void guardar(Mvtos_Cont_Distribuidor movimiento) {
		dao.save(movimiento);
	}
}

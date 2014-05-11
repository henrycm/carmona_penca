package co.com.multinivel.frontend.pedido;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.com.multinivel.backend.model.Abonos_Distribuidor;
import co.com.multinivel.backend.model.SaldoPedidoDistribuidor;
import co.com.multinivel.backend.service.AbonosDistribuidorService;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.SaldoPedidoDistristribuidorService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

@Controller
public class AbonoController {
	@Autowired
	private AbonosDistribuidorService mvtosService;
	@Autowired
	private AfiliadoService afiliadoService;
	@Autowired
	private SaldoPedidoDistristribuidorService saldoPedidoDistristribuidorService;

	@RequestMapping(value = "/movimiento/inicio")
	public String inicio(@ModelAttribute("movimiento") Abonos_Distribuidor m, ModelMap model, Principal p) {
		try {
			if (UsuarioHelper.getRol() == '1') {
				model.addAttribute("listaDistribuidores", afiliadoService.listarDistribuidores());
				if (m.getDistribuidor() != null) {
					List<Abonos_Distribuidor> l = mvtosService.consultar(m.getDistribuidor());
					model.addAttribute("movimientos", l);
				}
			}
			model.addAttribute("movimiento", m);
		} catch (MultinivelServiceException e) {
			model.addAttribute("error", e.getMessage());
		}
		return RecursosEnum.FW_MOVIMIENTO_CONTABLE.getRecurso();
	}

	@RequestMapping(value = "/movimiento/guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("movimiento") Abonos_Distribuidor m, ModelMap model, final RedirectAttributes ra) {
		try {
			m.setFecha(new Date());
			m.setUsuario(UsuarioHelper.getUsuario());
			mvtosService.guardar(m);

			double saldoAbonos;
			boolean grabadoExito = Boolean.FALSE;
			SaldoPedidoDistribuidor spd = new SaldoPedidoDistribuidor();
			spd = this.saldoPedidoDistristribuidorService.consultarSaldoDistribuidor(m.getDistribuidor());
			if (spd == null) {
				spd = new SaldoPedidoDistribuidor();
				spd.setDistribuidor(m.getDistribuidor());
				spd.setSaldo(0);
			}
			saldoAbonos = spd.getSaldoAbonado() + m.getSaldoAbonado();
			spd.setSaldoAbonado(saldoAbonos);

			grabadoExito = this.saldoPedidoDistristribuidorService.guardar(spd);
			if (grabadoExito) {
				model.addAttribute("mensaje", "El abono fue acumulado con éxito.");
			} else {
				model.addAttribute("error", "Se registró el abono; pero no fue acumulado. Comuníquese con el Administrador.");
			}
		} catch (MultinivelServiceException e) {
			model.addAttribute("error", e.getMessage());
		}
		m.setSaldoAbonado(0);
		ra.addFlashAttribute("movimiento", m);
		return "redirect:/spring/movimiento/inicio";
	}
}

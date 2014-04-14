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

import co.com.multinivel.backend.model.Mvtos_Cont_Distribuidor;
import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.MovimientosContablesService;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

@Controller
public class MovimientoController {
	@Autowired
	private MovimientosContablesService mvtosService;
	@Autowired
	private AfiliadoService afiliadoService;

	@RequestMapping(value = "/movimiento/inicio")
	public String inicio(@ModelAttribute("movimiento") Mvtos_Cont_Distribuidor m, ModelMap model,
			Principal p)
	{
		try {
			if (UsuarioHelper.getRol() == '1')
			{
				model.addAttribute("listaDistribuidores",
						afiliadoService.listarDistribuidores());
			}
			else
			{
				model.addAttribute("distribuidor",
						afiliadoService.consultar(UsuarioHelper.getUsuario()));
				m.setDistribuidor(UsuarioHelper.getUsuario());
			}
			if (m.getDistribuidor() != null)
			{
				List<Mvtos_Cont_Distribuidor> l = mvtosService.consultar(m.getDistribuidor());
				model.addAttribute("movimientos", l);
			}
		} catch (MultinivelServiceException e) {
			model.addAttribute("error", e.getMessage());
		}
		return RecursosEnum.FW_MOVIMIENTO_CONTABLE.getRecurso();
	}

	@RequestMapping(value = "/movimiento/guardar", method = RequestMethod.POST)
	public String guardar(@ModelAttribute("movimiento") Mvtos_Cont_Distribuidor m, ModelMap model,
			final RedirectAttributes ra)
	{
		m.setTipo(1);
		m.setFecha(new Date());
		mvtosService.guardar(m);
		m.setValor(0);
		ra.addFlashAttribute("movimiento", m);
		return "redirect:/spring/movimiento/inicio";
	}
}

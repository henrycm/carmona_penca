package co.com.multinivel.frontend.pedido;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.multinivel.backend.service.AfiliadoService;
import co.com.multinivel.backend.service.InventarioDistribuidorService;
import co.com.multinivel.shared.dto.InventarioDistribuidorDTO;
import co.com.multinivel.shared.exception.MultinivelServiceException;
import co.com.multinivel.shared.helper.UsuarioHelper;
import co.com.multinivel.shared.util.RecursosEnum;

@Controller
public class InventarioController {
	@Autowired
	private InventarioDistribuidorService inventarioDistribuidorService;
	@Autowired
	private AfiliadoService afiliadoService;

	@RequestMapping(value = "/inventarioDistribuidor/inicio")
	public String inicio(@ModelAttribute("inventario") InventarioDistribuidorDTO ind, ModelMap model, Principal p) {
		try {
			if (UsuarioHelper.getRol() == '1') {
				model.addAttribute("listaDistribuidores", afiliadoService.buscarDistribuidor(null, null));
			} else {
				model.addAttribute("listaDistribuidores", afiliadoService.buscarDistribuidor(UsuarioHelper.getUsuario(), null));
			}
			if (ind.getDistribuidor() != null) {
				List<InventarioDistribuidorDTO> lsInv = inventarioDistribuidorService.consultarInventarioDistribuidor(ind.getDistribuidor());
				model.addAttribute("inventarios", lsInv);
			}
			model.addAttribute("inventario", ind);
		} catch (MultinivelServiceException e) {
			model.addAttribute("error", e.getMessage());
		}
		return RecursosEnum.FW_INVENTARIO_DISTRIBUIDOR.getRecurso();
	}
}

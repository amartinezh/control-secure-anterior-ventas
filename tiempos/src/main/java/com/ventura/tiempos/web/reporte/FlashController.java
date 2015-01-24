package com.ventura.tiempos.web.reporte;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.MaskFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.method.annotation.ModelMethodProcessor;

import com.ventura.tiempos.service.reporte.FlashManagerService;
import com.ventura.tiempos.domain.reporte.Flash;


@Controller
@RequestMapping(value="/flash")
@SessionAttributes({"user_inicio"})
public class FlashController {
		
	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private FlashManagerService flashManagerService;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String lanzar(Model model) {
		if(model.containsAttribute("user_inicio") == true) {
			List<Flash> l = new LinkedList<Flash>();
			model.addAttribute("flash", new Flash());
			model.addAttribute("flash1ist", flashManagerService.getFlashList());			
			return "dashboard";		
		} else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/salir", method = RequestMethod.GET)
	public String salir(Model model,  SessionStatus status) {		 
		status.setComplete();
		return "redirect:/index/ingreso";
	}

}
package mx.gob.queretaro.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.request.CityRequest;
import mx.gob.queretaro.service.ICityService;
import mx.gob.queretaro.service.ICountryService;

@Controller
@RequestMapping("ciudad")
public class CityController {

	@Autowired
	private ICityService cityService;
	@Autowired
	private ICountryService countryService;

	@GetMapping("crear")
	public String create(ModelMap model) {
		try {
			List<Country> countries =countryService.obtenerTodos();
			System.out.println("Countrues : "+countries.size());
			model.addAttribute("countries", countryService.obtenerTodos());
		} catch (InternalException ex) {
			model.addAttribute("error", ex.getMessage());
		}

		return "ciudad/crear";
	}

	@PostMapping("guardar")
	public String save(ModelMap model, @Valid @ModelAttribute("cityform") CityRequest cityRequest,
			BindingResult result) {

		try {
			if (result.hasErrors()) {
				model.addAttribute("countries", countryService.obtenerTodos());
				StringBuilder mensaje = new StringBuilder();

				for (FieldError error : result.getFieldErrors()) {
					String campo = error.getField().trim() + " " + error.getDefaultMessage().trim().
							replace("null", "nulo") + ".";

					if (mensaje.toString().trim().isEmpty()) {
						mensaje.append(campo);
					} else {
						mensaje.append("<br />").append(campo);
					}
				}

				model.addAttribute("error", mensaje.toString());

				return "ciudad/crear";
			} else {
				cityService.guardar(cityRequest);

				return "ciudad/exito";
			}

		} catch (InternalException ex) {
			model.addAttribute("error", ex.getMessage());
			return "ciudad/crear";
		}

	}

}

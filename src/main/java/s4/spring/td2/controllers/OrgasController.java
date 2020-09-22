package s4.spring.td2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import s4.spring.td2.models.Organization;
import s4.spring.td2.repositories.OrgaRepository;

@Controller
@RequestMapping("/orgas")
public class OrgasController {
	@Autowired
	private OrgaRepository orgaRepo;
	
	@RequestMapping(value={"/","/index",""})
	public String index(ModelMap model) {
		List<Organization> orgas=orgaRepo.findAll();
		model.put("orgas", orgas);
		return "orgas/index";
	}
	
	@GetMapping("/new")
	public String newO() {
		return "orgas/form";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id) {
		Optional<Organization> orga=orgaRepo.findById(id);
		if(orga.isPresent()) {
			
		}
		return "orgas/form";
	}
}










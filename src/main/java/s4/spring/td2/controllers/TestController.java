package s4.spring.td2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.td2.models.Organization;
import s4.spring.td2.models.User;
import s4.spring.td2.repositories.OrgaRepository;
import s4.spring.td2.repositories.UserRepository;
@Controller
public class TestController {
	
	@Autowired
	private OrgaRepository repo;
	
	@Autowired
	private UserRepository uRepo;
	
	@RequestMapping("orga/new/{name}")
	public @ResponseBody String addOrga(@PathVariable String name) {
		Organization orga=new Organization();
		orga.setName(name);
		repo.saveAndFlush(orga);
		return orga +" ajoutée";
	}
	
	@RequestMapping("orga/{id}")
	public @ResponseBody String getOrga(@PathVariable int id) {
		Optional<Organization> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get()+"";
		}
		return "Organisation non trouvée";
	}
	
	@RequestMapping("user/new/{name}/{orgaName}")
	public @ResponseBody String addUserInOrga(@PathVariable String name,@PathVariable String orgaName) {
	    Optional<Organization> opt=repo.findByName(orgaName);
	    if(opt.isPresent()) {
	    	User user=new User();
	    	user.setFirstname(name);
	    	user.setOrganization(opt.get());
	    	uRepo.saveAndFlush(user);
	    	return user +" ajouté";
	    }
	    return "Organisation "+orgaName+" inexistante !";
	}
	
	@RequestMapping("orga/delete/{name}")
	public @ResponseBody String deleteOrga(@PathVariable String name) {
	    	if(repo.deleteByName(name)==1) {
	    		return "Orga "+name+" supprimée";
	    	}
	    return "Organisation "+name+" inexistante !";
	}
}












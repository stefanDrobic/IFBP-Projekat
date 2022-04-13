package com.ifbp.pkg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifbp.pkg.repository.AccountRepository;

import model.Account;

@Controller
@RequestMapping(value = "/user")
public class AccountController {

	@Autowired
	AccountRepository ar;
	
	//TODO: Preraditi u lokalni metod, treba rest da zovu.
	@RequestMapping(value = "/saveKorisnik")
	public boolean saveKorisnik(@Valid @ModelAttribute("Account") Account acc, BindingResult br) {
		if(br.hasErrors()) {
			return false;
		} else {
			ar.save(acc);
			return true;
		}
	}
	
	//Gotovo, potrebno testirati sa frontom.
	@RequestMapping(value = "/saveKorisnikRest", method = RequestMethod.POST)
	public void saveKorisnikRest(@Valid @RequestBody Account acc, BindingResult br) {
		this.saveKorisnik(acc, br);
	}
	
	//TODO: Uspostaviti security prije login metoda.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean login(@Valid @ModelAttribute("Account") Account acc, BindingResult br) {
		if(br.hasErrors()) {
			return false;
		} else {
			return true;
		}
	}
	
}

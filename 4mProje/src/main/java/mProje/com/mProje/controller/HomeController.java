package mProje.com.mProje.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mProje.com.mProje.entity.User;
import mProje.com.mProje.service.JpaUserDetailsService;



@Controller
public class HomeController {

	@Autowired
	private JpaUserDetailsService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "signup";
	}
	
	@PostMapping("/signup")
	public String doRegistration( @ModelAttribute("user") User u, BindingResult result ,HttpSession session) {
		
		if(result.hasErrors()) {
			return "registration";
		}
		
		if(service.checkEmail(u.getEmail())) {
			// hata döneceğiz
			session.setAttribute("msg", "email kayıtlı.");
			return "registration";
		} else {
			u.setActive(true);
			u.setUsername(u.getEmail());
			u.setInsertDate(LocalDateTime.now());
			u.setRoles("ROLE_USER");
			u.setPassword(encoder.encode(u.getPassword()));
			service.add(u);
		}
		
		return "redirect:/";
	}
	
}

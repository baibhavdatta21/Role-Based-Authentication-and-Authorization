package demo.example.t4_spb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.model.UserPrincipal;
import demo.example.t4_spb.security.Jwtutil;
import demo.example.t4_spb.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("public")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private Jwtutil jwtutil;
	
	@GetMapping("/get")
	public List<UserModel> func1(){
		return userService.get();
	}
	@PostMapping("/gen")
	public ResponseEntity<String> gen(@RequestBody UserModel u) {
		
		return  ResponseEntity.status(HttpStatus.OK).body(userService.gen(u));
	}
}

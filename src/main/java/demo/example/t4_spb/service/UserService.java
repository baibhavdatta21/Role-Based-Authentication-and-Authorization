package demo.example.t4_spb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.model.UserPrincipal;
import demo.example.t4_spb.repository.UserRepository;
import demo.example.t4_spb.security.Jwtutil;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Jwtutil jwtutil;
	
	@Autowired
	AuthenticationManager authManager;
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
	
	public  List<UserModel> get() {
		List<UserModel> lst=userRepository.findAll();
		return lst;
				
	}

	public String gen(UserModel u) {
		String hold=u.getPassword();
		UserPrincipal p=new UserPrincipal(u);
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		
		userRepository.save(u);

		
		Authentication man=authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(), hold));
		if(man.isAuthenticated()) {
			return jwtutil.generateToken(p);
		}
				return "Bad request";
	}
	
}

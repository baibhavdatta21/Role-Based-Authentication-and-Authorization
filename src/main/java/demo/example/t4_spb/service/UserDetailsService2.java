package demo.example.t4_spb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import demo.example.t4_spb.model.UserModel;
import demo.example.t4_spb.model.UserPrincipal;
import demo.example.t4_spb.repository.UserRepository;

@Component
public class UserDetailsService2  implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserModel u=userRepository.findByEmail(email);
		System.out.println(u.getEmail()+"ok");
		UserPrincipal up=new UserPrincipal(u);
		return up;
	}

}

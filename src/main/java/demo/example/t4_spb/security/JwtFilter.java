package demo.example.t4_spb.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import demo.example.t4_spb.service.UserDetailsService2;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	Jwtutil jwtutil;

	@Autowired 
	UserDetailsService2 userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String auth=request.getHeader("Authorization");
		String username=null;
		String token=null;
		System.out.println("Inside filter");
		if(auth!=null && auth.startsWith("Bearer ")) {
			token=auth.substring(7);
			username=jwtutil.extractUserName(token);
		}
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails u= userDetailsService.loadUserByUsername(username);
				
				if(jwtutil.validateToken(token,u)) {
					System.out.println("token validated");
					UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(u,u.getPassword(),u.getAuthorities());
					
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
					System.out.println("Filter work done");
				}
			}
		filterChain.doFilter(request,response);
	}

}

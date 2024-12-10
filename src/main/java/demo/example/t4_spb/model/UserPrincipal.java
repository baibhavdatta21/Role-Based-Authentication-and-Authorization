	package demo.example.t4_spb.model;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private UserModel u;
    
    
    private Set<RoleModel> authorities = new HashSet<>();
    
    public UserPrincipal(UserModel u) {
        this.u = u;
        // Assuming UserModel has a collection of roles
         // Initialize roles from the UserModel
        List<RoleModel>hold=u.getRoleModel();
        for(RoleModel h:hold) {
        	authorities.add(h);
        }
       
    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        // Map the roles to GrantedAuthority
//	    	List<GrantedAuthority>hold2=new ArrayList<GrantedAuthority>();
//	    	u.getRoleModel().forEach(c-> hold2.add( new SimpleGrantedAuthority(c.getRolename())));
//	        return hold2;
	    	return u.getRoleModel().stream()
	                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
	                .collect(Collectors.toList());
	    }

    @Override
    public String getPassword() {
        return u.getPassword();
    }

    @Override
    public String getUsername() {
        return u.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

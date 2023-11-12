package mProje.com.mProje.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import mProje.com.mProje.entity.User;

public class UserDTO implements UserDetails{

	
	private User user;

	public UserDTO(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = user.getRoles();
		String[] authorities = roles.split(",");
		List<SimpleGrantedAuthority> returnAuthorities = new ArrayList<>();
		
		for(int i = 0; i < authorities.length; i++ ) {
			SimpleGrantedAuthority auth = new SimpleGrantedAuthority(authorities[i]);
			returnAuthorities.add(auth);
		}
		
		return returnAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

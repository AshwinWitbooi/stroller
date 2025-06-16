package za.co.ashtech.stroller.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.ashtech.stroller.db.entities.StrollUser;
import za.co.ashtech.stroller.db.repo.StrollUserRepository;

@Service
public class StrollUserDetailsService implements UserDetailsService {
	
	@Autowired
	private StrollUserRepository strollUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		StrollUser user = strollUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new User(user.getUsername(),	user.getPassword(),Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
	}

}

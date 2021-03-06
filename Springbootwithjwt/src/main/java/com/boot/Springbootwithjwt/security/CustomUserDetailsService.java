package com.boot.Springbootwithjwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.Springbootwithjwt.model.User;
import com.boot.Springbootwithjwt.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
    UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

		User user = userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
						.orElseThrow(()-> new UsernameNotFoundException("User not found with username or email"));
		
		return UserPrincipal.create(user);
	}
	
	@Transactional
    public UserDetails loadUserById(Long id) {
		
		User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found with id:"+id));
		
		return UserPrincipal.create(user);
	}

}

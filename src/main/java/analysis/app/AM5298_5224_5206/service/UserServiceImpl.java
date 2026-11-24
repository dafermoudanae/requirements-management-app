package analysis.app.AM5298_5224_5206.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import analysis.app.AM5298_5224_5206.domain.User;
import analysis.app.AM5298_5224_5206.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);	
    }

	@Override
	public boolean isUserPresent(User user) {
		Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
		return storedUser.isPresent();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND %s", username)
	                ));
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}

	@Override
	public void saveUserForProfile(String username, User user) {
		User userExisting=userRepository.findByUsername(username).get();
		userExisting.setName(user.getName());
		userExisting.setEmail(user.getEmail());
		
		if(user.getPassword() != "") {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			userExisting.setPassword(encodedPassword);
		}
		
		userRepository.save(userExisting);
	}

	@Override
	public List<User> findByUsernameNot(String username) {
		return userRepository.findByUsernameNot(username);
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id).get();
	}
}

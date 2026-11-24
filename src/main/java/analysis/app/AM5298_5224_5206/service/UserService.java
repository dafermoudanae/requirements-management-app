package analysis.app.AM5298_5224_5206.service;

import java.util.List;

import org.springframework.stereotype.Service;

import analysis.app.AM5298_5224_5206.domain.User;

@Service
public interface UserService {
	public User findByUsername(String username);
	public void saveUserForProfile(String username, User user);
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public List<User> findByUsernameNot(String username);
    public User findById(int id);
}

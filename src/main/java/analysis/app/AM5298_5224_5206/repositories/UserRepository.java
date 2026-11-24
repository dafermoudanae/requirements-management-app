package analysis.app.AM5298_5224_5206.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import analysis.app.AM5298_5224_5206.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public Optional<User> findByUsername(String username);
	public List<User> findByUsernameNot(String username);
}
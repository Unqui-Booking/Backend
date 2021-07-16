package ar.edu.unq.tip.unquibooking.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public abstract List<User> findByMail(String mail);
	public abstract List<User> findByMailAndPassword(String mail, String password);
	
}

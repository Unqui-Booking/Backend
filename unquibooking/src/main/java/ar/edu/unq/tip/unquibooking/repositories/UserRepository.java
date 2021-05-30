package ar.edu.unq.tip.unquibooking.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.tip.unquibooking.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public abstract ArrayList<User> findByMail(String mail);
	public abstract ArrayList<User> findByMailAndPassword(String mail, String password);
	
}

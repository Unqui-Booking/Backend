package ar.edu.unq.tip.unquibooking.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.tip.unquibooking.model.User;
import ar.edu.unq.tip.unquibooking.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUSer(Long id) {
		return userRepository.findById(id).get();
	}
	
	public ArrayList<User> getUserByMail(String mail){
		return userRepository.findByMail(mail);
	}

}
